package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.AtributoObrigatorioException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepositorio;
	
	public Estado salvar(Estado estado) {
		
		if(estado.getNome() == null) {
			throw new AtributoObrigatorioException(
					String.format("O nome da cozinha é obrigatório. ", 1));
		}
		
		return estadoRepositorio.salvar(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepositorio.remover(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com código %d", estadoId));
		} 
	}
}
