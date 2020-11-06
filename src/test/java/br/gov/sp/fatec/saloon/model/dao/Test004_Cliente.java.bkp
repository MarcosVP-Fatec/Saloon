package br.gov.sp.fatec.saloon.model.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;

import com.google.protobuf.TextFormat.ParseException;

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
    public void test004_IncluirParceiroTemporario() throws ParseException, java.text.ParseException {
        System.out.println(Texto.padC("CLIENTE - Inclusão de Parceiro Temporário do Cliente", Padrao.larguraTela(),"+"));
        assertTrue(
        parceiroDao.cadastrarParceiro("PARCTEMP2"                           //apelido
                                     ,"parctemp2@hotmail.com"               //email
                                     ,"pwPT2"                               //senha
                                     ,"Buffet do Parceiro 2"                //nome
                                     ,Data.toDate("25/12/1982")             //dtNascimento
                                     ,"77777777777"                         //
                                     ,Data.today()
        ).getApelido().equals("PARCTEMP2"));
    }

    @Test
    public void test004_Cadastrar(){

        System.out.println(Texto.padC(" TESTE - Cadastrar CLIENTE com PARCEIRO ", Padrao.larguraTela(), '+'));
        Parceiro parceiro = parceiroDao.buscarParceiro("PARCTEMP2");
        
        assertTrue(
        clienteDao.cadastrarCliente( parceiro.getCpf()          //cpf_cnpj
                                   , parceiro.getNome()         //nome
                                   , "12"                       //tel_ddd
                                   , "987654321"                //tel_numero
                                   , parceiro
        ).getNome().equals("Buffet do Parceiro 2"));

    }

    @Test
    public void test004_Update() throws ParseException {

        System.out.println(Texto.padC(" TESTE - Update do Telefone do Cliente x Parceiro", Padrao.larguraTela(), '+'));
        Cliente cliente = clienteDao.buscarCliente("77777777777");
        cliente.setTelNumero("999999999");
        clienteDao.salvarCliente(cliente);
        assertTrue(cliente.getTelNumero().equals("999999999"));
        cliente = null;
    }

    @Test
    public void test004_RemoverClienteComParceiroTemporario(){
        System.out.println(Texto.padC("CLIENTE - Remoção de Cliente com Parceiro Temporário", Padrao.larguraTela()+50,"+"));
        Cliente cliente = clienteDao.buscarCliente("77777777777");
        cliente.setParceiro(null);
        assertTrue(clienteDao.removerCliente(cliente));
    }    

    @Test
    public void test004_RemoverParceiroTemporario(){
        System.out.println(Texto.padC("CLIENTE - Remoção de Parceiro Temporário", Padrao.larguraTela(),"+"));
        assertTrue(parceiroDao.removerParceiro(parceiroDao.buscarParceiro("PARCTEMP2")));
    }    

}