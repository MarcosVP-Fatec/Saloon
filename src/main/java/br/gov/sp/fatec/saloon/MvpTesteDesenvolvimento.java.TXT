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

public class MvpTesteDesenvolvimento {

    static int LARGURA = 112;

    public static void run() throws ParseException {
/*
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
            usuarioDadosPessoaisDao.salvar
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
            usuarioDadosPessoaisDao.salvar
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
        usuario = usuarioDadosPessoaisDao.buscar("MVP");
        usuario.setSenha("123"); //senhaMVP
        usuarioDadosPessoaisDao.salvar( usuario );
        
        System.out.println(Texto.padC("######################################## CADASTRO DE PROPRIETARIO ########################################", LARGURA, '#'));
        
        proprietarioDao.salvar
        (new Proprietario("BETÃO"
                        , "alberto.salas@terra.com.br"
                        , "pwAS"
                        , "Alberto Salasar"
                        , Data.toDate("25/06/1970")
                        , "33333333333")
            
        );
        
        System.out.println(Texto.padC("######################################## CADASTRO DE PARCEIROS ########################################", LARGURA, '#'));
        
        parceiroDao.salvar
        (new Parceiro("Festeira"
                     , "festeira@hotmail.com"
                     , "pwTF"
                     , "Tereza Festeira"
                     , Data.toDate("31/12/1980")
                     , "55555555555")
            
        );

        parceiroDao.salvar
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

        System.out.println(">>>>> " + proprietarioDao.buscar("BETÃO").getApelido());
        System.out.println(">>>>> " + em.find(AlugavelTipo.class,1L).getDescr());
        System.out.println(">>>>> " + alugavelTipoDao.buscarAlugavelTipo(1L).getDescr() + alugavelTipoDao.buscarAlugavelTipo(1L).getId());
        
        alugavelDao.cadastrar("Salão Maior"
                             ,proprietarioDao.buscar("BETÃO")
                             ,alugavelTipoDao.buscarAlugavelTipo(1L)
                             ,"Rua Ottoboni, 123 - Vila Industrial - São José dos campos"
                             , 150
                             , 600.00 );

        alugavelDao.cadastrar("Salão Menor"
                             ,proprietarioDao.buscar("BETÃO")
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
        Parceiro parceiro = parceiroDao.cadastrar("BIFECASSIA"                          //apelido
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

        parceiro = parceiroDao.buscar("BIFECASSIA");
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
*/
    }

}
