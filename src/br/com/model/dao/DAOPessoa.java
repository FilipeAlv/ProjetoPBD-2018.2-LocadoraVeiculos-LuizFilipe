package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Pessoa;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOPessoa extends DaoGenerico<Pessoa>{

    private static DAOPessoa DaoPessoa;

    public static DAOPessoa getInstace() {
        if (DaoPessoa != null) {
            return DaoPessoa;
        }

        return DaoPessoa = new DAOPessoa();
    }

    public List<Pessoa> findAll(){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        List<Pessoa> resultado = em.createQuery(SQLUtil.Pessoa.SELECT_ALL, Pessoa.class).getResultList();;
        return resultado;

    }

    public String findMaxCodigo() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        String codigo = em.createQuery(SQLUtil.Pessoa.SELECT_MAX_COD, String.class).getSingleResult();
        return codigo;
    }
    
    public Integer findMaxId() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        Integer id = em.createQuery(SQLUtil.Pessoa.SELECT_MAX_ID, Integer.class).getSingleResult();
        return id;
    }

}
