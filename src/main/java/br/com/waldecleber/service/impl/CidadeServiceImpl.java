package br.com.waldecleber.service.impl;

import br.com.waldecleber.dto.CidadeDTO;
import br.com.waldecleber.entity.Cidade;
import br.com.waldecleber.exception.CidadeNaoEncontradaException;
import br.com.waldecleber.exception.CidadeNaoPodeSerExcluidaException;
import br.com.waldecleber.exception.CidadeNomeDuplicadoException;
import br.com.waldecleber.repository.CidadeRepository;
import br.com.waldecleber.service.CidadeService;
import br.com.waldecleber.util.MapperConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeServiceImpl implements CidadeService {

	private CidadeRepository cidadeRepository;

	public CidadeServiceImpl(CidadeRepository cidadeRepository) {
		this.cidadeRepository= cidadeRepository;
	}
	
	public List<CidadeDTO> listarCidadePorEstado(String uf) {
		return MapperConverter.mapList(cidadeRepository.listarCidadePorEstado(uf), CidadeDTO.class);
	}

	public Cidade salvar(Cidade cidade) {
		Optional<Cidade> optional = cidadeRepository.buscarCidadePorNomeEPorEstado(cidade.getNome(), cidade.getEstado().getUf());
		if (optional.isPresent()) {
			throw new CidadeNomeDuplicadoException("Já existe uma cidade com este mesmo Estado.");
		}
		return cidadeRepository.save(cidade);
		
	}

	public void excluir(Long id) {
		Cidade cidade = cidadeRepository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaException("Não foi encontrada cidade com este id."));
		Optional<Cidade> optional = cidadeRepository.buscarCidadePorNomeEPorEstado(cidade.getNome(), cidade.getEstado().getUf());
		if (optional.isPresent() && cidade.getEstado().getUf().equals("RS")) {
			throw new CidadeNaoPodeSerExcluidaException("Esta cidade não pode ser removida do Estado Rio Grande do Sul.");
		}
		cidadeRepository.delete(cidade);
	}

	@Override
	public Cidade salvar(CidadeDTO cidadeDTO) {
		Cidade cidade = MapperConverter.map(cidadeDTO, Cidade.class);
		return salvar(cidade);
	}

	@Override
	public List<CidadeDTO> listarCidade() {
		return MapperConverter.mapList(cidadeRepository.listarTodasCidades(), CidadeDTO.class); 
	}

}
