package br.gov.sp.fatec.saloon.model.dao;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.GeneratorAudit;
import br.gov.sp.fatec.saloon.model.entity.GeneratorId;

public class Generico extends GeneratorId {

    /**
     * Uso genérico do salvar sem commit
     * @param <T>
     * @param EntityManager (Opcional)
     */

    public static <T> void salvarSemCommit(T t) { salvarSemCommit( t , PersistenceManager.getInstance().getEntityManager() ); }
    public static <T> void salvarSemCommit(T t, EntityManager em) {

        if ( ((GeneratorId) t).getId() == null ){ 
            em.persist( t );
        } else {
            GeneratorAudit.setAudit( t );
            em.merge( t );
        }                                       

    }
    
}