package br.com.cep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cep.model.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep, String> {

	List<Cep> findAllByCidade_Ibge(String ibge);

	List<Cep> findAllByCidade_IbgeAndCidade_Uf(String ibge, String uf);

}
