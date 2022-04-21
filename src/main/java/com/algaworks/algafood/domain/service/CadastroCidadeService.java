package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRespository;
	
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRespository.findById(estadoId);
		
		if(estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de estado com o código %d", estadoId));
		}
		
		cidade.setEstado(estado.get());
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", cidadeId));
		} 
	}
}
