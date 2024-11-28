package com.andredev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andredev.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{ //classe reponsável de relizar a persistencia no DB

}
