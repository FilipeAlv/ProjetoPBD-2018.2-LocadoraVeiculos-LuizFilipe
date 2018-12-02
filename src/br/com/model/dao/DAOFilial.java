package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
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
		return filial;
	}
	
	public List<Filial> findByNome(String nome){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Filial> tp = em.createQuery(SQLUtil.Filial.SELECT_NOME, Filial.class);
		tp.setParameter("nome", nome);
		return tp.getResultList();
	}
	
	public Filial findMaxId() {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		Filial filial = em.createQuery(SQLUtil.Filial.SELECT_MAX_ID, Filial.class).getSingleResult();
		return filial;
	}

}
