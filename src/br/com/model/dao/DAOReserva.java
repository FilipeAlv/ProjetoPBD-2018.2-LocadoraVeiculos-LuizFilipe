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
		return reserva;
	}
	
	public List<Reserva> findByData(Date data){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Reserva> tp = em.createQuery(SQLUtil.Reserva.SELECT_DATA, Reserva.class);
		tp.setParameter("dataInicial", data);
		return tp.getResultList();
	}
	
	public List<Reserva> findByTipoLocacao(String tipo){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Reserva> tp = em.createQuery(SQLUtil.Reserva.SELECT_TIPO, Reserva.class);
		tp.setParameter("tipoLocacao", tipo);
		return tp.getResultList();
	}
	

}
