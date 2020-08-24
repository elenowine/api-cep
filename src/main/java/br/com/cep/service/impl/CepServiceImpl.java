package br.com.cep.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cep.model.Cep;
import br.com.cep.model.Cidade;
import br.com.cep.repository.CepRepository;
import br.com.cep.repository.CidadeRepository;
import br.com.cep.service.CepService;
import br.com.cep.service.dto.RetornoViaCep;
import br.com.cep.util.Urls;

@Service
@Transactional
public class CepServiceImpl implements CepService {

	@Autowired
	private CepRepository cepRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private Urls urls;

	@Override
	public Cep buscarCep(String filtro) {
		if (filtro.length() != 8) {
			throw new RuntimeException("O cep informado deve possuir 8 dígitos");
		}
		
		Cep cep = new Cep();
		
		Optional<Cep> opt = cepRepository.findById(filtro);
		
		if (opt.isEmpty()) {
			RetornoViaCep retornoViaCep = consultarViaCep(filtro);
			
			cep.setBairro(retornoViaCep.getBairro());
			cep.setCep(retornoViaCep.getCep());
			cep.setComplemento(retornoViaCep.getComplemento());
			cep.setLogradouro(retornoViaCep.getLogradouro());
			
			Optional<Cidade> optCidade = cidadeRepository.findById(retornoViaCep.getIbge());
			
			if (optCidade.isEmpty()) {
				throw new RuntimeException("Cidade de IBGE valor " + retornoViaCep.getIbge() + " não encontrada na base");
			}
			
			cep.setCidade(optCidade.get());
			
			cep = cepRepository.saveAndFlush(cep);
		} else {
			cep = opt.get();
		}
		
		return cep;
	}

	@Override
	public List<Cep> listarCeps(String ibge, String uf) {
		List<Cep> ceps = new ArrayList<>();
		
		if (uf == null) {
			ceps = cepRepository.findAllByCidade_Ibge(ibge);
		} else {
			ceps = cepRepository.findAllByCidade_IbgeAndCidade_Uf(ibge, uf);
		}
		
		return ceps;
	}

	private RetornoViaCep consultarViaCep(String cep) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();
		String url = urls.getUrlViaCep() + cep + "/json/";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		HttpEntity<?> request = new HttpEntity<>(headers);
		HttpEntity<RetornoViaCep> response;

		try {
			response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, RetornoViaCep.class);
			return response.getBody();
		} catch (HttpStatusCodeException erro) {
			erro.printStackTrace();
			throw new RuntimeException("Falha na comunicação com o ViaCep");
		}

	}

}
