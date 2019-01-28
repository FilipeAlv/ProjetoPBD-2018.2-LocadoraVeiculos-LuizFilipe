package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.model.beans.Motorista;
import br.com.model.beans.Motorista2;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOMotoristaBackup extends DaoGenerico<Motorista>{

	private static DAOMotoristaBackup DaoMotorista;

	public static DAOMotoristaBackup getInstace() {
		if (DaoMotorista != null) {
			return DaoMotorista;
		}

		return DaoMotorista = new DAOMotoristaBackup();
	}

	public List<Motorista2> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Motorista2> motoristas = em.createQuery(SQLUtil.MotoristaBackup.SELECT_ALL, Motorista2.class).getResultList();;
		em.close();
		return motoristas;

	}
}
