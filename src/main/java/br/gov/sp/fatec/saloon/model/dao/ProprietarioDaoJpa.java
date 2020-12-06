package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.tool.Data;

public class ProprietarioDaoJpa implements ProprietarioDao {

    private EntityManager em;

    // CONSTRUTORES
    public ProprietarioDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ProprietarioDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public Proprietario salvar(Proprietario proprietario) {
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit( proprietario , em );
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException(
                        "Erro ao salvar o Proprietário" + (proprietario.getId()==null ? "!" : " " + proprietario.getNome() + "!"),
                        e);
            }            
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit( proprietario , em );
            em.getTransaction().commit();
        }

        return proprietario;
    }

    @Override
    public Proprietario cadastrar(String apelido
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

        return salvar( prop );
    }

    @Override
    public Proprietario cadastrar(String apelido
                                , String email
                                , String senha
                                , String nome
                                , Date dtNascimento
                                , String cpf) {

        return cadastrar( apelido
                        , email
                        , senha
                        , nome
                        , dtNascimento
                        , cpf
                        , Data.today());
    }

    @Override
    public Proprietario buscar(Long id) {
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
    public Proprietario buscar(String apelido) {
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
    public Proprietario buscarPorEmail(String email) {
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
    public List<Proprietario> buscar() {
        List<Proprietario> retorno;
        TypedQuery<Proprietario> query = em.createQuery("select p from Proprietario p", Proprietario.class);
        try {
            retorno = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public boolean remover(Proprietario proprietario) {
        try {
            em.remove(proprietario);
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
    public boolean remover(String apelido) {
        return remover(buscar(apelido));
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
        return buscarPorEmail(email) != null;
    }
    
}