package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Padrao;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class Test001_Usuario {

    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    UsuarioDadosPessoaisDao usuarioDadosPessoaisDao = new UsuarioDadosPessoaisDaoJpa(em);
    UsuarioDadosPessoais usuario;

    @Test
    public void testCadastrar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Cadastrar Usuário ", Padrao.larguraTela(), '+'));
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
    public void test001_Buscar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Buscar Usuário ", Padrao.larguraTela(), '+'));
        usuario = usuarioDadosPessoaisDao.buscarUsuarioDadosPessoais("--TESTE");
        assertTrue( usuario.getApelido().equals("--TESTE") );

    }

    @Test
    public void test001_Update() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Update da senha do Usuário ", Padrao.larguraTela(), '+'));
        usuario.setSenha("NovaSenha");
        usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais(usuario);
        assertTrue(usuario.getSenha().equals("NovaSenha"));
        usuario = null;

    }

    @Test
    public void test001_Excluir() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Excluir Usuário ", Padrao.larguraTela(), '+'));
        assertTrue( usuarioDadosPessoaisDao.removerUsuarioDadosPessoais(
            usuarioDadosPessoaisDao.buscarUsuarioDadosPessoais("--TESTE").getId()
        ) );

    }

}