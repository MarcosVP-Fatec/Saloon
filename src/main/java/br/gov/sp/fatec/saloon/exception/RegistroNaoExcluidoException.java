package br.gov.sp.fatec.saloon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class RegistroNaoExcluidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroNaoExcluidoException() {
        super();
    }

    public RegistroNaoExcluidoException(String message){
        super(message);
    }

    public RegistroNaoExcluidoException(Throwable cause){
        super(cause);
    }

    public RegistroNaoExcluidoException(String message, Throwable cause){
        super(message, cause);
    }

}
