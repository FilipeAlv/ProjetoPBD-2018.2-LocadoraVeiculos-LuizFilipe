package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Motorista;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOMotorista extends DaoGenerico<Motorista>{

    private static DAOMotorista DaoMotorista;

    public static DAOMotorista getInstace() {
        if (DaoMotorista != null) {
            return DaoMotorista;
        }

        return DaoMotorista = new DAOMotorista();
    }

    public List<Motorista> findAll(){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        List<Motorista> motoristas = em.createQuery(SQLUtil.Motorista.SELECT_ALL, Motorista.class).getResultList();;
        return motoristas;

    }

    public List<Motorista> findByHabilitacao(String habilitacao){
		EntityManager em = ConnectionFactory.getInstance().getConnection();
		TypedQuery<Motorista> tp = em.createQuery(SQLUtil.Motorista.SELECT_HABILITACAO, Motorista.class);
		tp.setParameter("habilitacao", habilitacao);
		return tp.getResultList();
	}

	@Override
	public void remove(Class<Motorista> classe, Integer id) {
		super.remove(classe, id);
	}
    

}
