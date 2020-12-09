package br.com.waldecleber.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class EstadoDTO {

	private Long id;

	private String nome;

	private String uf;
	
	private String bandeira;
	
	private Integer populacao;
	
	private BigDecimal custoPopulacional;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public BigDecimal getCustoPopulacional() {
		return custoPopulacional;
	}

	public void setCustoPopulacional(BigDecimal custoPopulacional) {
		this.custoPopulacional = custoPopulacional;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EstadoDTO estadoDTO = (EstadoDTO) o;
		return Objects.equals(id, estadoDTO.id)
				&& Objects.equals(nome, estadoDTO.nome)
				&& Objects.equals(uf, estadoDTO.uf)
				&& Objects.equals(bandeira, estadoDTO.bandeira)
				&& Objects.equals(populacao, estadoDTO.populacao)
				&& Objects.equals(custoPopulacional, estadoDTO.custoPopulacional);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, uf, bandeira, populacao, custoPopulacional);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String nome;
		private String uf;
		private String bandeira;
		private Integer populacao;
		private BigDecimal custoPopulacional;

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

		public Builder uf(String uf) {
			this.uf = uf;
			return this;
		}

		public Builder bandeira(String bandeira) {
			this.bandeira = bandeira;
			return this;
		}

		public Builder populacao(Integer populacao) {
			this.populacao = populacao;
			return this;
		}

		public Builder custoPopulacional(BigDecimal custoPopulacional) {
			this.custoPopulacional = custoPopulacional;
			return this;
		}

		public EstadoDTO build() {
			EstadoDTO estadoDTO = new EstadoDTO();
			estadoDTO.setId(id);
			estadoDTO.setNome(nome);
			estadoDTO.setUf(uf);
			estadoDTO.setBandeira(bandeira);
			estadoDTO.setPopulacao(populacao);
			estadoDTO.setCustoPopulacional(custoPopulacional);
			return estadoDTO;
		}
	}
}
