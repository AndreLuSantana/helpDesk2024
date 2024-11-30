package com.andredev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andredev.helpdesk.domain.Pessoa;
import java.util.List;
import java.util.Optional;



public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{ //classe reponsável de relizar a persistencia no DB

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
	
}
