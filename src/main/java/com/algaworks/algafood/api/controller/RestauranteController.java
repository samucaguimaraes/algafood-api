package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRetauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRetauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable("id") Long id){
		
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if(restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante =  cadastroRestaurante.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
			
		} catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.badRequest()
					.body(e.getMessage());
			
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable("restauranteId") Long restauranteId,
			@RequestBody Restaurante restaurante){
		
		try {
			
			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
			
			if(restauranteAtual.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
			cadastroRestaurante.salvar(restauranteAtual.get());
			
			return ResponseEntity.ok(restauranteAtual.get());
			
		} catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.badRequest()
					.body(e.getMessage());
			
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable("restauranteId") Long restauranteId,
			@RequestBody Map <String, Object> campos){
		
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
		
		if(restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteAtual.get());
		
		return atualizar(restauranteId, restauranteAtual.get());
		

	}

	/**
	 * 
	 * @param camposOrigens
	 * @param restauranteDestino
	 */
	private void merge(Map<String, Object> camposOrigens, Restaurante restauranteDestino) {
		
		// Conversão de json em objetos java.
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigens,Restaurante.class);

		camposOrigens.forEach((nomePropriedade, valorPropriedade) -> {
			
			/*
			 *  Busca na classe Restaurante um atributo 
			 */
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			/*
			 *  Alterando permissão para acessar os atributos privado da classe
			 */
			field.setAccessible(true);
			
			/*
			 *  recuperando dinamicamente o valor do objeto origem
			 */
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			/*
			 *  Setando os objetos com os valores das propriedades
			 */
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
	
	

}
