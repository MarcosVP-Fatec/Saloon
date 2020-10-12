package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;

public class UsuarioDadosPessoaisDaoJpa implements UsuarioDadosPessoaisDao {

    private EntityManager em;

    // CONSTRUTORES
    public UsuarioDadosPessoaisDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public UsuarioDadosPessoaisDaoJpa(EntityManager em){ this.em = em; }


    @Override
    public void salvarUsuarioDadosPessoaisSemCommit(UsuarioDadosPessoais usuarioDadosPessoais) {
        if (usuarioDadosPessoais.getId() == null)   em.persist(usuarioDadosPessoais);
        else                                        em.merge(usuarioDadosPessoais);
    }

    @Override
    public UsuarioDadosPessoais salvarUsuarioDadosPessoais(UsuarioDadosPessoais usuarioDadosPessoais) {
        try {
            em.getTransaction().begin();
            salvarUsuarioDadosPessoaisSemCommit(usuarioDadosPessoais);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Usuário" + (usuarioDadosPessoais.getId()==null ? "!" : " " + usuarioDadosPessoais.getNome() + "!"), e);
        }
        
        return usuarioDadosPessoais;
    }

    @Override
    public UsuarioDadosPessoais cadastrarUsuarioDadosPessoais(String apelido
                                                             ,String email
                                                             ,String senha
                                                             ,String nome
                                                             ,Date dtNascimento
                                                             ,String cpf) {

        return salvarUsuarioDadosPessoais( new UsuarioDadosPessoais(apelido
                                                                   , email
                                                                   , senha
                                                                   , nome
                                                                   , dtNascimento
                                                                   , cpf) );

    }

    @Override
    public UsuarioDadosPessoais buscarUsuarioDadosPessoais(Long id) {
        String jpql = "select u from UsuarioDadosPessoais u where u.id = :id";
        TypedQuery<UsuarioDadosPessoais> query = em.createQuery(jpql, UsuarioDadosPessoais.class);
        query.setParameter("id", id);
        return query.getSingleResult();    
    }

    @Override
    public UsuarioDadosPessoais buscarUsuarioDadosPessoais(String apelido) {
        String jpql = "select u from UsuarioDadosPessoais u where u.apelido = :apelido";
        TypedQuery<UsuarioDadosPessoais> query = em.createQuery(jpql, UsuarioDadosPessoais.class);
        query.setParameter("apelido", apelido);
        return query.getSingleResult();    
    }

    @Override
    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorEmail(String email) {
        String jpql = "select u from UsuarioDadosPessoais u where u.email = :email";
        TypedQuery<UsuarioDadosPessoais> query = em.createQuery(jpql, UsuarioDadosPessoais.class);
        query.setParameter("email", email);
        return query.getSingleResult();    
    }

    @Override
    public void removerUsuarioDadosPessoais(Long id) {
        UsuarioDadosPessoais usuarioDadosPessoais = buscarUsuarioDadosPessoais(id);
        if (usuarioDadosPessoais.getId() == null) throw new RuntimeException("Usuário não cadastrado => ID " + id + "!");
        removerUsuarioDadosPessoais(id);
    }

    @Override
    public void removerUsuarioDadosPessoais(UsuarioDadosPessoais usuarioDadosPessoais) {
        em.getTransaction().begin();
        em.remove(usuarioDadosPessoais);
        em.getTransaction().commit();
    }

}