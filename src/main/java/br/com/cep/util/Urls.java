package br.com.cep.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Urls {

	@Value("${viacep.link}")
	private String urlViaCep;

	public String getUrlViaCep() {
		return urlViaCep;
	}

	public void setUrlViaCep(String urlViaCep) {
		this.urlViaCep = urlViaCep;
	}
	
}
