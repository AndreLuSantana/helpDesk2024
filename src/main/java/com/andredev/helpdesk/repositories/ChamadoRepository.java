package com.andredev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andredev.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{ //classe reponsável de relizar a persistencia no DB

}
