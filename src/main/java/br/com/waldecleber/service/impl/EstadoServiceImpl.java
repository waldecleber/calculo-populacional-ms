package br.com.waldecleber.service.impl;

import br.com.waldecleber.dto.EstadoDTO;
import br.com.waldecleber.entity.Estado;
import br.com.waldecleber.exception.EstadoNaoEncontradoException;
import br.com.waldecleber.repository.CotacaoDolarClient;
import br.com.waldecleber.repository.EstadoRepository;
import br.com.waldecleber.service.EstadoService;
import br.com.waldecleber.util.MapperConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

	private final EstadoRepository estadoRepository;
	private final CotacaoDolarClient cotacaoDolarClient;

	private static final BigDecimal CUSTO_CIDADAO = new BigDecimal("123.45");
	private static final BigDecimal VALOR_CORTE = new BigDecimal(50000);
	private static final BigDecimal DESCONTO = new BigDecimal("12.3");
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public EstadoServiceImpl(EstadoRepository estadoRepository, CotacaoDolarClient cotacaoDolarClient) {
		this.estadoRepository = estadoRepository;
		this.cotacaoDolarClient = cotacaoDolarClient;
	}


	public Estado salvar(Estado estado) {		
		return estadoRepository.save(estado);		
	}

	public List<EstadoDTO> listarEstados() {
		List<Estado> estados = estadoRepository.listarEstados();
		List<EstadoDTO> listaDTO = MapperConverter.mapList(estados, EstadoDTO.class);
		listaDTO.forEach(e -> {
				e.setPopulacao(calcularPopulacaoEstado(e.getUf()));
				e.setCustoPopulacional(calcularCustoPopulacional(e.getUf()));
			});
		
		
		return listaDTO;
	}
	
	public Integer calcularPopulacaoEstado(String uf) {
		Estado estado = estadoRepository.buscarEstadoPorUf(uf);
		return estado.getCidades().stream()
					.map(p -> p.getPopulacao())
					.mapToInt(Integer::intValue)
					.sum();		
	}
	
	public BigDecimal calcularCustoPopulacional(String uf) {
		Integer populacao = this.calcularPopulacaoEstado(uf);
		BigDecimal cotacaoDolar = CollectionUtils.isEmpty(cotacaoDolarClient.getColacaoDolar())
				? BigDecimal.ZERO
				: cotacaoDolarClient.getColacaoDolar().get(0).getHigh();
		BigDecimal consultaCusto = new BigDecimal(populacao).multiply(CUSTO_CIDADAO).multiply(cotacaoDolar);
		if (consultaCusto.compareTo(VALOR_CORTE) > 0) {
			BigDecimal desconto = consultaCusto.multiply(DESCONTO).divide(ONE_HUNDRED);
			return consultaCusto.subtract(desconto).setScale(2, RoundingMode.DOWN);
		}
		
		return consultaCusto.setScale(2, RoundingMode.DOWN);
	}

	@Override
	public Estado salvar(EstadoDTO estadoDTO) {
		Estado estado = Estado.builder().build();
		BeanUtils.copyProperties(estadoDTO, estado);
		return salvar(estado);
	}

	@Override
	public List<EstadoDTO> buscarEstadoPorId(Long id) {
		Estado estado = estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException("Não foi encontrado nenhum estado com este id."));
		
		List<EstadoDTO> listaDTO = MapperConverter.mapList(Arrays.asList(estado), EstadoDTO.class);
		listaDTO.stream().forEach(e -> { 
			e.setPopulacao(calcularPopulacaoEstado(e.getUf()));
			e.setCustoPopulacional(calcularCustoPopulacional(e.getUf()));
		});
		
		return listaDTO;
	}

}
