package br.com.cep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cep.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, String> {

}
