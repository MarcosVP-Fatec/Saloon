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
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.id = :id", Usuario.class);
        try {
            return query.setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario buscar(String apelido) {
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.apelido = :apelido", Usuario.class);
        try {
            return query.setParameter("apelido", apelido).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :email", Usuario.class);
        try {
            return query.setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
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

    public static boolean autenticar( String userName, String userPW){
        
        Usuario usuario = new UsuarioDaoJpa().buscar(userName);
        return (usuario != null && usuario.getSenha().equals(userPW));

    }

    public static boolean isAdmin( String userName ){
        
        Usuario usuario = new UsuarioDaoJpa().buscar(userName);
        return ( usuario != null ? usuario.getUsuarioNivel() == 1L: false );

    }
}