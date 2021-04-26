package br.gov.sp.fatec.saloon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNaoEncontratoException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontratoException() {
        super();
    }

    public RegistroNaoEncontratoException(String message){
        super(message);
    }

    public RegistroNaoEncontratoException(Throwable cause){
        super(cause);
    }

    public RegistroNaoEncontratoException(String message, Throwable cause){
        super(message, cause);
    }
}
