package br.gov.sp.fatec.saloon.model.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.entity.Proprietario;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class AlugavelDaoJpa implements AlugavelDao {

    private EntityManager em;

    // CONSTRUTORES
    public AlugavelDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public AlugavelDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Alugavel salvarAlugavel(Alugavel alugavel) {
            em.getTransaction().begin();
            Generico.salvarSemCommit(alugavel, em);
            em.getTransaction().commit();
        /*    
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit(alugavel, em);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Alugável" + (alugavel.getId() == null ? "!" : " " + alugavel.getDescr() + "!"),e);
        }*/
        return alugavel;
    }

    @Override
    public Alugavel cadastrarAlugavel(String descr
                                     ,Proprietario proprietario
                                     ,AlugavelTipo alugavelTipo
                                     ,String endereco
                                     ,int capacidade
                                     ,BigDecimal valor) {

        return salvarAlugavel(new Alugavel(descr, proprietario, alugavelTipo, endereco, capacidade, valor));
    }

    public Alugavel cadastrarAlugavel(String descr
                                     ,Proprietario proprietario
                                     ,AlugavelTipo alugavelTipo
                                     ,String endereco
                                     ,int capacidade
                                     ,double valor) {

        return cadastrarAlugavel(descr, proprietario, alugavelTipo, endereco, capacidade, new BigDecimal(valor));
    }

    @Override
    public Alugavel buscarAlugavel(Long id) {
        /*
         * String jpql = "select a from Alugavel a where a.id = :id";
         * TypedQuery<Alugavel> query = em.createQuery(jpql, Alugavel.class);
         * query.setParameter("id", id); return query.getSingleResult();
         */
        return em.find(Alugavel.class, id);
    }

    @Override
    public List<Alugavel> buscarAlugavel(String apelidoProprietario) {
        String jpql = "select a from Alugavel a inner join a.proprietario p where p.apelido = :apelidoProprietario";
        TypedQuery<Alugavel> query = em.createQuery(jpql, Alugavel.class);
        return query.setParameter("apelidoProprietario", apelidoProprietario).getResultList();
    }

    @Override
    public boolean removerAlugavel(Long id) {
        Alugavel alugavel = buscarAlugavel(id);
        if (alugavel.getId() == null) throw new RuntimeException("Alugável não cadastrado => ID " + id + "!");
        removerAlugavel(alugavel);
        return true;
    }

    @Override
    public boolean removerAlugavel(Alugavel alugavel) {
        em.getTransaction().begin();
        em.remove(alugavel);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public void consoleAlugavelPorProprietario(String apelidoProprietario) {
        consoleAlugavelPorProprietario( em.find(Proprietario.class
                                      , new ProprietarioDaoJpa().buscarProprietario(apelidoProprietario)));
    }

    @Override
    public void consoleAlugavelPorProprietario(Proprietario proprietario) {
        System.out.println(Texto.padC(" LOG ALUGAVEL POR PROPRIETÁRIO ", 100,"#"));
        List<Alugavel> alugavel = buscarAlugavel(proprietario.getApelido());
        alugavel.forEach(a -> System.out.println(a.getDescr() + " capacidade " + a.getCapacidade() + " valor " + a.getValor()));
    }
    
}