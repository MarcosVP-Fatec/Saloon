package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public class ParceiroDaoJpa implements ParceiroDao {

    private EntityManager em;

    // CONSTRUTORES
    public ParceiroDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ParceiroDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public Parceiro salvarParceiro(Parceiro parceiro) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( parceiro , em );
            /*
            Query query = em.createQuery("update Parceiro set _inc_usua = :usua, _inc_data = :data where par_usu_id = :id",Parceiro.class);
            query.setParameter("usua",UsuarioLogado.getInstance().getId());
            query.setParameter("data",Data.today());
            query.setParameter("id",parceiro.getId());
            query.executeUpdate();            
            */
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Parceiro" + (parceiro.getId()==null ? "!" : " " + parceiro.getNome() + "!"), e);
        }
        
        return parceiro;
    }

    @Override
    public Parceiro cadastrarParceiro(String apelido
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

        return salvarParceiro( prop );
    }

    @Override
    public Parceiro buscarParceiro(Long id) {
        String jpql = "select p from Parceiro p where p.id = :id";
        TypedQuery<Parceiro> query = em.createQuery(jpql, Parceiro.class);
        query.setParameter("id", id);
        return query.getSingleResult();    
    }

    @Override
    public Parceiro buscarParceiro(String apelido) {
        String jpql = "select p from Parceiro p where p.apelido = :apelido";
        TypedQuery<Parceiro> query = em.createQuery(jpql, Parceiro.class);
        query.setParameter("apelido", apelido);
        return query.getSingleResult();    
    }

    @Override
    public Parceiro buscarParceiroPorEmail(String email) {
        String jpql = "select p from Parceiro p  where p.email = :email";
        TypedQuery<Parceiro> query = em.createQuery(jpql, Parceiro.class);
        query.setParameter("email", email);
        return query.getSingleResult();    
    }

    @Override
    public boolean removerParceiro(Long id) {
        Parceiro parceiro = buscarParceiro(id);
        if (parceiro.getId() == null) throw new RuntimeException("Parceiro não cadastrado => ID " + id + "!");
        removerParceiro(parceiro);
        return true;
    }

    @Override
    public boolean removerParceiro(Parceiro parceiro) {
        em.getTransaction().begin();
        em.remove(parceiro);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean existe(Long id) {
        return buscarParceiro(id) != null;
    }

    @Override
    public boolean existe(String apelido) {
        return buscarParceiro(apelido) != null;
    }

    @Override
    public boolean existeEmail(String email) {
        return buscarParceiroPorEmail(email) != null;
    }
    
}