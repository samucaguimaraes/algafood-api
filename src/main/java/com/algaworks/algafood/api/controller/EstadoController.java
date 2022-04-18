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
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;


@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.todos();
	}
	
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable("estadoId") Long id) {
		Estado estado = estadoRepository.buscar(id);
		
		if (estado != null) {
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado){
		try {
			estado = cadastroEstado.salvar(estado);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(estado);
			
		} catch (AtributoObrigatorioException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable("estadoId") Long estadoId
			, @RequestBody Estado estado ){
		
		Estado estadoAtual = estadoRepository.buscar(estadoId);
		
		if(estadoAtual != null) {
			
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			cadastroEstado.salvar(estado);
			return ResponseEntity.ok(estadoAtual);
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover(@PathVariable("estadoId") Long estadoId){
		
		try {
			cadastroEstado.excluir(estadoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
}
