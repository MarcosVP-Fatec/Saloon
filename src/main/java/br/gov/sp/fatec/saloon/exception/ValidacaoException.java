package br.gov.sp.fatec.saloon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class ValidacaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValidacaoException() {
        super();
    }

    public ValidacaoException(String message){
        super(message);
    }

    public ValidacaoException(Throwable cause){
        super(cause);
    }

    public ValidacaoException(String message, Throwable cause){
        super(message, cause);
    }

}






