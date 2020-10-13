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
import br.gov.sp.fatec.saloon.model.dao.AlugavelTipoDao;
import br.gov.sp.fatec.saloon.model.dao.AlugavelTipoDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.dao.ProprietarioDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.Proprietario;
import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.SaidasConsole;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class MvpTesteDesenvolvimento {

    static int LARGURA = 150;

    public static void run() throws ParseException {

        EntityManager em = PersistenceManager.getInstance().getEntityManager();
        UsuarioDadosPessoaisDao usuarioDadosPessoaisDao = new UsuarioDadosPessoaisDaoJpa(em);
        AlugavelTipoDao alugavelTipoDao = new AlugavelTipoDaoJpa(em);
        ProprietarioDao proprietarioDao = new ProprietarioDaoJpa(em);

        UsuarioDadosPessoais usuario;

        String texto;

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
        (new Proprietario("BETAO"
                        , "alberto.salas@terra.com.br"
                        , "psAS"
                        , "Alberto Salasar"
                        , Data.toDate("25/06/1970")
                        , "33333333333")
            
        );
        
        System.out.println(Texto.padC("######################################## CADASTRO DE TIPO DE ALUGÁVEL ########################################", LARGURA, '#'));
        alugavelTipoDao.cadastrarAlugavelTipo("Salão de Festas");



        
        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}