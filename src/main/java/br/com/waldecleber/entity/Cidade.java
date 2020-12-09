package br.com.waldecleber.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado", referencedColumnName = "id")
	private Estado estado;
	
	private Integer populacao;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
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
		Cidade cidade = (Cidade) o;
		return Objects.equals(id, cidade.id)
				&& Objects.equals(nome, cidade.nome)
				&& Objects.equals(estado, cidade.estado)
				&& Objects.equals(populacao, cidade.populacao);
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
		private Estado estado;

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

		public Builder estado(Estado estado) {
			this.estado = estado;
			return this;
		}

		public Builder populacao(Integer populacao) {
			this.populacao = populacao;
			return this;
		}

		public Cidade build() {
			Cidade cidade = new Cidade();
			cidade.setId(id);
			cidade.setNome(nome);
			cidade.setEstado(estado);
			cidade.setPopulacao(populacao);
			return cidade;
		}
	}
}
