package com.algaworks.algafood.service;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	private Notificador notificador;
	
		
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService "+ notificador);
	}


	public void ativar(Cliente cliente) {
		cliente.ativar();
			
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo.");
		
	}
}
