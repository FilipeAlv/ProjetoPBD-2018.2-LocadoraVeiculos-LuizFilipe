package br.com.model.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Locacao;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOLocacao extends DaoGenerico<Locacao>{
	
	private static DAOLocacao daoLocacao;
	
	public static DAOLocacao getInstance() {
		if(daoLocacao != null) {
			return daoLocacao;
		}
		return daoLocacao = new DAOLocacao();
	}
	
	public List<Locacao> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Locacao> locacao = em.createQuery(SQLUtil.Locacao.SELECT_ALL, Locacao.class).getResultList();
		return locacao;
	}
	
	public List<Locacao> findByData(Date data){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Locacao> tp = em.createQuery(SQLUtil.Locacao.SELECT_DATA, Locacao.class);
		tp.setParameter("dataFinal", data);
		return tp.getResultList();
	}
	

}
