package br.gov.sp.fatec.saloon.model.tool;

/**
 * @apiNote Biblioteca de funções para tratamento de texto
 * 
 */

 public class Text {
    
   /**
     * @apiNote left( String sTexto , int nTam ) -> Retorna a parte esqueda da String
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @return String
     */
    public static String left(String sTexto, int nTam) {
        return sTexto == null ? "" : sTexto.substring(0, sTexto.length() < nTam ? sTexto.length() : nTam);
    }

    /**
     * @apiNote sohDigitos( String numero ) = Verifica se uma String contém somente dígitos
     * @param String numeros
     * @return Boolean
     */
    public static boolean sohDigitos( String numero ){
        String digito;
        for (int i = 0; i < numero.length(); i++) {
            digito = numero.substring(i,i+1);
            if ( !digito.equals("0") &&
                 !digito.equals("1") && 
                 !digito.equals("2") && 
                 !digito.equals("3") && 
                 !digito.equals("4") && 
                 !digito.equals("5") && 
                 !digito.equals("6") && 
                 !digito.equals("7") && 
                 !digito.equals("8") && 
                 !digito.equals("9") ){
                     return false;
                 } 
        }
        return true;
    }

}

 

    
