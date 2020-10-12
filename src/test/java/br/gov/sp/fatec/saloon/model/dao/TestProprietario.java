package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.Proprietario;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class TestProprietario {

    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    ProprietarioDao proprietarioDao = new ProprietarioDaoJpa(em);
    Proprietario proprietario;

    static int LARGURA = 150;

    @Test
    public void testCadastrar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Cadastrar Proprietário ", LARGURA, '+'));
        assertTrue(
        proprietarioDao.salvarProprietario
        (new Proprietario("--TESTEPROP"
                         ,"teste_teste_prop@fatec.sp.gov.br"
                         ,"pwTESTE"
                         ,"TESTE_PROP"
                         ,Data.toDate("12/04/1969")
                         ,"88888888888"
                         )
        ).getApelido().equals("--TESTEPROP") );

    }

    @Test
    public void testBuscar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Buscar Proprietário ", LARGURA, '+'));
        proprietario = proprietarioDao.buscarProprietario("--TESTEPROP");
        assertTrue( proprietario.getApelido().equals("--TESTEPROP") );

    }

    @Test
    public void testUpdate() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Update da senha do Proprietário ", LARGURA, '+'));
        proprietario.setSenha("NovaSenha");
        proprietarioDao.salvarProprietario(proprietario);
        assertTrue(proprietario.getSenha().equals("NovaSenha"));
        proprietario = null;

    }

    @Test
    public void testExcluir() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Excluir Proprietário ", LARGURA, '+'));
        assertTrue( proprietarioDao.removerProprietario(
            proprietarioDao.buscarProprietario("--TESTEPROP").getId()
        ) );

    }
    
}