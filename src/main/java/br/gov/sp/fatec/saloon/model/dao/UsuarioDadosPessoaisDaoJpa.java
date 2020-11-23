package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;

public class UsuarioDadosPessoaisDaoJpa implements UsuarioDadosPessoaisDao {

    private EntityManager em;

    // CONSTRUTORES
    public UsuarioDadosPessoaisDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public UsuarioDadosPessoaisDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public UsuarioDadosPessoais salvar(UsuarioDadosPessoais usuarioDadosPessoais) {
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit(usuarioDadosPessoais, em);
                em.getTransaction().commit();
            } catch (PersistenceException e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException("Erro ao salvar o Usuário"
                        + (usuarioDadosPessoais.getId() == null ? "!" : " " + usuarioDadosPessoais.getNome() + "!"), e);
            }
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit(usuarioDadosPessoais, em);
            em.getTransaction().commit();
        }

        return usuarioDadosPessoais;
    }

    @Override
    public UsuarioDadosPessoais cadastrar(String apelido
                                         ,String email
                                         ,String senha
                                         ,String nome
                                         ,Date   dtNascimento
                                         ,String cpf
                                         ,Long   nivelUsuario) {

        return salvar( new UsuarioDadosPessoais(apelido
                                               , email
                                               , senha
                                               , nome
                                               , dtNascimento
                                               , cpf
                                               , nivelUsuario) );

    }

    @Override
    public UsuarioDadosPessoais buscar(Long id) {
        TypedQuery<UsuarioDadosPessoais> query = em.createQuery("select u from UsuarioDadosPessoais u where u.id = :id", UsuarioDadosPessoais.class);
        try {
            return query.setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UsuarioDadosPessoais buscar(String apelido) {
        TypedQuery<UsuarioDadosPessoais> query = em.createQuery("select u from UsuarioDadosPessoais u where u.apelido = :apelido", UsuarioDadosPessoais.class);
        try {
            return query.setParameter("apelido", apelido).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UsuarioDadosPessoais buscarPorEmail(String email) {
        TypedQuery<UsuarioDadosPessoais> query = em.createQuery("select u from UsuarioDadosPessoais u where u.email = :email", UsuarioDadosPessoais.class);
        try {
            return query.setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remover(Long id) {
        UsuarioDadosPessoais usuarioDadosPessoais = buscar(id);
        if (usuarioDadosPessoais.getId() == null) throw new RuntimeException("Usuário não cadastrado => ID " + id + "!");
        return remover(usuarioDadosPessoais);
    }

    @Override
    public boolean remover(UsuarioDadosPessoais usuarioDadosPessoais) {
        try {
            em.getTransaction().begin();
            em.remove(usuarioDadosPessoais);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean existe(Long id) {
        return buscar(id) != null;
    }

    @Override
    public boolean existe(String apelido) {
        return buscar( apelido ) != null;
    }

    @Override
    public boolean existePorEmail(String email) {
        return buscarPorEmail(email) != null;
    }

}