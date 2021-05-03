package br.gov.sp.fatec.saloon.model.entity.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.gov.sp.fatec.saloon.exception.DataInvalidaException;
import br.gov.sp.fatec.saloon.model.tool.Data;

@SpringBootTest
public class DataTests {

    @Test
    void testeData(){
    	
    	Quebra.print("Testes Métodos de Data");

        Date dTeste1 = Data.toDate("30/04/2021");
        Date dTeste2 = Data.toDate("02/05/2021");
        Date dTeste3 = Data.toDate("30/07/2021");
        Date dTeste4 = Data.toDate("30/04/2029");
        
        //Verifica soma de dias
        assertEquals( dTeste2, Data.addD( dTeste1 , 2 ) );

        //Verifica soma de meses
        assertEquals( dTeste3, Data.addM( dTeste1 , 3 ) );
        
        //Verifica soma de anos
        assertEquals( dTeste4, Data.addY( dTeste1 , 8 ) );
        
        assertFalse(Data.today().equals(new GregorianCalendar().getTime()));
        
    }
    
    @Test
    void testeDataExceptions() {

        //Verifica exceptions de data inválida
        Date dTesteErro;
        for (String sData : Arrays.asList("28/02/2021","31/02/2021")) {
        	try {
        		dTesteErro = Data.toDate(sData);
        		assertEquals(dTesteErro, Data.toDate(sData));
			} catch (DataInvalidaException e) {
				assertEquals("Data inválida: \"" + sData + "\"" , e.getMessage() );
			}
			
		}

        //Verifica exceptions de data inválida
        for (String sData : Arrays.asList("28/02/2021","31/12/196o")) {
        	try {
        		dTesteErro = Data.toDate(sData);
        		assertEquals(dTesteErro, Data.toDate(sData));
			} catch (DataInvalidaException e) {
				assertEquals("Data inválida: \"" + sData + "\"", e.getMessage() );
			}
		}

        //Verifica exceptions de data inválida
        for (String sData : Arrays.asList("28/02/2021","31/12/196o","31/1w/1969","3q/12/1969","31112/1969","31/1221969")) {
        	try {
        		Data.valid(sData);
			} catch (DataInvalidaException e) {
				assertEquals("Data inválida: \"" + sData + "\"", e.getMessage() );
			}
		}
    	
    }
    
    @Test
    void testeDataPartes(){
    	
    	Date data = Data.toDate("24/06/2021");
    	
    	assertEquals(  24, Data.day(data) );
    	assertEquals(   6, Data.month(data) );
    	assertEquals(2021, Data.year(data) );
    	
    	assertEquals("20210624", Data.dToSOrder(data));
    	
    	assertEquals("00:00:00", Data.time());
    	assertEquals("00:00:00", Data.time(data));
    	
    	assertEquals(5, Data.dayWeek(data));
    	
    }
}
