package br.gov.sp.fatec.saloon.model.tool;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.gov.sp.fatec.saloon.model.PersistenceManager;

public class Sistema {

    /**
     * @apiNote close() -> Rotina que fecha a conexão com o banco de dados e fecha a aplicação
     */
    public static void close(){

        //TODO
        /*
        EntityManager em = PersistenceManager.getInstance().getEntityManager();
        em.getTransaction().rollback();
        em.close();
        PersistenceManager.getInstance().getEntityManagerFactory().close();
        */
        System.exit(0);


    }

}

