package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.model.beans.Pessoa2;
import br.com.model.beans.PessoaFisica2;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOPessoaFisicaBackup extends DaoGenerico<Pessoa2>{

	private static DAOPessoaFisicaBackup DaoPessoaFisicaBackup;

	public static DAOPessoaFisicaBackup getInstace() {
		if (DaoPessoaFisicaBackup != null) {
			return DaoPessoaFisicaBackup;
		}

		return DaoPessoaFisicaBackup = new DAOPessoaFisicaBackup();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaFisica2> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<PessoaFisica2>pessoasFisicas = em.createQuery(SQLUtil.PessoaFisicaBackup.SELECT_ALL).getResultList();
		em.close();
		return pessoasFisicas;
	}


}
