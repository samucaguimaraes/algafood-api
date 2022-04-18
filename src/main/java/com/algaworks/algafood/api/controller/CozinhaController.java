package com.algaworks.algafood.api.controller;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.AtributoObrigatorioException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.todas();
	}
	
	
	/**
	 * GET /cozinhas/{cozinhaId} HTTP/1.1
	 * 
	 */
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha){
		try {
			cozinha = cadastroCozinha.salvar(cozinha);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(cozinha);
			
		} catch (AtributoObrigatorioException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	
	/**
	 * PUT /cozinhas/{cozinhaId} HTTP/1.1
	 */
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long cozinhaId,
			@RequestBody Cozinha cozinha) {
		
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinhaAtual != null) {
			//cozinhaAtual.setNome(cozinha.getNome());
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
			cadastroCozinha.salvar(cozinhaAtual);
			
			return ResponseEntity.ok(cozinhaAtual);
		} 
		
		return ResponseEntity.notFound().build();				
	}
	
	/**
	 * DELETE /cozinhas/{cozinhaId} HTTP/1.1
	 */
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover( @PathVariable("cozinhaId") Long cozinhaId) {
		
		try {
			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}			
	}
}


