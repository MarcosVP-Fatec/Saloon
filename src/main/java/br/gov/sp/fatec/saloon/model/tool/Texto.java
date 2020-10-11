package br.gov.sp.fatec.saloon.model.tool;

/**
 * @apiNote Biblioteca de funções para tratamento de texto.
 * 
 */

 public class Texto {
    
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
     * @apiNote padC( String texto , int nTam, Character cLetra ) -> Retorna o texto no meio do preenchimento de String
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @param cLetra -> Letra que vai preencher os espaços. Se omitido usará " "
     * @return String
     */
    public static String padC( String texto , int nTam )               { return padC( texto, nTam, ' ');}
    public static String padC( String texto , int nTam , String letra ){ return padC( texto, nTam, letra.charAt(0)); }
    public static String padC( String texto , int nTam , Character letra ){ 
        int nResta = nTam - texto.length();
        if ( nResta <= 0 ) return texto;
        int nDireita = nResta / 2;
        nResta -= nDireita;
        StringBuffer retorno = new StringBuffer( replicate( letra , nResta ) );
        retorno.append( texto );
        retorno.append( replicate( letra, nDireita ) );
        return retorno.toString();
    }

   /**
     * @apiNote padL( String texto , int nTam, Character cLetra ) -> Retorna o texto com preenchimeneto à esquerda do char passado
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @param cLetra -> Letra que vai preencher os espaços à esquerda. Se omitido usará " "
     * @return String
     */
    public static String padL( String texto , int nTam )               { return padL( texto, nTam, ' ');}
    public static String padL( String texto , int nTam , String letra ){ return padL( texto, nTam, letra.charAt(0)); }
    public static String padL( String texto , int nTam , Character letra ){ 
        if (texto.length() >= nTam ) return texto;
        return replicate(letra,nTam-texto.length()) + texto;
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

    /**
     * @apiNote concatenaChar( Character... letras ) = Retorna uma String que concatena as char´s passadas
     * @param Character...
     * @return String
     */
    public static String concatenarChar( Character... letras){
        StringBuffer retorno = new StringBuffer();
        for (Character letra : letras) {
            retorno.append(letra);
        }
        return retorno.toString();
    }

    /**
     * @apiNote replicate( String texto, int nQtd ) = Retorna uma String replicada n vêzes 
     * @param String texto
     * @param nQtd
     * @return String
     */
    public static String replicate( Character cLetra, int nQtd ){ return replicate( cLetra.toString(), nQtd ); }
    public static String replicate( String cTexto, int nQtd ){
        StringBuffer retorno = new StringBuffer( cTexto );
        for (int i = 1; i < nQtd; i++) {
            retorno.append( cTexto );
        }
        return retorno.toString();
    }

   /**
     * @apiNote strZero( String texto , int nTam ) -> Retorna a string preenchida com zeros à esquerda
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @return String
     */
     public static String strZero( int numero, int nTam){ return strZero( Integer.toString( numero ), nTam );}
     public static String strZero( String texto, int nTam){
        return padL( texto , nTam, '0');
    }

}

 

    
