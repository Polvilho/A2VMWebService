package com.webservice.model;

import java.util.ArrayList;

import com.webservice.dao.AutuacaoDAO;

public class AutuacaoBusiness {
	
	public ArrayList<Autuacao> buscarTodos() {
		
		AutuacaoDAO autuacaoDAO = new AutuacaoDAO();
		return autuacaoDAO.buscarTodos();
	}
	
	public String inserir(Autuacao autuacao) {
		
		AutuacaoDAO autuacaoDAO = new AutuacaoDAO();
		
		if (autuacaoDAO.inserir(autuacao) > 0) {
			return "Autuacao registrada no banco com sucesso !";
		}
		else {
			return "Falha ao registrar a autuacao no banco !";
		}
	}
	
	public Autuacao buscar(String placa) {
		
		AutuacaoDAO autuacaoDAO = new AutuacaoDAO();
		return autuacaoDAO.buscar(placa);
	}
	
}
