package br.gov.sp.fatec.saloon;

import java.math.BigDecimal;
import java.text.ParseException;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.tool.Data;

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

        //MvpTesteDesenvolvimento.run();
        Contrato ctr = new Contrato().novoContrato("11111111111"
                                                  ,"Joana D'arck"
                                                  ,"12"
                                                  ,"987654321"
                                                  ,"PRO_RICO"
                                                  ,"prorico@saloon.br"
                                                  ,"123"
                                                  ,Data.toDate("31/12/1960")
                                                  ,"22222222222"
                                                  ,"SALÃO FESTA ANIMADA"
                                                  ,"RUA DO SOBE E DESCE, Nº QUE NUNCA APARECE"
                                                  ,250
                                                  ,new BigDecimal(750.00)
                                                  ,Data.today()
                                                  ,new BigDecimal(100.00));

        System.out.println("---------------------------------");
        System.out.println("Exemplo controle transacional");
        System.out.println("---------------------------------");
        System.out.println(ctr.getId());

    }

}
