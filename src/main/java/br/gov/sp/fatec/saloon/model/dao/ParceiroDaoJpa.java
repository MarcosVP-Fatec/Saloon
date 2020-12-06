package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public class ParceiroDaoJpa implements ParceiroDao {

    private EntityManager em;

    // CONSTRUTORES
    public ParceiroDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ParceiroDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public Parceiro salvar(Parceiro parceiro) {
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit( parceiro , em );
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException(
                        "Erro ao salvar o Parceiro" + (parceiro.getId()==null ? "!" : " " + parceiro.getNome() + "!"),
                        e);
            }            
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit( parceiro , em );
            em.getTransaction().commit();
        }

        return parceiro;
    }

    @Override
    public Parceiro cadastrar(String apelido
                            , String email
                            , String senha
                            , String nome
                            , Date dtNascimento
                            , String cpf
                            , Date dtInicio) {

        Parceiro prop = new Parceiro( apelido
                                    , email
                                    , senha
                                    , nome
                                    , dtNascimento
                                    , cpf);
        prop.setDtInicio( dtInicio );                                                

        return salvar( prop );
    }

    @Override
    public Parceiro buscar(Long id) {
        String jpql = "select p from Parceiro p where p.id = :id";
        TypedQuery<Parceiro> query = em.createQuery(jpql, Parceiro.class);
        query.setParameter("id", id);
        return query.getSingleResult();    
    }

    @Override
    public Parceiro buscar(String apelido) {
        String jpql = "select p from Parceiro p where p.apelido = :apelido";
        TypedQuery<Parceiro> query = em.createQuery(jpql, Parceiro.class);
        query.setParameter("apelido", apelido);
        return query.getSingleResult();    
    }

    @Override
    public Parceiro buscarPorEmail(String email) {
        String jpql = "select p from Parceiro p  where p.email = :email";
        TypedQuery<Parceiro> query = em.createQuery(jpql, Parceiro.class);
        query.setParameter("email", email);
        return query.getSingleResult();    
    }

    @Override
    public boolean remover(Parceiro parceiro) {
        try {
            em.remove(parceiro);
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