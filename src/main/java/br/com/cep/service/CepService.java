package br.com.cep.service;

import java.util.List;

import br.com.cep.model.Cep;

public interface CepService {

	public Cep buscarCep(String cep);
	
	public List<Cep> listarCeps(String ibge, String uf);
}
