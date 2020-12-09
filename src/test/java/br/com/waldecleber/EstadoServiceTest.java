package br.com.waldecleber;


import br.com.waldecleber.dto.EstadoDTO;
import br.com.waldecleber.entity.Cidade;
import br.com.waldecleber.entity.Estado;
import br.com.waldecleber.repository.CotacaoDolarClient;
import br.com.waldecleber.repository.EstadoRepository;
import br.com.waldecleber.service.impl.EstadoServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class EstadoServiceTest {

	private EstadoServiceImpl estadoService;
	private EstadoRepository estadoRepository;
	private CotacaoDolarClient cotacaoDolarClient;

	@Before
	public void setUp() {
		estadoRepository = mock(EstadoRepository.class);
		cotacaoDolarClient = mock(CotacaoDolarClient.class);
		estadoService = new EstadoServiceImpl(estadoRepository, cotacaoDolarClient);
	}
	
	@Test
	public void salvaEstadoComSucesso() {
		Estado estado = Estado.builder()
				.id(1L)
				.nome("Rio Grande do Sul")
				.build();
		
		when(estadoRepository.save(any(Estado.class))).thenReturn(estado);
		estadoService.salvar(estado);
		verify(estadoRepository, times(1)).save(estado);
	}
	
	@Test
	public void listarEstados() {
		List<Estado> estados = Arrays.asList(Estado.builder().uf("RS").build());
		when(estadoRepository.listarEstados())
			.thenReturn(estados);

		Estado rs = Estado.builder()
				.uf("RS")
				.cidades(new HashSet<>())
				.build();
		Cidade cidade = Cidade.builder()
				.id(1L)
				.nome("Canoas")
				.populacao(5000)
				.build();

		rs.getCidades().add(cidade);
		when(estadoRepository.buscarEstadoPorUf("RS"))
				.thenReturn(rs);

		List<EstadoDTO> result = estadoService.listarEstados();

		verify(estadoRepository, times(1)).listarEstados();
		assertThat(estados.size(), is(result.size()));
	}
	
	@Test
	public void calcularPopulacaoEstado() {
		Estado estado = Estado.builder()
				.id(1L)
				.nome("Rio Grande do Sul")
				.uf("RS")
				.build();
		
		Cidade cidade1 = Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.populacao(12000)
				.estado(estado)
				.build();
		
		Cidade cidade2 = Cidade.builder()
				.id(2L)
				.nome("Canoas")
				.populacao(6000)
				.estado(estado)
				.build();
		
		Cidade cidade3 = Cidade.builder()
				.id(3L)
				.nome("Novo Hamburso")
				.populacao(4000)
				.estado(estado)
				.build();
		
		List<Cidade> cidades = new ArrayList<>();
		
		cidades.add(cidade1);
		cidades.add(cidade2);
		cidades.add(cidade3);
		
		estado.setCidades(new HashSet<>(cidades));
		when(estadoRepository.buscarEstadoPorUf("RS")).thenReturn(estado);
		Integer totalPopulacao = estadoService.calcularPopulacaoEstado(estado.getUf());
		
		assertThat(22000, is(totalPopulacao));
	}
 	
}
