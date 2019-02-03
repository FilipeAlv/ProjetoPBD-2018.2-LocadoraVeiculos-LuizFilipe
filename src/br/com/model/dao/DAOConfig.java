package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.model.beans.Config;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOConfig extends DaoGenerico<Config>{

	private static DAOConfig daoConfig;

	public static DAOConfig getInstance() {
		if(daoConfig != null) {
			return daoConfig;
		}
		return daoConfig = new DAOConfig();
	}

	public List<Config> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Config> config = em.createQuery(SQLUtil.Config.SELECT_ALL, Config.class).getResultList();
		em.close();
		return config;
	}
}
