package com.andredev.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andredev.helpdesk.domain.Chamado;
import com.andredev.helpdesk.domain.Cliente;
import com.andredev.helpdesk.domain.Tecnico;
import com.andredev.helpdesk.domain.DTO.ChamadoDTO;
import com.andredev.helpdesk.domain.enums.Prioridade;
import com.andredev.helpdesk.domain.enums.Status;
import com.andredev.helpdesk.repositories.ChamadoRepository;
import com.andredev.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return chamadoRepository.save(newChamado(objDTO));
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {

		objDTO.setId(id);
		
		Chamado odlObj = findById(id);
		odlObj = newChamado(objDTO);
		
		return chamadoRepository.save(odlObj);
	}
	
	private Chamado newChamado (ChamadoDTO obj) {
		
		Tecnico Tecnico = tecnicoService.findByid(obj.getTecnico());
		Cliente cliente = clienteService.findByid(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(Tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
		
	}

	
}
