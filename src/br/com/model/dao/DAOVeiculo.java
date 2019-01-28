package br.com.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import br.com.model.beans.Filial;
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
		em.close();
		return veiculos;

	}

	public Veiculo findByPlaca(String placa) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_PLACA, Veiculo.class);
		tq.setParameter("placa", placa);
		Veiculo veiculo = tq.getSingleResult();
		em.close();
		return veiculo;
	}

	public List<Veiculo> findByFilial(Integer filial) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createNamedQuery(SQLUtil.Veiculo.SELECT_FILIAL, Veiculo.class);
		tq.setParameter("filial", filial);
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}

	public List<Veiculo> findByAllReserva(Filial filial, Date data) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_ALL_RESERVA, Veiculo.class);
		tq.setParameter("filial", filial);
		tq.setParameter("data", data);
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}

	public List<Veiculo> findByStatus(String status) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_STATUS, Veiculo.class);
		tq.setParameter("status", status);	
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}


	public List<Veiculo> findByFind(String str){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT, Veiculo.class);
		tq.setParameter("str", "%" + str + "%");
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}

	public Integer contaVeiculoCategoria(Integer categoria) {
		Integer total;
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		StoredProcedureQuery query = em
				.createStoredProcedureQuery(SQLUtil.Veiculo.PROCEDURE_CONTA_VEICULO)
				.registerStoredProcedureParameter("categoria", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("total", Integer.class, ParameterMode.OUT)
				.setParameter("categoria", categoria);
		total= (Integer)query.getOutputParameterValue("total");
		query.execute();
		em.close();
		return total;
	}

}
