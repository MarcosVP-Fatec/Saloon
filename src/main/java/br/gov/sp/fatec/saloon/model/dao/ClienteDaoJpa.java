package br.gov.sp.fatec.saloon.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ClienteDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public class ClienteDaoJpa implements ClienteDao {

    private EntityManager em;

    // CONSTRUTORES
    public ClienteDaoJpa()                 {this(PersistenceManager.getInstance().getEntityManager());}
    public ClienteDaoJpa(EntityManager em) {this.em = em;}

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        em.getTransaction().begin();
        Generico.salvarSemCommit(cliente, em);
        em.getTransaction().commit();
/*
        try {
        em.getTransaction().begin();
        Generico.salvarSemCommit(cliente, em);
        em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Cliente" + (cliente.getId() == null ? "!" : " " + cliente.getNome() + "!"),e);
        }*/
        return cliente;
    }

    @Override
    public Cliente cadastrarCliente( String cpf_cnpj
                                   , String nome
                                   , String tel_ddd
                                   , String tel_numero
                                   , Parceiro parceiro) {

        return salvarCliente(new Cliente(cpf_cnpj
                                        ,nome
                                        ,tel_ddd
                                        ,tel_numero
                                        ,parceiro));
    }

    @Override
    public Cliente cadastrarCliente( String cpf_cnpj
                                   , String nome
                                   , String tel_ddd
                                   , String tel_numero) {
        return salvarCliente(new Cliente(cpf_cnpj
                                        ,nome
                                        ,tel_ddd
                                        ,tel_numero));
    }

    @Override
    public Cliente cadastrarCliente(String cpf_cnpj, String nome) {
        return salvarCliente(new Cliente(cpf_cnpj, nome));
    }

    @Override
    public Cliente buscarCliente(Long id) {
        TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.id = :id", Cliente.class);
        return query.setParameter("id", id).getSingleResult(); 
    }

    @Override
    public Cliente buscarCliente(String cpf) {
        TypedQuery<Cliente> query = 
        em.createQuery("select c from Cliente c where c.cpf_cnpj = :cpf_cnpj",Cliente.class);
        return query.setParameter("cpf_cnpj", cpf).getSingleResult();
    }

    @Override
    public List<Cliente> buscarClientePorNome(String nome) {
        TypedQuery<Cliente> query = 
        em.createQuery("select c from Cliente c where c.nome like '%:nome%'",Cliente.class);
        return query.setParameter("nome", nome).getResultList();
    }

    @Override
    public Cliente buscarClienteParceiro(Long idParceiro) {
        String jpql = "select c from Cliente c inner join c.parceiro p where p.id = :idParceiro";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        return query.setParameter("idParceiro", idParceiro).getSingleResult();
    }
    
    @Override
    public boolean removerCliente(Long id) {
        Cliente cliente = buscarCliente(id);
        if (cliente.getId() == null) throw new RuntimeException("Cliente não cadastrado => ID " + id + "!");
        removerCliente(cliente);
        return true;
    }

    @Override
    public boolean removerCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().rollback();        
        return true;
    }

}