package br.com.waldecleber.service;

import java.util.List;

import br.com.waldecleber.dto.EstadoDTO;
import br.com.waldecleber.entity.Estado;

public interface EstadoService {
	
	Estado salvar(EstadoDTO estado);
	
	List<EstadoDTO> listarEstados();
	
	List<EstadoDTO> buscarEstadoPorId(Long id);

}
