package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Padrao;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class Test002_Parceiro {

    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    ParceiroDao parceiroDao = new ParceiroDaoJpa(em);
    Parceiro parceiro;

    @Test
    public void test002_Cadastrar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Cadastrar Parceiro ", Padrao.larguraTela(), '+'));
        assertTrue(
        parceiroDao.salvarParceiro
        (new Parceiro("--TESTEPARC"
                     ,"teste_teste_pparcfatec.sp.gov.br"
                     ,"pwTESTE"
                     ,"TESTE_PARC"
                     ,Data.toDate("12/04/1969")
                     ,"88888888888"
                     )
        ).getApelido().equals("--TESTEPARC") );

    }

    @Test
    public void test002_Buscar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Buscar Parceiro ", Padrao.larguraTela(), '+'));
        parceiro = parceiroDao.buscarParceiro("--TESTEPARC");
        assertTrue( parceiro.getApelido().equals("--TESTEPARC") );

    }

    @Test
    public void test002_Update() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Update da senha do Parceiro ", Padrao.larguraTela(), '+'));
        parceiro.setSenha("NovaSenha");
        parceiroDao.salvarParceiro(parceiro);
        assertTrue(parceiro.getSenha().equals("NovaSenha"));
        parceiro = null;

    }

    @Test
    public void test002_Excluir() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Excluir Parceiro ", Padrao.larguraTela(), '+'));
        assertTrue( parceiroDao.removerParceiro(
            parceiroDao.buscarParceiro("--TESTEPARC").getId()
        ) );

    }

}