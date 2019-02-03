package br.com.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.com.model.beans.Financeiro;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOFinanceiro extends DaoGenerico<Financeiro>{

	private static DAOFinanceiro daoFinanceiro;

	public static DAOFinanceiro getInstance() {
		if(daoFinanceiro != null) {
			return daoFinanceiro;
		}
		return daoFinanceiro = new DAOFinanceiro();
	}

	public List<Financeiro> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Financeiro> financeiro = em.createQuery(SQLUtil.Financeiro.SELECT_ALL, Financeiro.class).getResultList();
		em.close();
		return financeiro;
	}

	public List<Financeiro> findByDate(Date de, Date ate){
		List<Financeiro> financeiro;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Financeiro> tp = em.createQuery(SQLUtil.Financeiro.SELECT_DATA, Financeiro.class);
			tp.setParameter("de", de);
			tp.setParameter("ate", ate);
			financeiro = tp.getResultList();
			em.close();
		}catch (NoResultException e) {
			financeiro = null;
		}
		return financeiro;
	}

}