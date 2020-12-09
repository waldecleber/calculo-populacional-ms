package br.com.waldecleber.controller;

import br.com.waldecleber.dto.EstadoDTO;
import br.com.waldecleber.entity.Estado;
import br.com.waldecleber.service.EstadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * @author waldecleber
 *
 */
@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private final EstadoService estadoService;

	public EstadoController(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	@CrossOrigin
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de estados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de estados"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	public ResponseEntity<List<EstadoDTO>> listarEstados() {		
		return ResponseEntity.ok().body(estadoService.listarEstados());
	}
	
	@CrossOrigin
	@GetMapping(path = "/{id}")
//	@ApiOperation(value = "Retorna uma lista de estados")
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Retorna a lista de estados"),
//			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
//			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
//	})
	public ResponseEntity<List<EstadoDTO>> listarEstados(@PathVariable Long id) {		
		return ResponseEntity.ok().body(estadoService.buscarEstadoPorId(id));
	}
	
	@CrossOrigin
	@PostMapping
//	@ApiOperation(value = "Salva um novo estado")
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Salva um novo estado"),
//			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
//			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
//	})
	public ResponseEntity<Estado> salvar(@RequestBody EstadoDTO estado) {    	
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.salvar(estado));			
	}
	
}
