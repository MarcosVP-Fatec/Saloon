package br.gov.sp.fatec.saloon.model.dao.stat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.AlugavelTipoDao;
import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

public class AlugavelTipoDaoJpa implements AlugavelTipoDao {

    private EntityManager em;

    // CONSTRUTORES
    public AlugavelTipoDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public AlugavelTipoDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public AlugavelTipo salvarAlugavelTipo(AlugavelTipo alugavelTipo) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( alugavelTipo , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Tipo do Alugável" + (alugavelTipo.getId()==null ? "!" : " " + alugavelTipo.getDescr() + "!"), e);
        }
        return alugavelTipo;
    }

    @Override
    public AlugavelTipo cadastrarAlugavelTipo(String descr) {
        return salvarAlugavelTipo(new AlugavelTipo(descr));
    }

    @Override
    public AlugavelTipo buscarAlugavelTipo(Long id) {
        String jpql = "select a from AlugavelTipo a where a.id = :id";
        TypedQuery<AlugavelTipo> query = em.createQuery(jpql, AlugavelTipo.class);
        query.setParameter("id", id);
        return query.getSingleResult();    
    }

    @Override
    public AlugavelTipo buscarAlugavelTipo(String descr) {
        String jpql = "select a from AlugavelTipo a where a.descr = :descr";
        TypedQuery<AlugavelTipo> query = em.createQuery(jpql, AlugavelTipo.class);
        query.setParameter("descr", descr);
        return query.getSingleResult();    
    }

    @Override
    public boolean removerAlugavelTipo(Long id) {
        AlugavelTipo alugavelTipo = buscarAlugavelTipo(id);
        if (alugavelTipo.getId() == null) throw new RuntimeException("Tipo de Alugável não cadastrado => ID " + id + "!");
        removerAlugavelTipo(alugavelTipo);
        return true;
    }

    @Override
    public boolean removerAlugavelTipo(AlugavelTipo alugavelTipo) {
        em.getTransaction().begin();
        em.remove(alugavelTipo);
        em.getTransaction().commit();
        return true;
    }
    
}