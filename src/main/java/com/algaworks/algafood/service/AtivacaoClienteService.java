package com.algaworks.algafood.service;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {

	private NotificadorEmail notificador;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
			
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo.");
		
	}
}
