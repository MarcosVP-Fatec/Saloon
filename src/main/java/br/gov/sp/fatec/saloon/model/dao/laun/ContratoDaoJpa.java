package br.gov.sp.fatec.saloon.model.dao.laun;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.laun.ContratoDao;
import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;

public class ContratoDaoJpa implements ContratoDao {

    private EntityManager em;

    // CONSTRUTORES
    public ContratoDaoJpa()                 { this(PersistenceManager.getInstance().getEntityManager());}
    public ContratoDaoJpa(EntityManager em) { this.em = em;                                             }

    @Override
    public Contrato salvarContrato(Contrato contrato) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit(contrato, em);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Contrato de Aluguel" + (contrato.getId() == null ? "!" : " " + contrato.getId() + "!"),e);
        }
        return contrato;
    }

    @Override
    public Contrato cadastrarContrato( Cliente cliente
                                     , Alugavel alugavel
                                     , Date data
                                     , BigDecimal reservaPaga
                                     , ContratoMotivo contratoMotivo) {

        return salvarContrato(new Contrato( cliente
                                          , alugavel
                                          , data
                                          , reservaPaga
                                          , contratoMotivo));
    }

    @Override
    public Contrato cadastrarContrato( Cliente cliente
                                     , Alugavel alugavel
                                     , Date data
                                     , BigDecimal reservaPaga
                                     , ContratoMotivo contratoMotivo
                                     , String festejoNomes
                                     , int festejoDia
                                     , MesAno festejoMes) {
        
        return salvarContrato( new Contrato( cliente
                                           , alugavel
                                           , data
                                           , reservaPaga
                                           , contratoMotivo
                                           , festejoNomes
                                           , festejoDia
                                           , festejoMes));
    }

    @Override
    public Contrato buscarContrato(Long id) {
        TypedQuery<Contrato> query = em.createQuery("select c from Contrato c where c.id = :id", Contrato.class);
        return query.setParameter("id", id).getSingleResult(); 
    }

    @Override
    public List<Contrato> buscarContratosDoCliente(Cliente cliente) {
        String jpql = "select c from Contrato c inner join c.cliente d where d.cpf_cnpj = :cpf_cnpj";
        TypedQuery<Contrato> query = em.createQuery(jpql, Contrato.class);
        return query.setParameter("cpf_cnpj", cliente.getCpf_cnpj()).getResultList();
    }

    @Override
    public List<Contrato> buscarContrato(Alugavel alugavel, Cliente cliente) {
        String jpql = "select c from Contrato      c " +
                      "      inner join c.cliente  d " +
                      "      inner join c.alugavel a " +
                      " where d.cpf_cnpj = :cpf_cnpj " +
                      "   and a.id       = :id       " +
                      " order by c.data              ";
        TypedQuery<Contrato> query = em.createQuery(jpql, Contrato.class);
        query.setParameter("cpf_cnpj", cliente.getCpf_cnpj());
        query.setParameter("id", alugavel.getId());
        return query.getResultList();
    }

    @Override
    public boolean removerContrato(Long id) {
        Contrato contrato = buscarContrato(id);
        if (contrato.getId() == null) throw new RuntimeException("contrato não cadastrado => ID " + id + "!");
        removerContrato(contrato);
        return true;
    }

    @Override
    public boolean removerContrato(Contrato contrato) {
        em.getTransaction().begin();
        em.remove(contrato);
        em.getTransaction().commit();
        return true;
    }

   
}