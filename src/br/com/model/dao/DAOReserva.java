package br.com.model.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Reserva;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOReserva extends DaoGenerico<Reserva>{
	
	private static DAOReserva daoReserva;
	
	public static DAOReserva getInstance() {
		if(daoReserva != null) {
			return daoReserva;
		}
		return daoReserva = new DAOReserva();
	}
	
	public List<Reserva> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Reserva> reserva = em.createQuery(SQLUtil.Reserva.SELECT_ALL, Reserva.class).getResultList();
		em.close();
		return reserva;
	}
	
	public List<Reserva> findByData(Date data){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Reserva> tp = em.createQuery(SQLUtil.Reserva.SELECT_DATA, Reserva.class);
		tp.setParameter("dataInicial", data);
		List<Reserva> reservas = tp.getResultList();
		em.close();
		return reservas;
	}
	
	public List<Reserva> findByTipoLocacao(String tipo){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Reserva> tp = em.createQuery(SQLUtil.Reserva.SELECT_TIPO, Reserva.class);
		tp.setParameter("tipoLocacao", tipo);
		List<Reserva> reservas = tp.getResultList();
		em.close();
		return reservas;
	}

	public List<Reserva> findByDataData(Date de, Date ate) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Reserva> tp = em.createQuery(SQLUtil.Reserva.SELECT_DATA_DATA, Reserva.class);
		tp.setParameter("de", de);
		tp.setParameter("ate", ate);
		List<Reserva> reservas = tp.getResultList();
		em.close();
		return reservas;
		
	}
	

}
