package br.gov.sp.fatec.saloon;

import java.math.BigDecimal;

/**
 * @App Saloon 
 * @apiNote Teste exclusivo de MARCOS VINICIO PEREIRA
 *
 * Usar a expressão abaixo para executar pelo maven
 * mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.saloon.model.PersistenceManager;

import br.gov.sp.fatec.saloon.model.dao.interf.AlugavelDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ClienteDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ParceiroDao;
import br.gov.sp.fatec.saloon.model.dao.interf.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.dao.interf.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.dao.interf.laun.ContratoDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.AlugavelTipoDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.ContratoMotivoDao;
import br.gov.sp.fatec.saloon.model.dao.interf.stat.MesAnoDao;
import br.gov.sp.fatec.saloon.model.dao.laun.ContratoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.stat.AlugavelTipoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.stat.ContratoMotivoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.stat.MesAnoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.AlugavelDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ClienteDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ParametroDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ParceiroDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ProprietarioDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;
import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
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
        ContratoDao contratoDao = new ContratoDaoJpa(em);
        ParametroDaoJpa parametroDao = new ParametroDaoJpa(em);

        System.out.println(Texto.padC("######################################## INÍCIO ########################################", LARGURA, '#'));
        System.out.println(""); 

        System.out.println(Texto.padC("######################################## CADASTRO DE PARÃMETROS ########################################", LARGURA, '#'));
        parametroDao.cadastrar("PRODUCAO", "Identifica que a aplicação está rodando em modo de produção", false);

        System.out.println(Texto.padC("######################################## CADASTRO DE USUÁRIO ISOLADO ########################################", LARGURA, '#'));

        if (!usuarioDadosPessoaisDao.existe("MVP")) {
            usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais
            (new UsuarioDadosPessoais("MVP"
                                    ,"mvp@fatec.sp.gov.br"
                                    ,"pwMVP"
                                    ,"Marcos Vinicio Pereira"
                                    ,Data.toDate("12/04/1969")
                                    ,"11111111111"
                                    ,1L)
            );
        } 

        if (!usuarioDadosPessoaisDao.existe("RSD")) {
            usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais
            (new UsuarioDadosPessoais("RSD"
                                    ,"rsd@fatec.sp.gov.br"
                                    ,"pwRSD"
                                    ,"Raian Silva Damaceno"
                                    ,Data.toDate("12/04/1995")
                                    ,"22222222222"
                                    ,2L)
            );
        }

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Faz alteração da senha em um dos usuários
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
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
        
        alugavelDao.cadastrar("Salão Maior"
                             ,proprietarioDao.buscarProprietario("BETÃO")
                             ,alugavelTipoDao.buscarAlugavelTipo(1L)
                             ,"Rua Ottoboni, 123 - Vila Industrial - São José dos campos"
                             , 150
                             , 600.00 );

        alugavelDao.cadastrar("Salão Menor"
                             ,proprietarioDao.buscarProprietario("BETÃO")
                             ,alugavelTipoDao.buscarAlugavelTipo(1L)
                             ,"Rua Ottoboni, 123 - Vila Industrial - São José dos campos"
                             , 80
                             , 450.00 );

        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTES ########################################", LARGURA, '#'));

        clienteDao.cadastrar("55555555555", "Cristina Regis Perrerrá","12", "98765431");
        clienteDao.cadastrar("44444444444", "Maria das Dores"        ,"12", "98765422");
        clienteDao.cadastrar("66666666666", "João José"              ,"11", "98865411");
        clienteDao.cadastrar("99999999999", "Anacleto Beneditino"    ,"12", "98777777");

        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTE PARCEIRO ########################################", LARGURA, '#'));
        Parceiro parceiro = parceiroDao.cadastrarParceiro("BIFECASSIA"                          //apelido
                                                         ,"bifecassia@hotmail.com"              //email
                                                         ,"pwBC"                                //senha
                                                         ,"Bufe da Cássia"                      //nome
                                                         ,Data.toDate("15/05/1972")             //dtNascimento
                                                         ,"77777777777"                         //
                                                         ,Data.today());

        clienteDao.cadastrar( parceiro.getCpf()          //cpf_cnpj
                            , parceiro.getNome()         //nome
                            , "12"                       //tel_ddd
                            , "987654321"                //tel_numero
                            , parceiro);

        System.out.println(Texto.padC("######################################## CADASTRO DE CLIENTES ATENDIDOS POR UM PARCEIRO ########################################", LARGURA, '#'));

        parceiro = parceiroDao.buscarParceiro("BIFECASSIA");
        parceiro.setClientes(new HashSet<Cliente>());
        parceiro.getClientes().add( clienteDao.buscar("44444444444") );
        parceiro.getClientes().add( clienteDao.buscar("99999999999") );

        System.out.println(Texto.padC("######################################## MOTIVOS DOS CONTRATOS ########################################", LARGURA, '#'));
        contratoMotivoDao.cadastrarContratoMotivo("Aniversário");
        contratoMotivoDao.cadastrarContratoMotivo("Bodas");
        contratoMotivoDao.cadastrarContratoMotivo("Casamento");
        contratoMotivoDao.cadastrarContratoMotivo("Confraternização");
        contratoMotivoDao.cadastrarContratoMotivo("Encontro de Amigos(as)");

        System.out.println(Texto.padC("######################################## CADASTRO DE MESES DO ANO ########################################", LARGURA, '#'));
        MesAnoDao mesAnoDao = new MesAnoDaoJpa(em);
        mesAnoDao.cadastrar("01", "Janeiro");
        mesAnoDao.cadastrar("02", "Fevereiro");
        mesAnoDao.cadastrar("03", "Março");
        mesAnoDao.cadastrar("04", "Abril");
        mesAnoDao.cadastrar("05", "Maio");
        mesAnoDao.cadastrar("06", "Junho");
        mesAnoDao.cadastrar("07", "Julho");
        mesAnoDao.cadastrar("08", "Agosto");
        mesAnoDao.cadastrar("09", "Setembro");
        mesAnoDao.cadastrar("10", "Outubro");
        mesAnoDao.cadastrar("11", "Novembro");
        mesAnoDao.cadastrar("12", "DEZEMBRO");

        MesAno mesAno = mesAnoDao.buscar("12");
        mesAno.setDescr("Dezembro");
        mesAnoDao.salvar(mesAno);

        System.out.println(Texto.padC("######################################## CLIENTE FAZ UM CONTRATO ########################################", LARGURA, '#'));
        Cliente cliente = clienteDao.buscar("55555555555");
        Alugavel alugavel = alugavelDao.buscar(1L);
        ContratoMotivo contratoMotivo = contratoMotivoDao.buscarContratoMotivo(1L);

        contratoDao.cadastrar( cliente
                             , alugavel
                             , Data.toDate("25/12/2020")
                             , new BigDecimal(100.00)
                             , contratoMotivo);

        alugavel = alugavelDao.buscar(2L);
        contratoDao.cadastrar( cliente
                             , alugavel
                             , Data.toDate("10/01/2021")
                             , new BigDecimal(100.00)
                             , contratoMotivo
                             , "Joãozinho"
                             , 5
                             , mesAnoDao.buscar("01"));

        contratoDao.cadastrar( cliente
                             , alugavel
                             , Data.toDate("15/11/2020")
                             , new BigDecimal(150)
                             , contratoMotivo
                             , "Mariazinha"
                             , 10
                             , mesAnoDao.buscar("11"));

        System.out.println(Texto.padC("######################################## CONTRATOS DE UM DETERNIMADO CLIENTE E DETERMINADO ALUGAVEL ########################################", LARGURA, '#'));

        List<Contrato> contratos = contratoDao.buscar(alugavel, cliente);
        System.out.println("Contratos do Cliente " + cliente.getNome() + " que alugou o " + alugavel.getDescr());
        for (Contrato ctr : contratos) {
            System.out.println("Data : " + (new SimpleDateFormat("dd.MM.yyyy").format(ctr.getData())) + " " );
        }

        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}