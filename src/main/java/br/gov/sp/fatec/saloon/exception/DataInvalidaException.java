package br.gov.sp.fatec.saloon.exception;

import br.gov.sp.fatec.saloon.model.tool.Texto;

/**
 * 
 * @author Marcos Vinicio Pereira
 *
 */

public class DataInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String M_STRING = Texto.Trad("Data inválida: \"");

	public DataInvalidaException() {
        super();
    }

    public DataInvalidaException(String message){
        super(M_STRING + message + "\"");
    }

    public DataInvalidaException(Throwable cause){
        super(cause);
    }

    public DataInvalidaException(String message, Throwable cause){
        super(M_STRING + message + "\"", cause);
    }
	
}
