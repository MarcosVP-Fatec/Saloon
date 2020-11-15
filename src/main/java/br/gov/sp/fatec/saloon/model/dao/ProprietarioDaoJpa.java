package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.tool.Data;

public class ProprietarioDaoJpa implements ProprietarioDao {

    private EntityManager em;

    // CONSTRUTORES
    public ProprietarioDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ProprietarioDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public Proprietario salvarProprietario(Proprietario proprietario) {
            em.getTransaction().begin();
            Generico.salvarSemCommit( proprietario , em );
            em.getTransaction().commit();
/*
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( proprietario , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Proprietário" + (proprietario.getId()==null ? "!" : " " + proprietario.getNome() + "!"), e);
        }*/
        return proprietario;
    }

    @Override
    public Proprietario cadastrarProprietario(String apelido
                                            , String email
                                            , String senha
                                            , String nome
                                            , Date dtNascimento
                                            , String cpf
                                            , Date dtInicio) {

        Proprietario prop = new Proprietario( apelido
                                            , email
                                            , senha
                                            , nome
                                            , dtNascimento
                                            , cpf);
        prop.setDtInicio( dtInicio );                                                

        return salvarProprietario( prop );
    }

    @Override
    public Proprietario cadastrarProprietario(String apelido
                                            , String email
                                            , String senha
                                            , String nome
                                            , Date dtNascimento
                                            , String cpf) {

        return cadastrarProprietario( apelido
                                    , email
                                    , senha
                                    , nome
                                    , dtNascimento
                                    , cpf
                                    , Data.today());
    }

    @Override
    public Proprietario buscarProprietario(Long id) {
        Proprietario retorno;
        String jpql = "select p from Proprietario p where p.id = :id";
        TypedQuery<Proprietario> query = em.createQuery(jpql, Proprietario.class);
        query.setParameter("id", id);
        try {
            retorno = query.getSingleResult();    
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public Proprietario buscarProprietario(String apelido) {
        Proprietario retorno;
        String jpql = "select p from Proprietario p where p.apelido = :apelido";
        TypedQuery<Proprietario> query = em.createQuery(jpql, Proprietario.class);
        query.setParameter("apelido", apelido);
        try {
            retorno = query.getSingleResult();    
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public Proprietario buscarProprietarioPorEmail(String email) {
        Proprietario retorno;
        String jpql = "select p from Proprietario p  where p.email = :email";
        TypedQuery<Proprietario> query = em.createQuery(jpql, Proprietario.class);
        query.setParameter("email", email);
        try {
            retorno = query.getSingleResult();    
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public boolean removerProprietario(Long id) {
        Proprietario proprietario = buscarProprietario(id);
        if (proprietario.getId() == null) throw new RuntimeException("Proprietário não cadastrado => ID " + id + "!");
        removerProprietario(proprietario);
        return true;
    }

    @Override
    public boolean removerProprietario(Proprietario proprietario) {
        em.getTransaction().begin();
        em.remove(proprietario);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean existe(Long id) {
        return buscarProprietario(id) != null;
    }

    @Override
    public boolean existe(String apelido) {
        return buscarProprietario(apelido) != null;
    }

    @Override
    public boolean existeEmail(String email) {
        return buscarProprietarioPorEmail(email) != null;
    }
    
}