package br.com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.model.beans.CaminhonetaCarga;
import br.com.util.ConnectionFactory;
import br.com.util.SQLUtil;


public class DAOCaminhonetaCarga extends DaoGenerico<CaminhonetaCarga>{

    private static DAOCaminhonetaCarga DaoCaminhonetaCarga;

    public static DAOCaminhonetaCarga getInstace() {
        if (DaoCaminhonetaCarga != null) {
            return DaoCaminhonetaCarga;
        }

        return DaoCaminhonetaCarga = new DAOCaminhonetaCarga();
    }

    public List<CaminhonetaCarga> findAll(){
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        List<CaminhonetaCarga> resultado = em.createQuery(SQLUtil.CaminhonetaCarga.SELECT_ALL, CaminhonetaCarga.class).getResultList();;
        em.close();
        return resultado;

    }

    public List<CaminhonetaCarga> findPotencia(float potencia) {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        TypedQuery<CaminhonetaCarga> tq = em.createQuery(SQLUtil.CaminhonetaCarga.SELECT_POTENCIA, CaminhonetaCarga.class);
        List<CaminhonetaCarga> caminhonetasCarga = tq.getResultList();
        em.close();
        return caminhonetasCarga;
    }
    
    @Override
    public void remove(Class<CaminhonetaCarga> classe, Integer id) {
    	super.remove(classe, id);
    }

}
