package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.model.beans.Veiculo;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOVeiculo extends DaoGenerico<Veiculo>{
	private static DAOVeiculo daoVeiculo;
	
	public static DAOVeiculo getInstance() {
		if(daoVeiculo != null) {
			return daoVeiculo;
		}
		return daoVeiculo = new DAOVeiculo();
	}
	
	public List<Veiculo> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Veiculo> veiculos = em.createQuery(SQLUtil.Veiculo.SELECT_ALL, Veiculo.class).getResultList();
		return veiculos;
	}

	public Veiculo findByPlaca(String placa) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_PLACA, Veiculo.class);
		tq.setParameter("placa", placa);
	    Veiculo veiculo = tq.getSingleResult();
		return veiculo;
	}
    
}
