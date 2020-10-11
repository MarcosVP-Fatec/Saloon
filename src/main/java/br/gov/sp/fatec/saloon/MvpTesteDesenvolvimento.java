package br.gov.sp.fatec.saloon;

import java.text.ParseException;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.saloon.model.PersistenceManager;
import br.gov.sp.fatec.saloon.model.tool.SaidasConsole;

/**
 * @App Saloon 
 * @apiNote Teste exclusivo de MARCOS VINICIO PEREIRA
 *
 * Usar a expressão abaixo para executar pelo maven
 * mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

import br.gov.sp.fatec.saloon.model.tool.Texto;

public class MvpTesteDesenvolvimento {

    static int LARGURA = 150;

    EntityManager em = PersistenceManager.getInstance().getEntityManager();

    public static void run() throws ParseException {

        System.out.println(Texto.padC("######################################## INÍCIO ########################################", LARGURA, '#'));
        System.out.println(""); 

        System.out.println(Texto.padC("######################################## CADASTRO DE USUÁRIO ########################################", LARGURA, '#'));
        //UsuarioDadosPessoais usuario = new UsuarioDadosPessoais("MVP", "mvp@fatecsjc.sp.gov.br", "pwmvp", "Marcos Vinicio Pereira", Data.toDate("12/04/1969"), "11111111111");


        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}