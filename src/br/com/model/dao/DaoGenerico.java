package br.com.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import br.com.model.beans.EntidadeBase;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;

public class DaoGenerico<T extends EntidadeBase> {
	private static EntityManager manager = ConnectionFactory.getInstance().getConnection();

	public T findById(Class<T> classe, Integer id){
		return manager.find(classe, id);
	}

	public void saveOrUpdate(T obj){
		try{
			manager.getTransaction().begin();
			if(obj.getId() == null){
				manager.persist(obj);
			}else{
				manager.merge(obj);
			}
			manager.getTransaction().commit();
		}catch(Exception e){
			manager.getTransaction().rollback();
		}
	}

	public void remove(Class<T> classe, Integer id){
		T t = findById(classe, id);
		try{
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		}catch (Exception ex) {
			System.out.println("Erro desconhecido");
			manager.getTransaction().rollback();
		}
	}

	public static boolean existe(String objeto, String tabela, String campo, String atributo) {
		try {
			EntityManager em = ConnectionFactory.getInstance().getConnection();
			TypedQuery<EntidadeBase> tp = em.createQuery(SQLUtil.DaoGenerico.EXISTE, EntidadeBase.class);
			tp.setParameter("objeto", objeto);
			tp.setParameter("tabela", tabela);
			tp.setParameter("campo", campo);
			tp.setParameter("atributo", atributo);
			tp.getSingleResult();
			return true;}
		catch (Exception e) {
			return false;
		}

	}
	
	
}