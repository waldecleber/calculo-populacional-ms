package br.com.waldecleber.controller;

import br.com.waldecleber.dto.CidadeDTO;
import br.com.waldecleber.entity.Cidade;
import br.com.waldecleber.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author waldecleber
 *
 */
@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	private final CidadeService cidadeService;

	@Autowired
	public CidadeController(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}


	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CidadeDTO>> listarCidades() {		
		return ResponseEntity.ok().body(cidadeService.listarCidade());
	}
	
	@CrossOrigin
	@GetMapping(path = "/estado/{uf}")
	public ResponseEntity<List<CidadeDTO>> listarCidadePorEstado(@PathVariable(name = "uf") String uf) {		
		return ResponseEntity.ok().body(cidadeService.listarCidadePorEstado(uf));
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody CidadeDTO cidade) {    	
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidade));			
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public Map<String, Boolean> excluir(@PathVariable Long id) {
		cidadeService.excluir(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
