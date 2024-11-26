package com.andredev.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.andredev.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamandos = new ArrayList<>();

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamandos() {
		return chamandos;
	}

	public void setChamandos(List<Chamado> chamandos) {
		this.chamandos = chamandos;
	}
	
	

}
