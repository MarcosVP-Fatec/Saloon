package br.gov.sp.fatec.saloon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class CpfInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfInvalidoException() {
        super();
    }

    public CpfInvalidoException(String cpf){
        super("CPF inválido: \"" + cpf + "\"");
    }

    public CpfInvalidoException(Throwable cause){
        super(cause);
    }

    public CpfInvalidoException(String cpf, Throwable cause){
        super("CPF inválido: \"" + cpf + "\"", cause);
    }

}










