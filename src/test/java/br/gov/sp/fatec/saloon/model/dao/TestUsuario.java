package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class TestUsuario {

    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    UsuarioDadosPessoaisDao usuarioDadosPessoaisDao = new UsuarioDadosPessoaisDaoJpa(em);

    static int LARGURA = 150;

    @Test
    public void testCadastrar() throws ParseException {

        System.out.println(Texto.padC("TESTE - Cadastrar Usuário", LARGURA, '+'));
        assertTrue(
        usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais
        (new UsuarioDadosPessoais("--TESTE"
                                 ,"teste_teste@fatec.sp.gov.br"
                                 ,"pwTESTE"
                                 ,"TESTE TESTE"
                                 ,Data.toDate("12/04/1969")
                                 ,"88888888888"
                                 )
        ).getApelido().equals("--TESTE") );

    }

    @Test
    public void testBuscar() throws ParseException {

        System.out.println(Texto.padC("TESTE - Buscar Usuário", LARGURA, '+'));
        UsuarioDadosPessoais usu = usuarioDadosPessoaisDao.buscarUsuarioDadosPessoais("--TESTE");
        assertTrue( usuarioDadosPessoaisDao.buscarUsuarioDadosPessoais("--TESTE").getApelido().equals("--TESTE") );

    }

}