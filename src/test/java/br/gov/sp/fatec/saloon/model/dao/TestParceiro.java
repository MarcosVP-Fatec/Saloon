package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.Parceiro;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Padrao;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class TestParceiro {

    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    ParceiroDao parceiroDao = new ParceiroDaoJpa(em);
    Parceiro parceiro;

    @Test
    public void testCadastrar() throws ParseException {

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
    public void testBuscar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Buscar Parceiro ", Padrao.larguraTela(), '+'));
        parceiro = parceiroDao.buscarParceiro("--TESTEPARC");
        assertTrue( parceiro.getApelido().equals("--TESTEPARC") );

    }

    @Test
    public void testUpdate() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Update da senha do Parceiro ", Padrao.larguraTela(), '+'));
        parceiro.setSenha("NovaSenha");
        parceiroDao.salvarParceiro(parceiro);
        assertTrue(parceiro.getSenha().equals("NovaSenha"));
        parceiro = null;

    }

    @Test
    public void testExcluir() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Excluir Parceiro ", Padrao.larguraTela(), '+'));
        assertTrue( parceiroDao.removerParceiro(
            parceiroDao.buscarParceiro("--TESTEPARC").getId()
        ) );

    }

}