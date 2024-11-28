package com.andredev.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.andredev.helpdesk.domain.Chamado;
import com.andredev.helpdesk.domain.Cliente;
import com.andredev.helpdesk.domain.Tecnico;
import com.andredev.helpdesk.domain.enums.Perfil;
import com.andredev.helpdesk.domain.enums.Prioridade;
import com.andredev.helpdesk.domain.enums.Status;
import com.andredev.helpdesk.repositories.ChamadoRepository;
import com.andredev.helpdesk.repositories.ClienteRepository;
import com.andredev.helpdesk.repositories.TecnicoRepository;

@Service //classe de serviço
public class DBService {
	
	@Autowired //injeção de dependência
	private TecnicoRepository tecnicoRepository;
	@Autowired //injeção de dependência
	private ClienteRepository clienteRepository;
	@Autowired //injeção de dependência
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() { //metodo que realiza a carga inicial no DB para ateste
		
		Tecnico t1 = new Tecnico(null, "André", "294.120.000-22", "andrelusantana@hotmail.com", "123");
		t1.addPerfil(Perfil.ADMIN);
		Tecnico t2 = new Tecnico(null, "Agnes", "808.861.260-88", "andre@hotmail.com", "123");
		t2.addPerfil(Perfil.ADMIN);
		
		Cliente c1 = new Cliente(null, "Karleane", "194.146.530-74", "karleane@hotmail.com", "123");
		Cliente c2 = new Cliente(null, "Bianca", "141.019.420-56", "karle@hotmail.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", c1, t1);
		Chamado ch2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 02", "Primeiro Chamado", c2, t2);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
		
		tecnicoRepository.saveAll(Arrays.asList(t2));
		clienteRepository.saveAll(Arrays.asList(c2));
		chamadoRepository.saveAll(Arrays.asList(ch2));
	}
}
