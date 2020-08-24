package br.com.cep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cep.model.Cep;
import br.com.cep.service.CepService;

@RestController
@RequestMapping("/ceps")
public class CepsController {

	@Autowired
	private CepService cepService;
	
	@CrossOrigin
	@GetMapping
	public List<Cep> listarCeps(@RequestParam String ibge, String uf) {
		return cepService.listarCeps(ibge, uf);
	}
	
}
