package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;

public class NotificadorEmail implements Notificador {
	
	private boolean caixaAlta;
	private String hostServidorSmtp;
	
	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		if(this.caixaAlta) {
			mensagem = mensagem.toUpperCase();
		}
		
		System.out.printf("Notificamos %s através do e-mail %s usando SMTP %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), this.hostServidorSmtp,mensagem);		
	}

}
