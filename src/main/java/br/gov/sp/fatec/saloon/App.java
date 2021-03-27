package br.gov.sp.fatec.saloon;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;

/**
 * @App Saloon
 *
 *      Usar a expressão abaixo para executar pelo maven 
 *      mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

public class App {

    @Autowired
    private ClienteRepository clienteRepo;


    public static void main(String[] args) throws ParseException
    {

        System.out.println("---------------------------------");
        System.out.println("Exemplo controle transacional");
        System.out.println("---------------------------------");

        Cliente cliente = new Cliente();
        cliente.setCpf_cnpj("55555555555");
        cliente.setNome("Joana D'ark");
        cliente.setTelDdd("12");
        cliente.setTelNumero("987654321");

        
        //MvpTesteDesenvolvimento.run();

        


    }
    
}
