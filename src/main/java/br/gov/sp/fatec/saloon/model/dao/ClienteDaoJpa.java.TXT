package br.gov.sp.fatec.saloon.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ClienteDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public class ClienteDaoJpa implements ClienteDao {

    private EntityManager em;

    // CONSTRUTORES
    public ClienteDaoJpa()                 {this(PersistenceManager.getInstance().getEntityManager());}
    public ClienteDaoJpa(EntityManager em) {this.em = em;}

    @Override
    public Cliente salvar(Cliente cliente) {
        /*
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit(cliente, em);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException(
                        "Erro ao salvar o Cliente" + (cliente.getId() == null ? "!" : " " + cliente.getNome() + "!"),
                        e);
            }
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit(cliente, em);
            em.getTransaction().commit();
        }
        */
        return cliente;
    }

    @Override
    public Cliente cadastrar( String cpf_cnpj
                            , String nome
                            , String tel_ddd
                            , String tel_numero
                            , Parceiro parceiro) {

        return salvar(new Cliente(cpf_cnpj
                                 ,nome
                                 ,tel_ddd
                                 ,tel_numero
                                 ,parceiro));
    }

    @Override
    public Cliente cadastrar( String cpf_cnpj
                            , String nome
                            , String tel_ddd
                            , String tel_numero) {
        return salvar(new Cliente(cpf_cnpj
                                 ,nome
                                 ,tel_ddd
                                 ,tel_numero));
    }

    @Override
    public Cliente cadastrar(String cpf_cnpj, String nome) {
        return salvar(new Cliente(cpf_cnpj, nome));
    }

    @Override
    public Cliente buscar(Long id) {
        Cliente retorno;
        TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.id = :id", Cliente.class);
        try {
            retorno = query.setParameter("id", id).getSingleResult(); 
        } catch (Exception e) {
            return null;
        }
        return retorno;
    }

    @Override
    public Cliente buscar(String cpf) {
        TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.cpf_cnpj = :cpf_cnpj",Cliente.class);
        try {
            return query.setParameter("cpf_cnpj", cpf).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        TypedQuery<Cliente> query = 
        em.createQuery("select c from Cliente c where c.nome like '%:nome%'",Cliente.class);
        try {
            return query.setParameter("nome", nome).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cliente buscarParceiro(Long idParceiro) {
        TypedQuery<Cliente> query = em.createQuery("select c from Cliente c inner join c.parceiro p where p.id = :idParceiro", Cliente.class);
        try {
            return query.setParameter("idParceiro", idParceiro).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public boolean remover(Long id) {
        Cliente cliente = buscar(id);
        if (cliente.getId() == null) throw new RuntimeException("Cliente não cadastrado => ID " + id + "!");
        return remover(cliente);
    }

    @Override
    public boolean remover(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.remove(cliente);
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
    public boolean existe(String cpf) {
        return buscar(cpf) != null;
    }

}