package br.gov.sp.fatec.saloon.model.tool;

/**
 * @author Marcos Vinicio Pereira
 * @apiNote Biblioteca de funções para tratamento de texto.
 * @author Marcos Vinicio Pereira
 * 
 */

 public class Texto {
    
	// O construtor é privado, portanto esta classe não pode ser instanciada
    private Texto() { }

    /**
     * @apiNote left( String sTexto , int nTam ) -> Retorna a parte esqueda da String
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @return String
     */
    public static String left(String texto, int nTam) {
        return texto.substring(0, texto.length() < nTam ? texto.length() : nTam);
    }

   /**
     * @apiNote right( String texto , int nTam ) -> Retorna a parte direita da String
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @return String
     */
    public static String right(String texto, int nTam) {
        return texto.substring( texto.length() <= nTam ? 0 : texto.length() - nTam );
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
     * @author Marcos Vinicio Pereira
     * @apiNote padL( String texto , int nTam, Character cLetra ) -> Retorna o texto com preenchimento à esquerda com o char passado
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
     * @author Marcos Vinicio Pereira
     * @apiNote padR( String texto , int nTam, Character cLetra ) -> Retorna o texto com preenchimento à direita com o char passado
     * oBS.: Esta função corta o texto se ele for maior que o tamanho desejado, pois é feita para impressão e relatórios formatados
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @param cLetra -> Letra que vai preencher os espaços à esquerda. Se omitido usará " "
     * @return String
     */
    public static String padR( String texto , int nTam )               { return padR( texto, nTam, ' ');}
    public static String padR( String texto , int nTam , String letra ){ return padR( texto, nTam, letra.charAt(0)); }
    public static String padR( String texto , int nTam , Character letra ){ 
       if (texto.length() >= nTam ) return Texto.left(texto, nTam);
        return texto + replicate(letra,nTam - texto.length() ) ;
    }

    /**
     * @author Marcos Vinicio Pereira
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
     * @author Marcos Vinicio Pereira
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
     * @author Marcos Vinicio Pereira
     * @apiNote strZero( String texto , int nTam ) -> Retorna a string preenchida com zeros à esquerda
     * @param String -> Texto
     * @param Int    -> Tamanho que quer que retorne
     * @return String
     */
     public static String strZero( int numero, int nTam){ return strZero( Integer.toString( numero ), nTam );}
     public static String strZero( String texto, int nTam){
        return padL( texto , nTam, '0');
    }

     /**
	  * @author Marcos Vinicio Pereira
	  * @apiNote trad( String texto , int nTam ) -> Retorna o texto conforme tradução 
	  * @param String -> Texto
	  * @param Int    -> Tamanho que quer que retorne
	  * @return String
	  */
     public static String Trad(String texto) {
    	 return texto;
     }
     
     /**
	  * @author Marcos Vinicio Pereira
	  * @apiNote Verifica se um texto é um dígito 
	  * @param String ou Char
	  * @return boolean
	  */
     public static boolean isDigit( char digito ) {
    	 if (   digito == '0' || digito == '1' || digito == '2' || digito == '3' 
        	 || digito == '4' || digito == '5' || digito == '6' || digito == '7' 
        	 || digito == '8' || digito == '9') {
    		 return true;
    	 }
    	 return false;
     }

     /**
      * @author Marcos Vinicio Pereira
      * @apiNote sohDigitos( String numero ) = Verifica se uma String contém somente dígitos (0123456789)
      * @param String 
      * @return Boolean
      */
     public static boolean sohDigitos( String digitos ) {
     	 for (char digChar : digitos.toCharArray()) {
		    if (!isDigit(digChar)) return false;
		 }
    	 return true;
     }

 }

 

    
