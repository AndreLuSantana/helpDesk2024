package com.andredev.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andredev.helpdesk.services.DBService;

import jakarta.annotation.PostConstruct;

@Configuration // classe de configuração
@Profile("test") // estabelece o perfil da classe
public class TestConfig {

	@Autowired
	private DBService dbService;

	// para o metodo ser chamado ao rodar o programa

	@PostConstruct
	public void instanciaDB() {

		this.dbService.instanciaDB();

	}
}
