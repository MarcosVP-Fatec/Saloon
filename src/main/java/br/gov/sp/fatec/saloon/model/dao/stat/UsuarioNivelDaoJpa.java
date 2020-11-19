package br.gov.sp.fatec.saloon.model.dao.stat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.UsuarioNivelDao;
import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

public class UsuarioNivelDaoJpa implements UsuarioNivelDao {

    private EntityManager em;

    // CONSTRUTORES
    public UsuarioNivelDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public UsuarioNivelDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public UsuarioNivel salvar(UsuarioNivel usuarioNivel) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( usuarioNivel , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Nível de Usuário " + (usuarioNivel.getId()==null ? "!" : " " + usuarioNivel.getDescr() + "!"), e);
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
        return query.setParameter("id", id).getSingleResult();    
    }

    @Override
    public boolean remover(Long id) {
        return remover(buscar(id));
    }

    @Override
    public boolean remover(UsuarioNivel usuarioNivel) {
        em.getTransaction().begin();
        em.remove(usuarioNivel);
        em.getTransaction().commit();
        return true;
    }
    
}