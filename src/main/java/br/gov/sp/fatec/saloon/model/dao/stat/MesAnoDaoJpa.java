package br.gov.sp.fatec.saloon.model.dao.stat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.MesAnoDao;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

public class MesAnoDaoJpa implements MesAnoDao {

    private EntityManager em;

    // CONSTRUTORES
    public MesAnoDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public MesAnoDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public MesAno salvarMesAno( MesAno mesAno ) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( mesAno , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Tipo do Alugável" + (mesAno.getId()==null ? "!" : " " + mesAno.getDescr() + "!"), e);
        }
        return mesAno;
    }

    @Override
    public MesAno cadastrarMesAno(String id, String descr) {
        return salvarMesAno(new MesAno(id, descr));
    }

    @Override
    public MesAno buscarMesAno(Long id) {
        TypedQuery<MesAno> query = em.createQuery("select m from MesAno m where m.id = :id", MesAno.class);
        return query.setParameter("id", id).getSingleResult();    
    }

    @Override
    public MesAno buscarMesAno(String mes) {
        TypedQuery<MesAno> query = em.createQuery("select m from MesAno m where m.numero = :mes", MesAno.class);
        return query.setParameter("mes", mes).getSingleResult();    
    }
    
}