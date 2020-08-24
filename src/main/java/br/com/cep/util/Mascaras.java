package br.com.cep.util;

public class Mascaras {
	
	public static String inserirMascaraCep(String cep) {
		return cep.substring(0, 5) + "-" + cep.substring(5, cep.length());
	}
	
	public static String removerMascaraCep(String cep) {
		return cep.replace("-", "");
	}
}
