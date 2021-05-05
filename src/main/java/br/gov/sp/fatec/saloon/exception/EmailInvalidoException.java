package br.gov.sp.fatec.saloon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class EmailInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailInvalidoException() {
        super();
    }

    public EmailInvalidoException(String message){
        super("E-Mail inválido: \"" + message + "\"");
    }

    public EmailInvalidoException(Throwable cause){
        super(cause);
    }

    public EmailInvalidoException(String message, Throwable cause){
    	super("E-Mail inválido: \"" + message + "\"", cause);
    }

}








