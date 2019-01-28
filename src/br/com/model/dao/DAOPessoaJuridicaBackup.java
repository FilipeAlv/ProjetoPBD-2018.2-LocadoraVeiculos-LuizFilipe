package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.model.beans.Pessoa2;
import br.com.model.beans.PessoaJuridica2;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOPessoaJuridicaBackup extends DaoGenerico<Pessoa2>{

	private static DAOPessoaJuridicaBackup DaoPessoaJuridicaBackup;

	public static DAOPessoaJuridicaBackup getInstace() {
		if (DaoPessoaJuridicaBackup != null) {
			return DaoPessoaJuridicaBackup;
		}

		return DaoPessoaJuridicaBackup = new DAOPessoaJuridicaBackup();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica2> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<PessoaJuridica2>pessoasJuridicas = em.createQuery(SQLUtil.PessoaJuridicaBackup.SELECT_ALL).getResultList();
		em.close();
		return pessoasJuridicas;
	}

}
