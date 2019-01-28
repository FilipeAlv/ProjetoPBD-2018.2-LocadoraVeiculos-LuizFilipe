package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.model.beans.Categoria;
import br.com.model.beans.ValorLocacao;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOValorLocacao extends DaoGenerico<ValorLocacao>{
	
	private static DAOValorLocacao daoValorLocacao;
	
	public static DAOValorLocacao getInstance() {
		if(daoValorLocacao != null) {
			return daoValorLocacao;
		}
		return daoValorLocacao = new DAOValorLocacao();
	}
	
	public List<ValorLocacao> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<ValorLocacao> valores = em.createQuery(SQLUtil.ValorLocacao.SELECT_ALL, ValorLocacao.class).getResultList();
		em.close();
		return valores;
	}

	public Double findByTipoCategoria(String tipo, Categoria categoria) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Double> tp = em.createQuery(SQLUtil.ValorLocacao.SELECT_TIPO_CAT, Double.class);
		tp.setParameter("tipo", tipo);
		tp.setParameter("categoria", categoria);
		Double valor = tp.getSingleResult();
		em.close();
		return valor;
		
	}

}
