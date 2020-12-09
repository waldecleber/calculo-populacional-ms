package br.com.waldecleber.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CidadeNaoPodeSerExcluidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CidadeNaoPodeSerExcluidaException(String msg) {
		super(msg);
	}

}
