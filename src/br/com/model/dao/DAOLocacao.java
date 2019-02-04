package br.com.model.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Locacao;
import br.com.model.beans.Motorista;
import br.com.model.beans.Pessoa;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOLocacao extends DaoGenerico<Locacao>{

	private static DAOLocacao daoLocacao;

	public static DAOLocacao getInstance() {
		if(daoLocacao != null) {
			return daoLocacao;
		}
		return daoLocacao = new DAOLocacao();
	}

	public List<Locacao> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Locacao> locacao = em.createQuery(SQLUtil.Locacao.SELECT_ALL, Locacao.class).getResultList();
		em.close();
		return locacao;
	}

	public List<Locacao> findByData(Date data){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_DATA, Locacao.class);
		tp.setParameter("dataFinal", data);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByMotorista(Motorista motorista) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_MOTORISTA, Locacao.class);
		tp.setParameter("motorista", motorista);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByCliente(Pessoa pessoa) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_CLIENTE, Locacao.class);
		tp.setParameter("pessoa", pessoa);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByDataData(Date de, Date ate) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_DATA_DATA, Locacao.class);
		tp.setParameter("de", de);
		tp.setParameter("ate", ate);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByDataCliente(Date de, Date ate, Pessoa pessoa) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_DATA_CLIENTE, Locacao.class);
		tp.setParameter("de", de);
		tp.setParameter("ate", ate);
		tp.setParameter("pessoa", pessoa);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByDataMotorista(Date de, Date ate, Motorista pessoa) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_DATA_MOTORISTA, Locacao.class);
		tp.setParameter("de", de);
		tp.setParameter("ate", ate);
		tp.setParameter("pessoa", pessoa);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByDataClienteMotorista(Date de, Date ate, Pessoa pessoa, Motorista motorista) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_DATA_CLIENTE_MOTORISTA, Locacao.class);
		tp.setParameter("de", de);
		tp.setParameter("ate", ate);
		tp.setParameter("pessoa", pessoa);
		tp.setParameter("motorista", motorista);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}

	public List<Locacao> findByClienteMotorista(Pessoa pessoa, Motorista motorista) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_CLIENTE_MOTORISTA, Locacao.class);
		tp.setParameter("pessoa", pessoa);
		tp.setParameter("motorista", motorista);
		List<Locacao> locacoes = tp.getResultList();
		em.close();
		return locacoes;
	}


}
