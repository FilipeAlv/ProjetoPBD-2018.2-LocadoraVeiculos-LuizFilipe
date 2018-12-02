package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.model.beans.Endereco;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOEndereco extends DaoGenerico<Endereco>{

    private static DAOEndereco DaoEndereco;

    public static DAOEndereco getInstace() {
        if (DaoEndereco != null) {
            return DaoEndereco;
        }

        return DaoEndereco = new DAOEndereco();
    }

    public List<Endereco> findByUF(String uf){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        TypedQuery<Endereco> tq = em.createQuery(SQLUtil.Endereco.SELECT_UF, Endereco.class);
        tq.setParameter("uf", uf);
        List<Endereco> enderecos = tq.getResultList();;
        return enderecos;

    }

    public Integer findMaxId() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        Integer id = em.createQuery(SQLUtil.Endereco.SELECT_MAX_ID, Integer.class).getSingleResult();
        return id;
    }

}
