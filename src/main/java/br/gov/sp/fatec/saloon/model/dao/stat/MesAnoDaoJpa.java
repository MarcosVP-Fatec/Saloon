package br.gov.sp.fatec.saloon.model.dao.stat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.MesAnoDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

public class MesAnoDaoJpa implements MesAnoDao {

    private EntityManager em;

    // CONSTRUTORES
    public MesAnoDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public MesAnoDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public MesAno salvar(MesAno mesAno) {
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit(mesAno, em);
                em.getTransaction().commit();
            } catch (PersistenceException e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException("Erro ao salvar o Tipo do Alugável"
                        + (mesAno.getId() == null ? "!" : " " + mesAno.getDescr() + "!"), e);
            }
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit(mesAno, em);
            em.getTransaction().commit();
        }
        return mesAno;
    }

    @Override
    public MesAno cadastrar(String id, String descr) {
        return salvar(new MesAno(id, descr));
    }

    @Override
    public MesAno buscar(Long id) {
        TypedQuery<MesAno> query = em.createQuery("select m from MesAno m where m.id = :id", MesAno.class);
        try {
            return query.setParameter("id", id).getSingleResult();    
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MesAno buscar(String mes) {
        TypedQuery<MesAno> query = em.createQuery("select m from MesAno m where m.numero = :mes", MesAno.class);
        try {
            return query.setParameter("mes", mes).getSingleResult();    
        } catch (Exception e) {
            return null;
        }
    }
    
}