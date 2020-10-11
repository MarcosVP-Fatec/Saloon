package br.gov.sp.fatec.saloon;

/**
 * @App Saloon 
 * @apiNote Teste exclusivo de MARCOS VINICIO PEREIRA
 *
 * Usar a expressão abaixo para executar pelo maven
 * mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */


import br.gov.sp.fatec.saloon.model.tool.Validador;

/**
 * @apiNote - Saloon - Testes de Desenvolvimento
 * @author - MARCOS VINICIO PEREIRA
 * 
 *         Usar a expressão abaixo para executar pelo maven * mvn exec:java
 *         -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */
 
public class MvpTesteDesenvolvimento {

    public static void run() {
        System.out.println( Validador.dvCpf("99999999999") );
    }

}