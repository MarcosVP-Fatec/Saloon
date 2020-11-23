package br.gov.sp.fatec.saloon.model.dao.stat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.UsuarioNivelDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

public class UsuarioNivelDaoJpa implements UsuarioNivelDao {

    private EntityManager em;

    // CONSTRUTORES
    public UsuarioNivelDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public UsuarioNivelDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public UsuarioNivel salvar(UsuarioNivel usuarioNivel) {
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit(usuarioNivel, em);
                em.getTransaction().commit();
            } catch (PersistenceException e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException("Erro ao salvar o Nível de Usuário "
                        + (usuarioNivel.getId() == null ? "!" : " " + usuarioNivel.getDescr() + "!"), e);
            }
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit(usuarioNivel, em);
            em.getTransaction().commit();
        }
        return usuarioNivel;
    }

    @Override
    public UsuarioNivel cadastrar(String descr) {
       return salvar( new UsuarioNivel( descr) );
    }

    @Override
    public UsuarioNivel buscar(Long id) {
        TypedQuery<UsuarioNivel> query = 
        em.createQuery("select a from UsuarioNivel a where a.id = :id", UsuarioNivel.class);
        try {
            return query.setParameter("id", id).getSingleResult();    
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remover(Long id) {
        UsuarioNivel usuarioNivel = buscar(id);
        if (usuarioNivel.getId() == null) throw new RuntimeException("Nível de usuário não cadastrado => ID " + id + "!");
        return remover(usuarioNivel);
    }

    @Override
    public boolean remover(UsuarioNivel usuarioNivel) {
        try {
            em.getTransaction().begin();
            em.remove(usuarioNivel);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
    
}