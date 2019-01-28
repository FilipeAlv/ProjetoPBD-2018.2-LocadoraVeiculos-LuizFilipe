package br.com.model.dao;


import java.util.List;
import javax.persistence.EntityManager;
import br.com.model.beans.Locacao;
import br.com.model.beans.Locacao2;
import br.com.model.beans.LocacaoAdapter;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOLocacaoBackup extends DaoGenerico<Locacao>{

	private static DAOLocacaoBackup daoLocacao;

	public static DAOLocacaoBackup getInstance() {
		if(daoLocacao != null) {
			return daoLocacao;
		}
		return daoLocacao = new DAOLocacaoBackup();
	}

	public List<Locacao2> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Locacao2> locacao = em.createQuery(SQLUtil.LocacaoBackup.SELECT_ALL, Locacao2.class).getResultList();
		em.close();
		return locacao;
	}

	public List<LocacaoAdapter> findView(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<LocacaoAdapter> locacao = em.createQuery(SQLUtil.LocacaoBackup.SELECT_VIEW, LocacaoAdapter.class).getResultList();
		em.close();
		return locacao;
	}


}
