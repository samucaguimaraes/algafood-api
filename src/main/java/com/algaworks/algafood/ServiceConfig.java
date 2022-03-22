package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.notificacao.NotificadorEmail;
import com.algaworks.algafood.service.AtivacaoClienteService;

@Configuration
public class ServiceConfig {

	@Bean
	public AtivacaoClienteService ativacaoClienteService(NotificadorEmail notificador) {
		return new AtivacaoClienteService(notificador);
	}
}
