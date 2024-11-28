package com.andredev.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andredev.helpdesk.domain.Tecnico;
import com.andredev.helpdesk.repositories.TecnicoRepository;
import com.andredev.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findByid(Integer id) {
		
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado!! Id: " + id));
	}

	public List<Tecnico> findAll() {
		
		
		return tecnicoRepository.findAll();
		
	}
}
