package com.andredev.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andredev.helpdesk.domain.Chamado;
import com.andredev.helpdesk.domain.Cliente;
import com.andredev.helpdesk.domain.Tecnico;
import com.andredev.helpdesk.domain.enums.Perfil;
import com.andredev.helpdesk.domain.enums.Prioridade;
import com.andredev.helpdesk.domain.enums.Status;
import com.andredev.helpdesk.repositories.ChamadoRepository;
import com.andredev.helpdesk.repositories.ClienteRepository;
import com.andredev.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpDeskApplication implements CommandLineRunner{

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico t1 = new Tecnico(null, "André", "81760248215", "andrelusantana@hotmail.com", "123");
		t1.addPerfil(Perfil.ADMIN);
		
		Cliente c1 = new Cliente(null, "Karleane", "8176028215", "karleane@hotmail.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", c1, t1); 
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}

}
