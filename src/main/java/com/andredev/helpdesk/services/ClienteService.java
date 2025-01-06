package com.andredev.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andredev.helpdesk.domain.Cliente;
import com.andredev.helpdesk.domain.Pessoa;
import com.andredev.helpdesk.domain.DTO.ClienteDTO;
import com.andredev.helpdesk.repositories.ClienteRepository;
import com.andredev.helpdesk.repositories.PessoaRepository;
import com.andredev.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.andredev.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findByid(Integer id) {

		Optional<Cliente> obj = ClienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!! Id: " + id));
	}

	public List<Cliente> findAll() {

		return ClienteRepository.findAll();

	}

	public Cliente create(ClienteDTO objDTO) {

		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));

		ValidaPorCPFeEmail(objDTO);

		Cliente newObj = new Cliente(objDTO);

		return ClienteRepository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {

		objDTO.setId(id);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		Cliente oldObj = findByid(id);
		ValidaPorCPFeEmail(objDTO);
		oldObj = new Cliente(objDTO);

		return ClienteRepository.save(oldObj);
	}
	
	

	private void ValidaPorCPFeEmail(ClienteDTO objDTO) {

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
		
		Cliente obj = findByid(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente selecinado tem chamados vinculados a ele! Não pode ser deletado.");
		}
		
		ClienteRepository.deleteById(id);
		
	}

}
