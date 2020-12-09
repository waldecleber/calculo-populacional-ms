package br.com.waldecleber.repository;

import br.com.waldecleber.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "estado", path = "estado")
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("SELECT e FROM Estado e " +
			" LEFT JOIN FETCH e.cidades cidade ")
	List<Estado> listarEstados();

	@Query("SELECT e FROM Estado e " +
			" LEFT JOIN FETCH e.cidades cidade " +
			"WHERE e.uf = :uf")
	Estado buscarEstadoPorUf(String uf);

}
