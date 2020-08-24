package br.com.cep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TCep")
public class Cep {

	@Id
	@Column(name = "CEP", length = 10)
	private String cep;

	@Column(name = "LOGRADOURO", length = 255, nullable = false)
	private String logradouro;

	@Column(name = "COMPLEMENTO", length = 255)
	private String complemento;

	@Column(name = "BAIRRO", length = 50)
	private String bairro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IBGE")
	private Cidade cidade;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
