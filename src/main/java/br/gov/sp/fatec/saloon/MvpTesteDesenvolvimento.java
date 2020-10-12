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
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDao;
import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.SaidasConsole;
import br.gov.sp.fatec.saloon.model.tool.Texto;

public class MvpTesteDesenvolvimento {

    static int LARGURA = 150;

    public static void run() throws ParseException {

        EntityManager em = PersistenceManager.getInstance().getEntityManager();
        UsuarioDadosPessoaisDao usuarioDadosPessoaisDao = new UsuarioDadosPessoaisDaoJpa(em);

        System.out.println(Texto.padC("######################################## INÍCIO ########################################", LARGURA, '#'));
        System.out.println(""); 

        System.out.println(Texto.padC("######################################## CADASTRO DE USUÁRIO ########################################", LARGURA, '#'));

        usuarioDadosPessoaisDao.salvarUsuarioDadosPessoais
        (new UsuarioDadosPessoais("MVP"
                                 ,"mvp@fatec.sp.gov.br"
                                 ,"pwMVP"
                                 ,"Marcos Vinicio Pereira"
                                 ,Data.toDate("12/04/1969")
                                 ,"11111111111")
                                 );

        /*
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        */
        
        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}