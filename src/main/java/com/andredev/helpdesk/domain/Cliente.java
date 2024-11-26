package com.andredev.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	
	private List<Chamado> chamandos = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}

	public List<Chamado> getChamandos() {
		return chamandos;
	}

	public void setChamandos(List<Chamado> chamandos) {
		this.chamandos = chamandos;
	}
	
	

}
