package br.gov.sp.fatec.saloon;

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
import br.gov.sp.fatec.saloon.model.tool.Validador;

public class MvpTesteDesenvolvimento {

    static int LARGURA = 150;

    public static void run() {
        System.out.println(Texto.concatenarChar('A','B','C','D'));
        System.out.println(Texto.padC("######################################## INÍCIO ########################################", LARGURA, '#'));
        System.out.println(Texto.strZero("123",10));
        System.out.println(Texto.strZero(123,10));
        System.out.println( Validador.dvCpf("99999999999") );
        System.out.println(Texto.right( "testamenteiro",5)+"#");
        System.out.println(Texto.padR("preenchido à direita ", LARGURA,'.'));

        System.out.println(Texto.padC("######################################## FIM ########################################", LARGURA, '#'));
        SaidasConsole.printFatecEnd();

    }

}