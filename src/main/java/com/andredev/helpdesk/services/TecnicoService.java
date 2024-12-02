package com.andredev.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.andredev.helpdesk.domain.Pessoa;
import com.andredev.helpdesk.domain.Tecnico;
import com.andredev.helpdesk.domain.DTO.TecnicoDTO;
import com.andredev.helpdesk.repositories.PessoaRepository;
import com.andredev.helpdesk.repositories.TecnicoRepository;
import com.andredev.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.andredev.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findByid(Integer id) {

		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!! Id: " + id));
	}

	public List<Tecnico> findAll() {

		return tecnicoRepository.findAll();

	}

	public Tecnico create(TecnicoDTO objDTO) {

		objDTO.setId(null);

		ValidaPorCPFeEmail(objDTO);

		Tecnico newObj = new Tecnico(objDTO);

		return tecnicoRepository.save(newObj);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {

		objDTO.setId(id);
		Tecnico oldObj = findByid(id);
		ValidaPorCPFeEmail(objDTO);
		oldObj = new Tecnico(objDTO);

		return tecnicoRepository.save(oldObj);
	}
	
	

	private void ValidaPorCPFeEmail(TecnicoDTO objDTO) {

		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {

			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {

			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
		}
	}

	public void delete(Integer id) {
		
		Tecnico obj = findByid(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico selecinado tem chamados vinculados a ele! Não pode ser deletado.");
		}
		
		tecnicoRepository.deleteById(id);
		
	}

}
