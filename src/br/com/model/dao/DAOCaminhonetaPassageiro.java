package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.model.beans.CaminhonetaPassageiro;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOCaminhonetaPassageiro extends DaoGenerico<CaminhonetaPassageiro>{

    private static DAOCaminhonetaPassageiro DaoCaminhonetaPassageiro;

    public static DAOCaminhonetaPassageiro getInstace() {
        if (DaoCaminhonetaPassageiro != null) {
            return DaoCaminhonetaPassageiro;
        }

        return DaoCaminhonetaPassageiro = new DAOCaminhonetaPassageiro();
    }

    public List<CaminhonetaPassageiro> findAll(){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        List<CaminhonetaPassageiro> resultado = em.createQuery(SQLUtil.CaminhonetaPassageiro.SELECT_ALL, CaminhonetaPassageiro.class).getResultList();
        em.close();
        return resultado;

    }
}
