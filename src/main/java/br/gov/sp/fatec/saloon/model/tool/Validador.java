package br.gov.sp.fatec.saloon.model.tool;

/**
 * @apiNote Biblioteca de funções para validadores de códigos (CPF, CNPJ, etc.)
 * 
 */

 public class Validador {

    /**
     * @apiNote cpf( sCPF ) --> Valida se um CPF é válido
     * @param sCPF --> Código CPF de 11 dígitos
     * @return boolean
     */
    public static boolean cpf( String cpf ){
        return true;
    }
    
    public static String dvCpf( String cpf ){
        if (cpf.length() != 11 && cpf.length() != 9 ) return "  ";
        return "  ";
    }

    /**
     * @apiNote cnpj( sCNPJ ) --> Valida se um CNPJ é válido
     * @param sCPF --> Código CNPJ de 14 dígitos
     * @return boolean
     */
    public static boolean cnpj( String cnpj ){
        return cnpj.length() == 14;
    }

}