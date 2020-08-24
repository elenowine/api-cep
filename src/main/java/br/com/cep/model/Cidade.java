package br.com.cep.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TCidade")
public class Cidade {

	@Id
	@Column(name = "IBGE", length = 20)
	private String ibge;

	@Column(name = "UF", length = 2, nullable = false)
	private String uf;

	@Column(name = "LOCALIDADE", length = 100, nullable = false)
	private String localidade;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "IBGE")
	@JsonIgnore
	private List<Cep> ceps;

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public List<Cep> getCeps() {
		return ceps;
	}

	public void setCeps(List<Cep> ceps) {
		this.ceps = ceps;
	}

}
