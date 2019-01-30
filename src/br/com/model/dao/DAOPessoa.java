package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import br.com.model.beans.Pessoa;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOPessoa extends DaoGenerico<Pessoa>{

	private static DAOPessoa DaoPessoa;

	public static DAOPessoa getInstace() {
		if (DaoPessoa != null) {
			return DaoPessoa;
		}

		return DaoPessoa = new DAOPessoa();
	}

	public List<Pessoa> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Pessoa> resultado = em.createQuery(SQLUtil.Pessoa.SELECT_ALL, Pessoa.class).getResultList();;
		em.close();
		return resultado;

	}

	public String findMaxCodigo() {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		String codigo = em.createQuery(SQLUtil.Pessoa.SELECT_MAX_COD, String.class).getSingleResult();
		em.close();
		return codigo;
	}

	public Integer findMaxId() {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		Integer id = em.createQuery(SQLUtil.Pessoa.SELECT_MAX_ID, Integer.class).getSingleResult();
		em.close();
		return id;
	}

	public Pessoa findByLoginSenha(String login, String senha) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Pessoa> query = em.createQuery(SQLUtil.Pessoa.SELECT_LOGIN_SENHA, Pessoa.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		Pessoa pessoa = query.getSingleResult();
		em.close();
		return pessoa;
	}

	public Pessoa findByLogin(String login) {
		Pessoa pessoa;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Pessoa> query = em.createQuery(SQLUtil.Pessoa.SELECT_LOGIN, Pessoa.class);
			query.setParameter("login", login);
			pessoa = query.getSingleResult();
			em.close();
		}catch (NoResultException e) {
			pessoa = null;
		}
		return pessoa;
	}

	public Pessoa findByCPF(String cpf) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Pessoa> query = em.createQuery(SQLUtil.Pessoa.SELECT_CPF, Pessoa.class);
		query.setParameter("cpf", cpf);
		Pessoa pessoa = query.getSingleResult();
		em.close();
		return pessoa;
	}

	public void gerarCodigo(String nome){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		StoredProcedureQuery query = em
				.createStoredProcedureQuery(SQLUtil.Pessoa.PROCEDURE_GERA_CODIGO)
				.registerStoredProcedureParameter("nome", String.class, ParameterMode.IN)
				.setParameter("nome", nome);

		query.execute();
		em.close();

	}

	public void updateSenha(String login, String senhaAtual, String novaSenha) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		em.createQuery(SQLUtil.Pessoa.UPDADTE_SENHA)
		.setParameter("login", login)
		.setParameter("senha", senhaAtual)
		.setParameter("novaSenha", novaSenha)
		.executeUpdate();
		em.close();
	}


}