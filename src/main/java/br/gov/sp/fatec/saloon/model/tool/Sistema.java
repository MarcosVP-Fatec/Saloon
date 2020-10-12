package br.gov.sp.fatec.saloon.model.tool;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.saloon.model.PersistenceManager;

public class Sistema {

    /**
     * @apiNote close() -> Rotina que fecha a conexão com o banco de dados e fecha a aplicação
     */
    public static void close(){

        EntityManager em = PersistenceManager.getInstance().getEntityManager();
        if ( em.getTransaction().getRollbackOnly() ) {
            em.getTransaction().rollback();
            System.out.println(">>>>>> Encontradas Transações Pendentes que foram desfeitas!");
        }
        em.close();
        PersistenceManager.getInstance().getEntityManagerFactory().close();

        System.exit(0);

    }

}

