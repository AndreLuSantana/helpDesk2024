package com.andredev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andredev.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
