package br.com.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import br.com.model.beans.Categoria;
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
		Veiculo veiculo;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_PLACA, Veiculo.class);
			tq.setParameter("placa", placa);
			veiculo = tq.getSingleResult();
			em.close();
		}catch (NoResultException e) {
			veiculo = null;
		}
		return veiculo;
	}

	public List<Veiculo> findByFilial(Filial filial) {
		List<Veiculo> veiculos;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_FILIAL, Veiculo.class);
			tq.setParameter("filial", filial);
			veiculos = tq.getResultList();
			em.close();
		}catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	}


	public List<Veiculo> findByStatus(String status) {
		List<Veiculo> veiculos ;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_STATUS, Veiculo.class);
			tq.setParameter("status", status);	
			veiculos = tq.getResultList();
			em.close();
		}catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	}


	public List<Veiculo> findByFind(String str){
		List<Veiculo> veiculos;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_BUSCA, Veiculo.class);
			tq.setParameter("str", "%" + str + "%");
			veiculos= tq.getResultList();
			em.close();
		}catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	}

	public List<Veiculo> findByFindFilial(String str, Filial filial){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_BUSCA_FILIAL, Veiculo.class);
		tq.setParameter("str", "%" + str + "%");
		tq.setParameter("filial", filial );
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}

	public List<Veiculo> findByFindStatus(String str, String status){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_BUSCA_STATUS, Veiculo.class);
		tq.setParameter("str", "%" + str + "%");
		tq.setParameter("status", "%" + status + "%");
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

	public List<Veiculo> findByData(Date data) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_DATA, Veiculo.class);
		tq.setParameter("data", data );
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}

	public List<Veiculo> findByDataFilial(Date data, Filial filial) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_DATA_FILIAL, Veiculo.class);
		tq.setParameter("data", data );
		tq.setParameter("filial", filial);
		List<Veiculo> veiculos = tq.getResultList();
		em.close();
		return veiculos;
	}

	public List<Veiculo> findByFilialCategoria(Filial filial, Categoria categoria) {
		List<Veiculo> veiculos ;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_CATEGORIA_FILIAL, Veiculo.class);
			tq.setParameter("categoria", categoria );
			tq.setParameter("filial", filial);
			veiculos= tq.getResultList();
			em.close();
		}catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	}
	public List<Veiculo> findByCategoria(Categoria categoria) {
		List<Veiculo> veiculos;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_CATEGORIA, Veiculo.class);
			tq.setParameter("categoria", categoria);
			veiculos = tq.getResultList();
			em.close();
		}catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	
	}

	public List<Veiculo> findByFilialStatus(Filial filial, String status) {
		List<Veiculo> veiculos;
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<Veiculo> tq = em.createQuery(SQLUtil.Veiculo.SELECT_STATUS_FILIAL, Veiculo.class);
			tq.setParameter("status", status);
			tq.setParameter("filial", filial);
			veiculos = tq.getResultList();
			em.close();
		}catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	}

}
