package br.com.waldecleber.service;

import java.util.List;

import br.com.waldecleber.dto.CidadeDTO;
import br.com.waldecleber.entity.Cidade;

public interface CidadeService {

	List<CidadeDTO> listarCidadePorEstado(String uf);
	
	Cidade salvar(Cidade cidade);
	
	void excluir(Long id);

	Cidade salvar(CidadeDTO cidade);

	List<CidadeDTO> listarCidade();
}
