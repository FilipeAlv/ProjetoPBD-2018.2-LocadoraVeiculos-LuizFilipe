package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Categoria;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DAOCategoria extends DaoGenerico<Categoria>{
	
	private static DAOCategoria daoCategoria;
	
	public static DAOCategoria getInstance() {
		if(daoCategoria != null) {
			return daoCategoria;
		}
		return daoCategoria = new DAOCategoria();
	}
	
	public List<Categoria> findAll(){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		List<Categoria> categorias = em.createQuery(SQLUtil.Categoria.SELECT_ALL, Categoria.class).getResultList();
		return categorias;
	}
	
	public List<Categoria> findByTamanho(String tamanho){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Categoria> tp = em.createQuery(SQLUtil.Categoria.SELECT_TAMANHO, Categoria.class);
		tp.setParameter("tamanho", tamanho);
		return tp.getResultList();
	}
	
	public Categoria findMaxId() {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		Categoria categoria = em.createQuery(SQLUtil.Categoria.SELECT_MAX_ID, Categoria.class).getSingleResult();
		return categoria;
	}

	public Categoria findByNome(String nome) {
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Categoria> tp = em.createQuery(SQLUtil.Categoria.SELECT_NOME, Categoria.class);
		tp.setParameter("nome", nome);
		return tp.getSingleResult();
	}


}
