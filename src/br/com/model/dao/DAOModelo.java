package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Modelo;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOModelo extends DaoGenerico<Modelo>{
	
	private static DAOModelo daoModelo;
	
	public static DAOModelo getInstance() {
		if(daoModelo != null) {
			return daoModelo;
		}
		return daoModelo = new DAOModelo();
	}
	
	public List<Modelo> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Modelo> Modelos = em.createQuery(SQLUtil.Modelo.SELECT_ALL, Modelo.class).getResultList();
		return Modelos;
	}
	
	public Modelo findByNome(String nome){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Modelo> tp = em.createQuery(SQLUtil.Modelo.SELECT_NOME, Modelo.class);
		tp.setParameter("nome", nome);
		return tp.getSingleResult();
	}
}
