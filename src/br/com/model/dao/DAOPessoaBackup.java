package br.com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.model.beans.ClienteAdapter;
import br.com.model.beans.Pessoa2;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOPessoaBackup extends DaoGenerico<Pessoa2>{

	private static DAOPessoaBackup DaoPessoaBackup;

	public static DAOPessoaBackup getInstace() {
		if (DaoPessoaBackup != null) {
			return DaoPessoaBackup;
		}

		return DaoPessoaBackup = new DAOPessoaBackup();
	}

	public List<ClienteAdapter> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<ClienteAdapter> resultado = em.createQuery(SQLUtil.PessoaBackup.SELECT_ALL, ClienteAdapter.class).getResultList();
		em.close();
		return resultado;
	}	

}

