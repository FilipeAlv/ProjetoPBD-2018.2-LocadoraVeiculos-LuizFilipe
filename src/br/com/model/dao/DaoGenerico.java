package br.com.model.dao;

import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import br.com.model.beans.EntidadeBase;
import br.com.util.ConnectionFactory;

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
		}catch (ConstraintViolationException e) {
			System.out.println("Usuario com esse nome");
			manager.getTransaction().rollback();
		}catch (Exception ex) {
			System.out.println("Erro desconhecido");
			manager.getTransaction().rollback();
		}
	}
}