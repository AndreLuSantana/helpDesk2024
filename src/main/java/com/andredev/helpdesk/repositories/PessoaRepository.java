package com.andredev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andredev.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	
}
