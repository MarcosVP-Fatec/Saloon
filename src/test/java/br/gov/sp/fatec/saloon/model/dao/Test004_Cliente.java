package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.entity.Cliente;
import br.gov.sp.fatec.saloon.model.entity.Parceiro;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Padrao;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class Test004_Cliente {
 
    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    ClienteDao clienteDao = new ClienteDaoJpa(em);
    ParceiroDao parceiroDao = new ParceiroDaoJpa(em);
    Cliente cliente;

    @Test
    public void test004_IncluirParceiroTemporario(){
        System.out.println(Texto.padC("CLIENTE - Inclusão de Parceiro Temporário", Padrao.larguraTela(),"+"));
        assertTrue( parceiroDao.cadastrarParceiro("PARCEIROCLI"
                                                  ,"testeparceirocliente@fatec.sp.gov.br"
                                                  ,"pwpccli"
                                                  ,"Parceiro Teste para Cliente"
                                                  ,Data.today()   //Data.toDate("17/07/1995")
                                                  ,"77777777777"
                                                  ,Data.today()).getSenha().equals("pwpccli"));
    }

    @Test
    public void exclui004_RemoveParceiroTemporario(){

        System.out.println(Texto.padC("CLIENTE - Remoção de Parceiro Temporário", Padrao.larguraTela(),"+"));
        Parceiro parceiro = parceiroDao.buscarParceiroPorEmail("testeparceirocliente@fatec.sp.gov.br");
        assertTrue(parceiroDao.removerParceiro(parceiro));
        
        //new UsuarioDadosPessoaisDaoJpa().removerUsuarioDadosPessoais(id);
    }


/*
    @Test
    public void test004_Cadastrar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Cadastrar Cliente ", Padrao.larguraTela(), '+'));
        
        assertTrue(
        clienteDao.cadastrarCliente( "77777777777"
                                   , "Parceiro teste cliente"
                                   , "12"
                                   , "123456789"
                                   , parceiro

        ).getNome().equals("Parceiro teste cliente") );

    }

    @Test
    public void test004_Buscar() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Buscar Cliente ", Padrao.larguraTela(), '+'));
        cliente = clienteDao.buscarCliente("77777777777");
        assertTrue( cliente.getCpf_cnpj().equals("77777777777") );

    }

    @Test
    public void test004_Update() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Update do Telefone do Cliente ", Padrao.larguraTela(), '+'));
        cliente.setTel_numero("98765432");
        clienteDao.salvarCliente(cliente);
        assertTrue(cliente.getTel_numero().equals("98765432"));
        cliente = null;

    }

    @Test
    public void test004_Excluir() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Excluir Cliente ", Padrao.larguraTela(), '+'));
        assertTrue( clienteDao.removerCliente(
            clienteDao.buscarCliente("77777777777").getId()
        ) );

    }
  */  
}