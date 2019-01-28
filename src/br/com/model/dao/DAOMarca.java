package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Marca;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOMarca extends DaoGenerico<Marca>{

	private static DAOMarca daoMarca;

	public static DAOMarca getInstance() {
		if(daoMarca != null) {
			return daoMarca;
		}
		return daoMarca = new DAOMarca();
	}

	public List<Marca> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Marca> marcas = em.createQuery(SQLUtil.Marca.SELECT_ALL, Marca.class).getResultList();
		em.close();
		return marcas;
	}

	public Marca findByNome(String nome){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Marca> tp = em.createQuery(SQLUtil.Marca.SELECT_NOME, Marca.class);
		tp.setParameter("nome", nome);
		Marca marca = tp.getSingleResult();
		em.close();
		return marca;
	}
}
