package br.gov.sp.fatec.saloon;

/**
 * @App Saloon 
 * @apiNote Teste exclusivo de MARCOS VINICIO PEREIRA
 *
 * Usar a expressão abaixo para executar pelo maven
 * mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

import java.text.ParseException;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.saloon.model.PersistenceManager;

import br.gov.sp.fatec.saloon.model.dao.interf.AlugavelDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ClienteDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.dao.interf.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.AlugavelTipoDao;
import br.gov.sp.fatec.saloon.model.dao.stat.AlugavelTipoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.AlugavelDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ClienteDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ParceiroDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ProprietarioDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;

import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.SaidasConsole;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class MvpTesteDesenvolvimento {

    static int LARGURA = 112;

    public static void run() throws ParseException {

        EntityManager em = PersistenceManager.getInstance().getEntityManager();
        UsuarioDadosPessoaisDao usuarioDadosPessoaisDao = new UsuarioDadosPessoaisDaoJpa(em);
        AlugavelDao alugavelDao = new AlugavelDaoJpa(em);
        AlugavelTipoDao alugavelTipoDao = new AlugavelTipoDaoJpa(em);
        ProprietarioDao proprietarioDao = new ProprietarioDaoJpa(em);
        ParceiroDao parceiroDao = new ParceiroDaoJpa(em);
        UsuarioDadosPessoais usuario;
        ClienteDao clienteDao = new ClienteDaoJpa(em);

        System.out.println(Texto.padC("######################################## INÍCIO ########################################", LARGURA, '#'));
        System.out.println(""); 

        System.out.println(Texto.padC("######################################## CADASTRO DE USUÁRIO ISOLADO ########################################", LARGURA, '#'));

        usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais
        (new UsuarioDadosPessoais("MVP"
                                 ,"mvp@fatec.sp.gov.br"
                                 ,"pwMVP"
                                 ,"Marcos Vinicio Pereira"
                                 ,Data.toDate("12/04/1969")
                                 ,"11111111111"
                                 )
        );

        usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais
        (new UsuarioDadosPessoais("RSD"
                                 ,"rsd@fatec.sp.gov.br"
                                 ,"pwRSD"
                                 ,"Raian Silva Damaceno"
                                 ,Data.toDate("12/04/1995")
                                 ,"22222222222"
                                 )
        );

        // Faz alteração da senha em um dos usuários
        usuario = usuarioDadosPessoaisDao.buscarUsuarioDadosPessoais("MVP");
        usuario.setSenha("senhaMVP");
        usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais( usuario );
        
        System.out.println(Texto.padC("######################################## CADASTRO DE PROPRIETARIO ########################################", LARGURA, '#'));
        
        proprietarioDao.salvarProprietario
        (new Proprietario("BETÃO"
                        , "alberto.salas@terra.com.br"
                        , "pwAS"
                        , "Alberto Salasar"
                        , Data.toDate("25/06/1970")
                        , "33333333333")
            
        );
        
        System.out.println(Texto.padC("######################################## CADASTRO DE PARCEIROS ########################################", LARGURA, '#'));
        
        parceiroDao.salvarParceiro
        (new Parceiro("Festeira"
                     , "festeira@hotmail.com"
                     , "pwTF"
                     , "Tereza Festeira"
                     , Data.toDate("31/12/1980")
                     , "55555555555")
            
        );

        parceiroDao.salvarParceiro
        (new Parceiro("SantaMaria"
                     , "agendamento@santa_maria.com.br"
                     , "pwBSM"
                     , "Maria dos Santos"
                     , Data.toDate("15/05/1972")
                     , "44444444444")
            
        );

        System.out.println(Texto.padC("######################################## CADASTRO DE TIPO DE ALUGÁVEL ########################################", LARGURA, '#'));
        alugavelTipoDao.cadastrarAlugavelTipo("Salão de Festas");
        alugavelTipoDao.cadastrarAlugavelTipo("Salão de Palestras");
        alugavelTipoDao.cadastrarAlugavelTipo("Salão de Reuniões");
        alugavelTipoDao.cadastrarAlugavelTipo("Sala de Aula");
        alugavelTipoDao.cadastrarAlugavelTipo("Salão de Festas");

        em.clear();

        System.out.println(Texto.padC("######################################## CADASTRO DE ALUGÁVEL ########################################", LARGURA, '#'));

        System.out.println(">>>>> " + proprietarioDao.buscarProprietario("BETÃO").getApelido());
        System.out.println(">>>>> " + em.find(AlugavelTipo.class,1L).getDescr());
        System.out.println(">>>>> " + alugavelTipoDao.buscarAlugavelTipo(1L).getDescr() + alugavelTipoDao.buscarAlugavelTipo(1L).getId());
        
        alugavelDao.cadastrarAlugavel("Salão Maior"
                                     ,proprietarioDao.buscarProprietario("BETÃO")
                                     ,alugavelTipoDao.buscarAlugavelTipo(1L)
                                     ,"Rua Ottoboni, 123 - Vila Industrial - São José dos campos"
                                     , 150
                                     , 600.00 );

        alugavelDao.cadastrarAlugavel("Salão Menor"
                                     ,proprietarioDao.buscarProprietario("BETÃO")
                                     ,alugavelTipoDao.buscarAlugavelTipo(1L)
                                     ,"Rua Ottoboni, 123 - Vila Industrial - São José dos campos"
                                     , 80
                                     , 450.00 );

        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTES ########################################", LARGURA, '#'));
        //clienteDao.cadastrarCliente("55555555555", "Cristina Regis Perrerrá","12", "98765431");

/*                                     
        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTE PARCEIRO ########################################", LARGURA, '#'));
        Parceiro parceiro = parceiroDao.cadastrarParceiro("TESTE_PARC2"                         //apelido
                                                         ,"testeparceirotempoario@hotmail.com"  //email
                                                         ,"pwTP"                                //senha
                                                         ,"Parceiro Temporário"                 //nome
                                                         ,Data.toDate("15/05/1972")             //dtNascimento
                                                         ,"77777777777"                         //
                                                         ,Data.today());

        
        Cliente cliente = clienteDao.cadastrarCliente(parceiro.getCpf(), parceiro.getNome(), "12", "998765432", parceiro);
        System.out.println(">>>>> Cliente ID  " + cliente.getId() );*/
/*
        System.out.println(">>>>> REMOVENDO PARCEIRO " );
        parceiroDao.removerParceiro(
            parceiroDao.buscarParceiro("TESTE_PARC2").getId());
        //System.out.println(">>>>> Parceiro " + parceiroDao.buscarParceiro("TESTE_PARC2").getId());
*/





        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}