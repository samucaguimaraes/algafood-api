package com.algaworks.algafood.domain.exception;

public class AtributoObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AtributoObrigatorioException(String mensagem) {
		super(mensagem);
	}

}
