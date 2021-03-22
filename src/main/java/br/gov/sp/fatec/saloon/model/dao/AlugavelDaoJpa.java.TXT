package br.gov.sp.fatec.saloon.model.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.saloon.model.PersistenceManager;

import br.gov.sp.fatec.saloon.model.dao.interf.AlugavelDao;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;

import br.gov.sp.fatec.saloon.model.tool.Texto;

public class AlugavelDaoJpa implements AlugavelDao {

    private EntityManager em;

    // CONSTRUTORES
    public AlugavelDaoJpa()                 { this(PersistenceManager.getInstance().getEntityManager());}
    public AlugavelDaoJpa(EntityManager em) { this.em = em;                                             }

    @Override
    public Alugavel salvar(Alugavel alugavel) {
        if (Parametro.lerLogico("PARAMETRO")) {
            try {
                em.getTransaction().begin();
                Generico.salvarSemCommit(alugavel, em);
                em.getTransaction().commit();
            } catch (PersistenceException e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                throw new RuntimeException("Erro ao salvar o Alugável"
                        + (alugavel.getId() == null ? "!" : " " + alugavel.getDescr() + "!"), e);
            }
        } else {
            em.getTransaction().begin();
            Generico.salvarSemCommit(alugavel, em);
            em.getTransaction().commit();
        }
        return alugavel;
    }

    @Override
    public Alugavel cadastrar(String        descr
                             ,Proprietario  proprietario
                             ,AlugavelTipo  alugavelTipo
                             ,String        endereco
                             ,int           capacidade
                             ,BigDecimal    valor) {

        return salvar(new Alugavel(descr, proprietario, alugavelTipo, endereco, capacidade, valor));
    }

    public Alugavel cadastrar(String descr
                             ,Proprietario proprietario
                             ,AlugavelTipo alugavelTipo
                             ,String endereco
                             ,int capacidade
                             ,double valor) {

        return cadastrar(descr, proprietario, alugavelTipo, endereco, capacidade, new BigDecimal(valor));
    }

    @Override
    public Alugavel buscar(Long id) {
        TypedQuery<Alugavel> query = em.createQuery("select a from Alugavel a where a.id = :id", Alugavel.class);
        try {
            return query.setParameter("id", id).getSingleResult(); 
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Alugavel> buscar(String apelidoProprietario) {
        String jpql = "select a from Alugavel a inner join a.proprietario p where p.apelido = :apelidoProprietario";
        TypedQuery<Alugavel> query = em.createQuery(jpql, Alugavel.class);
        try {
            return query.setParameter("apelidoProprietario", apelidoProprietario).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remover(Long id) {
        Alugavel alugavel = buscar(id);
        if (alugavel.getId() == null) throw new RuntimeException("Alugável não cadastrado => ID " + id + "!");
        return remover(alugavel);
    }

    @Override
    public boolean remover(Alugavel alugavel) {
        try {
            em.getTransaction().begin();
            em.remove(alugavel);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public void consoleAlugavelPorProprietario(String apelidoProprietario) {
        consoleAlugavelPorProprietario( em.find(Proprietario.class
                                      , new ProprietarioDaoJpa().buscar(apelidoProprietario)));
    }

    @Override
    public void consoleAlugavelPorProprietario(Proprietario proprietario) {
        System.out.println(Texto.padC(" LOG ALUGAVEL POR PROPRIETÁRIO ", 100,"#"));
        List<Alugavel> alugavel = buscar(proprietario.getApelido());
        alugavel.forEach(a -> System.out.println(a.getDescr() + " capacidade " + a.getCapacidade() + " valor " + a.getValor()));
    }

    @Override
    public boolean existe(Long id) {
        return buscar(id) != null;
    }
    
}