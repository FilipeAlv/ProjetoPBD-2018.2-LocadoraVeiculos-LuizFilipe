package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.com.model.beans.Filial;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOFilial extends DaoGenerico<Filial>{

	private static DAOFilial daoFilial;

	public static DAOFilial getInstance() {
		if(daoFilial != null) {
			return daoFilial;
		}
		return daoFilial = new DAOFilial();
	}

	public List<Filial> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Filial> filial = em.createQuery(SQLUtil.Filial.SELECT_ALL, Filial.class).getResultList();
		em.close();
		return filial;
	}

	public Filial findByNome(String nome){
		Filial filial;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Filial> tp = em.createQuery(SQLUtil.Filial.SELECT_NOME, Filial.class);
			tp.setParameter("nome", nome);
			filial = tp.getSingleResult();
			em.close();
		}catch (NoResultException e) {
			filial = null;
		}
		return filial;
	}

	public Integer findIdByNome(String nome){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		@SuppressWarnings("unchecked")
		TypedQuery<Integer> tp = (TypedQuery<Integer>) em.createQuery(SQLUtil.Filial.SELECT_ID_NOME);
		tp.setParameter("nome", nome);
		Integer id = tp.getSingleResult();
		em.close();
		return id;
	}


	public Filial findMaxId() {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		Filial filial = em.createQuery(SQLUtil.Filial.SELECT_MAX_ID, Filial.class).getSingleResult();
		em.close();
		return filial;
	}

}
