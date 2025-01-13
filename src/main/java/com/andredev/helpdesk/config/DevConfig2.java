package com.andredev.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andredev.helpdesk.services.DBService;

import jakarta.annotation.PostConstruct;

@Configuration //classe de configuração
@Profile("dev2") //estabelece o perfil da classe
public class DevConfig2 {

	@Autowired
	private DBService dbService;

    //para o metodo ser chamado ao rodar o programa
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
    
	//@PostConstruct
    //public boolean instanciaDB() {
		
	//	if (value.equals("update")) {
	//		this.dbService.instanciaDB();
	//	}
	//	return false;
	//}
}
