package br.com.waldecleber.dto;


import java.util.Objects;

public class CidadeDTO {

	private Long id;

	private String nome;

	private EstadoDTO estado;

	private Integer populacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CidadeDTO cidadeDTO = (CidadeDTO) o;
		return Objects.equals(id, cidadeDTO.id) && Objects.equals(nome, cidadeDTO.nome) && Objects.equals(estado, cidadeDTO.estado) && Objects.equals(populacao, cidadeDTO.populacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, estado, populacao);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String nome;
		private EstadoDTO estado;

		private Integer populacao;

		private Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder estado(EstadoDTO estado) {
			this.estado = estado;
			return this;
		}

		public Builder populacao(Integer populacao) {
			this.populacao = populacao;
			return this;
		}

		public CidadeDTO build() {
			CidadeDTO cidadeDTO = new CidadeDTO();
			cidadeDTO.setId(id);
			cidadeDTO.setNome(nome);
			cidadeDTO.setEstado(estado);
			cidadeDTO.setPopulacao(populacao);
			return cidadeDTO;
		}
	}
}
