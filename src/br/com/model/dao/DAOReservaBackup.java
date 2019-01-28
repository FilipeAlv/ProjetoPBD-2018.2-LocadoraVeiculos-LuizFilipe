package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.model.beans.Reserva2;
import br.com.model.beans.ReservaAdapter;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOReservaBackup extends DaoGenerico<Reserva2>{
	
	private static DAOReservaBackup daoReserva;
	
	public static DAOReservaBackup getInstance() {
		if(daoReserva != null) {
			return daoReserva;
		}
		return daoReserva = new DAOReservaBackup();
	}
	
	public List<Reserva2> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Reserva2> reserva = em.createQuery(SQLUtil.ReservaBackup.SELECT_ALL, Reserva2.class).getResultList();
		em.close();
		return reserva;
	}
	
	public List<ReservaAdapter> findView(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		@SuppressWarnings("unchecked")
		List<ReservaAdapter> reserva = em.createQuery(SQLUtil.ReservaBackup.SELECT_VIEW).getResultList();
		em.close();
		return reserva;
	}

}
