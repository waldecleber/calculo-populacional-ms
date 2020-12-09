package br.com.waldecleber;

import br.com.waldecleber.entity.Cidade;
import br.com.waldecleber.entity.Estado;
import br.com.waldecleber.exception.CidadeNaoPodeSerExcluidaException;
import br.com.waldecleber.exception.CidadeNomeDuplicadoException;
import br.com.waldecleber.repository.CidadeRepository;
import br.com.waldecleber.service.impl.CidadeServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CidadeServiceTest {

	private CidadeServiceImpl cidadeService;
	private CidadeRepository cidadeRepository;

	@Before
	public void setUp() {
		cidadeRepository = mock(CidadeRepository.class);
		cidadeService = new CidadeServiceImpl(cidadeRepository);
	}

	@Test
	public void listarCidadesPorEstado() {
		when(cidadeRepository.listarCidadePorEstado(anyString()))				
			.thenReturn(Arrays.asList(Cidade.builder().build()));
		cidadeService.listarCidadePorEstado("RS");
		verify(cidadeRepository, times(1)).listarCidadePorEstado(anyString());
	}
	
	@Test
	public void salvarCidadeComSucess() {
		Estado estadoRS = buildEstadoRS();		
		Cidade cidade = buildCidade(estadoRS);

		when(cidadeRepository.buscarCidadePorNomeEPorEstado("Porto Alegre", "RS"))
				.thenReturn(Optional.empty());

		when(cidadeRepository.save(any(Cidade.class)))				
			.thenReturn(cidade);

		cidadeService.salvar(cidade);
		verify(cidadeRepository, times(1)).save(cidade);
		
	}
	
	@Test(expected = CidadeNomeDuplicadoException.class)
	public void salvarCidadeExceptionComMesmoNomePorEstado() {
		Estado estadoRS = buildEstadoRS();		
		Cidade cidade = buildCidade(estadoRS);

		when(cidadeRepository.buscarCidadePorNomeEPorEstado("Porto Alegre", "RS"))
				.thenReturn(Optional.of(cidade));

		when(cidadeRepository.save(any(Cidade.class)))				
			.thenReturn(cidade);

		cidadeService.salvar(cidade);
	}
	
	@Test
	public void excluirCidadeComSucesso() {
		Estado estadoRS = buildEstadoRS();
		Cidade cidade = buildCidade(estadoRS);
		
		doReturn(Optional.of(cidade)).when(cidadeRepository).findById(1L);
		doReturn(Optional.empty())
			.when(cidadeRepository).buscarCidadePorNomeEPorEstado(cidade.getNome(), estadoRS.getUf());

		cidadeService.excluir(cidade.getId());
		verify(cidadeRepository, times(1)).delete(cidade);
	}
	
	@Test(expected = CidadeNaoPodeSerExcluidaException.class)
	public void tentarExcluirCidadeDoRioGrandeDoSul() {
		Estado estadoRS = buildEstadoRS();		
		Cidade cidade = buildCidade(estadoRS);
		
		doReturn(Optional.of(cidade))
			.when(cidadeRepository).buscarCidadePorNomeEPorEstado(cidade.getNome(), estadoRS.getUf());
		
		doReturn(Optional.of(cidade))
			.when(cidadeRepository).findById(1L);
		
		cidadeService.excluir(cidade.getId());
		verify(cidadeRepository, times(0)).delete(cidade);		
	}

	private Cidade buildCidade(Estado estadoRS) {
		return Cidade.builder()
				.id(1L)
				.nome("Porto Alegre")
				.estado(estadoRS)
				.build();
	}

	private Estado buildEstadoRS() {
		return Estado.builder()
			.id(1L)
			.nome("Rio Grande do Sul")
			.uf("RS")
			.build();
	}

}
