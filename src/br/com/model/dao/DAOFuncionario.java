package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Funcionario;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOFuncionario extends DaoGenerico<Funcionario>{

    private static DAOFuncionario DAOFuncionario;

    public static DAOFuncionario getInstace() {
        if (DAOFuncionario != null) {
            return DAOFuncionario;
        }

        return DAOFuncionario = new DAOFuncionario();
    }

    public List<Funcionario> findAll(){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        List<Funcionario> funcionario = em.createQuery(SQLUtil.Funcionario.SELECT_ALL, Funcionario.class).getResultList();;
        return funcionario;

    }
    
    public List<Funcionario> findByCargo(String cargo){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        TypedQuery<Funcionario> tq = em.createQuery(SQLUtil.Funcionario.SELECT_CARGO, Funcionario.class);
        tq.setParameter("cargo", cargo);
        List<Funcionario> funcionario = tq.getResultList();
        return funcionario;

    }

    
   

}
