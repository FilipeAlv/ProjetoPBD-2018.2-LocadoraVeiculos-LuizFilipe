package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.com.model.beans.Pessoa;
import br.com.model.beans.PessoaFisica;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOPessoaFisica extends DaoGenerico<Pessoa>{

	private static DAOPessoaFisica DaoPessoaFisica;

	public static DAOPessoaFisica getInstace() {
		if (DaoPessoaFisica != null) {
			return DaoPessoaFisica;
		}

		return DaoPessoaFisica = new DAOPessoaFisica();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<PessoaFisica>pessoasFisicas = em.createQuery(SQLUtil.PessoaFisica.SELECT_ALL).getResultList();
		em.close();
		return pessoasFisicas;
	}


	public PessoaFisica findByCpf(String cpf) {
		PessoaFisica pessoa;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<PessoaFisica> tq = em.createQuery(SQLUtil.PessoaFisica.SELECT_CPF, PessoaFisica.class);
			tq.setParameter("cpf", cpf);
			pessoa = tq.getSingleResult();
			em.close();
		}catch (NoResultException e) {
			pessoa = null;
		}
		return pessoa;
	}
	
	public List<PessoaFisica> findByFind(String str){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<PessoaFisica> tq = em.createQuery(SQLUtil.PessoaFisica.SELECT, PessoaFisica.class);
		tq.setParameter("str", "%" + str + "%");
		List<PessoaFisica> pessoaFisica = tq.getResultList();
		em.close();
		return pessoaFisica;
	}
	
	

}
