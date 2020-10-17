package br.gov.sp.fatec.saloon.model.dao.stat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.Generico;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.ContratoMotivoDao;
import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;

public class ContratoMotivoDaoJpa implements ContratoMotivoDao {

    private EntityManager em;

    // CONSTRUTORES
    public ContratoMotivoDaoJpa()                { this(PersistenceManager.getInstance().getEntityManager());  }
    public ContratoMotivoDaoJpa(EntityManager em){ this.em = em; }

    @Override
    public ContratoMotivo salvarContratoMotivo(ContratoMotivo contratoMotivo) {
        try {
            em.getTransaction().begin();
            Generico.salvarSemCommit( contratoMotivo , em );
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o Tipo do Tipo de Contrato" + (contratoMotivo.getId()==null ? "!" : " " + contratoMotivo.getDescr() + "!"), e);
        }
        return contratoMotivo;
    }

    @Override
    public ContratoMotivo cadastrarContratoMotivo(String descr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ContratoMotivo buscarContratoMotivo(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ContratoMotivo> buscarContratoMotivo(String descr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removerContratoMotivo(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removerContratoMotivo(ContratoMotivo contratoMotivo) {
        // TODO Auto-generated method stub
        return false;
    }
    
}