package br.gov.sp.fatec.saloon.model.tool;

/**
 * @apiNote Biblioteca de funções para tratamento de data
 * @author Marcos Vinicio Pereira
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.gov.sp.fatec.saloon.exception.DataInvalidaException;

public class Data {

	// O construtor é privado, portanto esta classe não pode ser instanciada
    private Data() { }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote today() -> Retorna a data atual do sistema operacional.
     * @implNote São feitas algumas conversões para que a data tenha hor/min/seg zerados.
     * @param none
     * @return Date
     */
    public static Date today() {
        return Data.toDate((Data.dToS(new GregorianCalendar().getTime())));
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote toDate( String 99/99/9999 ) = Função que transforma uma String em
     *          data
     * @param sData -> "dd/mm/yyyy"
     * @return Date
     * @throws ParseException
     */
    public static Date toDate(String sData) {
    	Date data;
    	valid(sData);
    	try {
    		data = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(sData);
    		if (!Data.dToS(data).equals(sData)) throw new DataInvalidaException(sData);
    		return data;
		} catch (ParseException e) {
			// TODO: handle exception
			valid(sData);
			e.printStackTrace();
		}
		return null;
    }
    
    //Função auxiliar das funções de dia, mês e ano abaixo
    private static Date add(Date data, int dias, int tipo) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        calendario.add(tipo, dias);
        return calendario.getTime();
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote addD( Date data, ind dias) -> Função que soma dias a uma
     *          data;
     * @implNote Através do Calendar, trabalhamos a data informada e adicionamos
     *           dias nela
     * @param Date = dd/mm/yyyy
     * @param int = Quantidade de dias que será soma a data
     * @return Date
     */
    public static Date addD(Date data, int dias) {
    	return add(data, dias, Calendar.DAY_OF_MONTH);
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote addD( Date data, ind dias) -> Função que soma meses a uma
     *          data;
     * @implNote Através do Calendar, trabalhamos a data informada e adicionamos
     *           dias nela
     * @param Date = dd/mm/yyyy
     * @param int = Quantidade de meses que será soma a data
     * @return Date
     */
    public static Date addM(Date data, int meses) {
    	return add(data, meses, Calendar.MONTH);
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote addD( Date data, ind dias) -> Função que soma meses a uma
     *          data;
     * @implNote Através do Calendar, trabalhamos a data informada e adicionamos
     *           dias nela
     * @param Date = dd/mm/yyyy
     * @param int = Quantidade de meses que será soma a data
     * @return Date
     */
    public static Date addY(Date data, int anos) {
    	return add(data, anos, Calendar.YEAR);
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote DtoS --> Função que transforma uma Data em string de DD/MM/AAAA
     * @param Date
     * @return String -> "DD/MM/AAAA"
     */
    public static String dToS(Date data) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        StringBuffer buffer = new StringBuffer(Texto.strZero(calendario.get(Calendar.DAY_OF_MONTH), 2));
        buffer.append('/');
        buffer.append(Texto.strZero(calendario.get(Calendar.MONTH)+1, 2));
        buffer.append('/');
        return buffer.append(Texto.strZero(calendario.get(Calendar.YEAR), 4)).toString();
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote dToStringOrdem -> Retorna a hora atual em caractere para ordenação
     * @param Date
     * @return String --> AAAAMMDD
     */
    public static String dToSOrder(Date data){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        StringBuffer buffer = new StringBuffer(Texto.strZero(calendario.get(Calendar.YEAR), 4));
        buffer.append(Texto.strZero(calendario.get(Calendar.MONTH)+1, 2));
        return buffer.append(Texto.strZero(calendario.get(Calendar.DAY_OF_MONTH), 2)).toString();
     }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote time -> Retorna a hora atual em caractere
     * @param none
     * @return String
     */
    public static String time() {
        return new SimpleDateFormat("HH:mm:ss").format(today());
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote time -> Retorna a hora da data informada em caractere
     * @param Date
     * @return String
     */
    public static String time(Date data) {
        return new SimpleDateFormat("HH:mm:ss").format(data);
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote day -> Retorna int do dia da data informada
     * @param Date
     * @return int
     */
    public static int day(Date data){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        return calendario.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote month -> Retorna int do mês da data informada
     * @param Date
     * @return int
     */
    public static int month(Date data){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        return calendario.get(Calendar.MONTH)+1;
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote year -> Retorna int do ano da data informada
     * @param Date
     * @return int
     */
    public static int year(Date data){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        return calendario.get(Calendar.YEAR);
    }

    /**
     * @author Marcos Vinicio Pereira
     * @apiNote dayWeek -> Retorna int do dia da semana da data informada
     * @param Date
     * @return int
     */
    public static int dayWeek(Date data){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        return calendario.get(Calendar.DAY_OF_WEEK);
    }
    
    public static boolean valid(String sData) {
    	
    	if (   Texto.sohDigitos(sData.substring(0, 2)) 
    		&& sData.substring(2,3).equals("/")	
    		&& Texto.sohDigitos(sData.substring(3, 5))
    		&& sData.substring(5,6).equals("/")
    		&& Texto.sohDigitos(sData.substring(6, 10))) { 
    		return true;
        }	
    		 
    	throw new DataInvalidaException(sData);
    	
    }
    
}

