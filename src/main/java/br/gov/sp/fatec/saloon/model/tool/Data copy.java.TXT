package br.gov.sp.fatec.saloon.model.tool;

/**
 * @apiNote Biblioteca de funções para tratamento de data
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Data {

    /**
     * @apiNote today() -> Retorna a data atual do sistema operacional.
     * @param none
     * @return Date
     */
    public static Date today() {
        return new GregorianCalendar().getTime();
    }

    /**
     * @apiNote toDate( String 99/99/9999 ) = Função que transforma uma String em
     *          data
     * @param sData -> "dd/mm/yyyy"
     * @return Date
     * @throws ParseException
     */
    public static Date toDate(String sData) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(sData);
    }
    
    /**
     * @apiNote DtoS( Date ) = Função que transforma uma Data em string de AAAAMMDD
     * @param Date
     * @return String -> "AAAAMMDD"
     */
    public static String DtoS(Date data) {
    	return Texto.strZero(data.getYear(), 4) + Texto.strZero(data.getMonth(), 2) + Texto.strZero(data.getDay(), 2);
    }
    

    /**
     * @apiNote dataSomaDias( Date data, ind dias) -> Função que soma dias a uma
     *          data;
     * @implNote Através do Calendar, trabalhamos a data informada e adicionamos
     *           dias nela
     * @param Date = dd/mm/yyyy
     * @param dias = Quantidade de dias que será somaa a data
     * @return Date
     */
    public static Date dataSomaDias(Date data, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }

    /**
     * @apiNote time() -> Retorna a hora atual em caractere
     * @param none
     * @return String
     */
    public static String time() {
        return new SimpleDateFormat("HH:mm:ss").format(today());
    }

}