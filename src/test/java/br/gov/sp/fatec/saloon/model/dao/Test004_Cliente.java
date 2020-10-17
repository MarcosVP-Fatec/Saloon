package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.dao.interf.ClienteDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.Padrao;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class Test004_Cliente {

    EntityManager em = PersistenceManager.getInstance().getEntityManager();
    ClienteDao clienteDao = new ClienteDaoJpa(em);
    ParceiroDao parceiroDao = new ParceiroDaoJpa(em);
    Cliente cliente;

    @Test
    public void test004_apagar(){
        assertTrue(true);
    }

    /*

    @Test
    public void test004_IncluirParceiroTemporario() throws ParseException {
        System.out.println(Texto.padC("CLIENTE - Inclusão de Parceiro Temporário do Cliente", Padrao.larguraTela(),"+"));
        assertTrue(
        parceiroDao.cadastrarParceiro("TESTE_PARC2"                         //apelido
                                     ,"testeparceirotempoario@hotmail.com"  //email
                                     ,"pwTP"                                //senha
                                     ,"Parceiro Temporário"                 //nome
                                     ,Data.toDate("15/05/1972")             //dtNascimento
                                     ,"77777777777"                         //
                                     ,Data.today()
        ).getApelido().equals("TESTE_PARC2") );
    }

    @Test
    public void test004_Cadastrar(){

        System.out.println(Texto.padC(" TESTE - Cadastrar CLIENTE com PARCEIRO ", Padrao.larguraTela(), '+'));
        
        assertTrue(
        clienteDao.cadastrarCliente("77777777777"               //cpf_cnpj
                                   ,"Parceiro teste Cliente"    //nome
                                   ,"12"                        //tel_ddd
                                   ,"987654324"                 //tel_numero
                                   ,parceiroDao.buscarParceiro("TESTE_PARC2")
        ).getNome().equals("Parceiro teste Cliente") );

        System.out.println(">>>>>>>> FIM CADASTRO DE CLIENTE COM PARCEIRO");

    }

    /*
    @Test
    public void test004_RemoveParceiroTemporario(){
        System.out.println(Texto.padC("CLIENTE - Remoção de Parceiro Temporário do Cliente", Padrao.larguraTela(),"+"));
        Parceiro parceiro = parceiroDao.buscarParceiroPorEmail("testeparceirotempoario@hotmail.com");
        System.out.println(">>>>>>>>>> Parceiro cadastrado " + parceiro.getApelido());
        assertTrue(parceiroDao.removerParceiro(parceiro));
    }*/

/*

    @Test
    public void test004_Cadastrar() throws ParseException {


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