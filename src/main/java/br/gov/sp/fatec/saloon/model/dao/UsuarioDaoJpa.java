package br.gov.sp.fatec.saloon.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.UsuarioDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public class UsuarioDaoJpa implements UsuarioDao {

    private EntityManager em;

    // CONSTRUTORES
    public UsuarioDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public UsuarioDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Usuario buscar(Long id) {
        Usuario retorno;
        String jpql = "select u from Usuario u where u.id = :id";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class).setParameter("id", id);
        try {
            retorno = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public Usuario buscar(String apelido) {
        Usuario retorno;
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.apelido = :apelido", Usuario.class)
                .setParameter("apelido", apelido);
        try {
            retorno = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario retorno;
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
                .setParameter("email", email);
        try {
            retorno = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public boolean existe(Long id) {
        return buscar(id) != null;
    }

    @Override
    public boolean existe(String apelido) {
        return buscar(apelido) != null;
    }

    @Override
    public boolean existeEmail(String email) {
        return buscar(email) != null;
    }

}