package com.algaworks.algafood.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;

@Component
public class NotificadorEmail {
	
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificamos %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);		
	}

}
