package br.gov.sp.fatec.saloon;

import java.text.ParseException;

/**
 * @App Saloon
 *
 *      Usar a expressão abaixo para executar pelo maven 
 *      mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

public class App {

    public static void main(String[] args) throws ParseException
    {
        new MvpTesteSpringBoot().run(); 
    }

}