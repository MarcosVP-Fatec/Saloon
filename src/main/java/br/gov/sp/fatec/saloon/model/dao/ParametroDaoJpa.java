package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ParametroDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;

public class ParametroDaoJpa implements ParametroDao {

    private EntityManager em;

    // CONSTRUTORES
    public ParametroDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ParametroDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public Parametro salvar(Parametro parametro) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( parametro , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Parametro" + (parametro.getId()==null ? "!" : " " + parametro.getCod() + "!"), e);
        }
        return parametro;
    }

    @Override
    public Parametro buscar(Long id) {
        TypedQuery<Parametro> query = em.createQuery("select p from Parametro p where p.id = :id", Parametro.class);
        try {
            return query.setParameter("id", id).getSingleResult();    
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Parametro buscar(String cod) {
        TypedQuery<Parametro> query = em.createQuery("select p from Parametro p where p.cod = :cod", Parametro.class);
        try {
            return query.setParameter("cod", cod).getSingleResult();    
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remover(Parametro parametro) {
        try {
            em.remove(parametro);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remover(Long id) {
        return remover(buscar(id));
    }

    @Override
    public boolean remover(String cod) {
        return remover(buscar(cod));
    }

    @Override
    public boolean existe(Long id) {
        return buscar(id) != null;
    }

    @Override
    public boolean existe(String cod) {
        return buscar(cod) != null;
    }

    @Override
    public Parametro cadastrar(String cod, String descricao, Double numero) {
        return salvar(new Parametro(cod, descricao, numero));
    }

    @Override
    public Parametro cadastrar(String cod, String descricao, Date data) {
        return salvar(new Parametro(cod, descricao, data));
    }

    @Override
    public Parametro cadastrar(String cod, String descricao, String texto) {
        return salvar(new Parametro(cod, descricao, texto));
    }
    
    @Override
    public Parametro cadastrar(String cod, String descricao, boolean logico) {
        return salvar(new Parametro(cod, descricao, logico));
    }
}