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
import java.util.HashSet;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.saloon.model.PersistenceManager;

import br.gov.sp.fatec.saloon.model.dao.interf.AlugavelDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ClienteDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.dao.interf.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.AlugavelTipoDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.ContratoMotivoDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.MesAnoDao;
import br.gov.sp.fatec.saloon.model.dao.stat.AlugavelTipoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.stat.ContratoMotivoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.stat.MesAnoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.AlugavelDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ClienteDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ParceiroDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ProprietarioDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
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
        ContratoMotivoDao contratoMotivoDao = new ContratoMotivoDaoJpa(em);

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

        clienteDao.cadastrarCliente("55555555555", "Cristina Regis Perrerrá","12", "98765431");
        clienteDao.cadastrarCliente("44444444444", "Maria das Dores"        ,"12", "98765422");
        clienteDao.cadastrarCliente("66666666666", "João José"              ,"11", "98865411");
        clienteDao.cadastrarCliente("99999999999", "Anacleto Beneditino"    ,"12", "98777777");

        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTE PARCEIRO ########################################", LARGURA, '#'));
        Parceiro parceiro = parceiroDao.cadastrarParceiro("BIFECASSIA"                          //apelido
                                                         ,"bifecassia@hotmail.com"              //email
                                                         ,"pwBC"                                //senha
                                                         ,"Bufe da Cássia"                      //nome
                                                         ,Data.toDate("15/05/1972")             //dtNascimento
                                                         ,"77777777777"                         //
                                                         ,Data.today());

        clienteDao.cadastrarCliente( parceiro.getCpf()          //cpf_cnpj
                                   , parceiro.getNome()         //nome
                                   , "12"                       //tel_ddd
                                   , "987654321"                //tel_numero
                                   , parceiro);

        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTES ATENDIDOS POR UM PARCEIRO ########################################", LARGURA, '#'));

        parceiro = parceiroDao.buscarParceiro("BIFECASSIA");
        parceiro.setClientes(new HashSet<Cliente>());
        parceiro.getClientes().add( clienteDao.buscarCliente("44444444444") );
        parceiro.getClientes().add( clienteDao.buscarCliente("99999999999") );

        System.out.println(Texto.padC("######################################## MOTIVOS DOS CONTRATOS ########################################", LARGURA, '#'));
        contratoMotivoDao.cadastrarContratoMotivo("Aniversário");
        contratoMotivoDao.cadastrarContratoMotivo("Bodas");
        contratoMotivoDao.cadastrarContratoMotivo("Casamento");
        contratoMotivoDao.cadastrarContratoMotivo("Confraternização");
        contratoMotivoDao.cadastrarContratoMotivo("Encontro de Amigos(as)");

        System.out.println(Texto.padC("######################################## CADASTRO DE MESES DO ANO ########################################", LARGURA, '#'));
        MesAnoDao mesAnoDao = new MesAnoDaoJpa(em);
        mesAnoDao.cadastrarMesAno("01", "Janeiro");
        mesAnoDao.cadastrarMesAno("02", "Fevereiro");
        mesAnoDao.cadastrarMesAno("03", "Março");
        mesAnoDao.cadastrarMesAno("04", "Abril");
        mesAnoDao.cadastrarMesAno("05", "Maio");
        mesAnoDao.cadastrarMesAno("06", "Junho");
        mesAnoDao.cadastrarMesAno("07", "Julho");
        mesAnoDao.cadastrarMesAno("08", "Agosto");
        mesAnoDao.cadastrarMesAno("09", "Setembro");
        mesAnoDao.cadastrarMesAno("10", "Outubro");
        mesAnoDao.cadastrarMesAno("11", "Novembro");
        mesAnoDao.cadastrarMesAno("12", "DEZEMBRO");

        MesAno mesAno = mesAnoDao.buscarMesAno("12");
        mesAno.setDescr("Dezembro");
        mesAnoDao.salvarMesAno(mesAno);

        



        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}