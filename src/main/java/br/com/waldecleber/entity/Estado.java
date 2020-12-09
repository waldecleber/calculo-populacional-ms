package br.com.waldecleber.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String uf;
	
	private String bandeira;
	
	@JsonIgnore
	@OneToMany(mappedBy = "estado",fetch = FetchType.LAZY)
	private Set<Cidade> cidades;

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

	public Set<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Estado estado = (Estado) o;
		return Objects.equals(id, estado.id)
				&& Objects.equals(nome, estado.nome)
				&& Objects.equals(uf, estado.uf)
				&& Objects.equals(bandeira, estado.bandeira);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, uf, bandeira);
	}

	public static Builder builder() {
		return new Builder();
	}
	public static final class Builder {

		private Long id;
		private String nome;
		private String uf;
		private String bandeira;

		private Set<Cidade> cidades;

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

		public Builder cidades(Set<Cidade> cidades) {
			this.cidades = cidades;
			return this;
		}

		public Estado build() {
			Estado estado = new Estado();
			estado.setId(id);
			estado.setNome(nome);
			estado.setUf(uf);
			estado.setBandeira(bandeira);
			estado.setCidades(cidades);
			return estado;
		}
	}
}
