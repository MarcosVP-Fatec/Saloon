package br.gov.sp.fatec.saloon;

/**
 * @App Saloon
 *
 *      Usar a expressão abaixo para executar pelo maven 
 *      mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

public class App {

    public static void main(String[] args) 
    {
        new NaoInjeta().run(); 
    }

}