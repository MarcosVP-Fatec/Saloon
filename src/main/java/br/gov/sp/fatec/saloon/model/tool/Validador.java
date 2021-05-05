package br.gov.sp.fatec.saloon.model.tool;

import java.util.InputMismatchException;

/**
 * @apiNote Biblioteca de funções para validadores de códigos (CPF, CNPJ, etc.)
 * @author Marcos Vinicio Pereira
 * 
 */

 public class Validador {

    /**
     * @apiNote cpf( sCPF ) --> Valida se um CPF é válido
     * @param String --> Código CPF de 11 dígitos
     * @return boolean
     */
    public static boolean cpf( String cpf ){
    	//Tamanho obrigatório 
    	if (cpf.length() != 11) {
    		return false;
    	}
    	//Tem que conter somente dígitos
    	if (!Texto.sohDigitos(cpf)) {
    		return false;
    	}
    	//Verifica o DV
    	if (!Texto.right(cpf,2).equals(dvCpf(cpf))) {
    		return false;
    	}
        return true;
    }
    
    public static String dvCpf( String cpf ){

        cpf = cpf.trim();

        if (cpf.length() == 11) cpf = Texto.left(cpf,9); 

        if (cpf.length() != 9 || !Texto.sohDigitos(cpf) ) return "--";

        // considera-se erro cpf's formados por uma sequencia de numeros iguais
        /*if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return(false);*/

        char dig10, dig11;
        int sm = 0, peso = 10, i, r, num;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            
            //----------------------------------------------------
            // Cálculo do 1º Dígito Verificador
            // converte o i-esimo caractere do CPF em um número:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 = posição de '0' na tabela ASCII)
            //----------------------------------------------------
            for (i=0 ; i<9 ; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if ( (r == 10) || (r == 11) ){dig10 = '0';}   
            else                         {dig10 = (char)(r + 48);}    // converte no respectivo caractere numérico

            //----------------------------------------------------
            // Cálculo do 2º Dígito Verificador
            //----------------------------------------------------
            cpf += dig10;
            sm = 0;
            peso = 11;
            for( i=0 ; i<10 ; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if ( (r == 10) || (r == 11) ){ dig11 = '0'; }   
            else                         { dig11 = (char)(r + 48); }   

            return Texto.concatenarChar(dig10, dig11);

        } catch (InputMismatchException erro) {
            //throw new InputMismatchException(erro.getMessage());
            return "xx"; //Retorno que não permitirá a validação do DV.
        }

    }

    /**
     * @apiNote cnpj( sCNPJ ) --> Valida se um CNPJ é válido
     * @param sCPF --> Código CNPJ de 14 dígitos
     * @return boolean
     */
    public static boolean cnpj( String cnpj ){
        return cnpj.length() == 14;
    }

    /**
     * @apiNote isCpfOrCnpj( String cod )) --> Validação com exception se é CPF ou CNPJ válido
     * @param cod --> Código CPF de 11 dígitos ou CNPJ de 14 dígitos
     * @return null
     */
    public static void isCpfOrCnpj( String cod ){
        if ( cod.length() == 11){
            if ( ! Validador.cpf(cod) ){
                throw new RuntimeException(">>>> Número inválido de CPF => " + cod);
            }
        } else if (cod.length() == 14){
            if ( ! Validador.cnpj(cod)) {
                throw new RuntimeException(">>>> Número inválido de CNPJ => " + cod);
            }

        } else {
            throw new RuntimeException(">>>> Número inválido de CPF (11 dígitos) ou CNPJ (14 dígitos) => Código passado com " + cod.length() + " dígitos.");
        }

    }
    
    /**
     * @apiNote isEmail --> Validação se o e-mail é válido
     * @param String --> Código CPF de 11 dígitos ou CNPJ de 14 dígitos
     * @return boolean
     */
    public static boolean isEmail(String email) {
    	if (!email.contains("@")) {
    		return false;
    	}
    	return true;
    }

}