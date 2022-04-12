package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> todas();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Long id);

}
