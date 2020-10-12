package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.Proprietario;

public class ProprietarioDaoJpa implements ProprietarioDao {

    private EntityManager em;

    // CONSTRUTORES
    public ProprietarioDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ProprietarioDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public Proprietario salvarProprietario(Proprietario proprietario) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( proprietario , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Proprietário" + (proprietario.getId()==null ? "!" : " " + proprietario.getNome() + "!"), e);
        }
        
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
    public Proprietario buscarProprietario(Long id) {
        String jpql = "select p from Proprietario p where p.id = :id";
        TypedQuery<Proprietario> query = em.createQuery(jpql, Proprietario.class);
        query.setParameter("id", id);
        return query.getSingleResult();    
    }

    @Override
    public Proprietario buscarProprietario(String apelido) {
        String jpql = "select p from Proprietario p where p.apelido = :apelido";
        TypedQuery<Proprietario> query = em.createQuery(jpql, Proprietario.class);
        query.setParameter("apelido", apelido);
        return query.getSingleResult();    
    }

    @Override
    public Proprietario buscarProprietarioPorEmail(String email) {
        String jpql = "select p from Proprietario p  where p.email = :email";
        TypedQuery<Proprietario> query = em.createQuery(jpql, Proprietario.class);
        query.setParameter("email", email);
        return query.getSingleResult();    
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
    
}