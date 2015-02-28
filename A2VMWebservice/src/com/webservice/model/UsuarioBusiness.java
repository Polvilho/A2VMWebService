package com.webservice.model;

import com.webservice.dao.UsuarioDAO;

public class UsuarioBusiness {
	
	public Usuario buscar(String user) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.buscar(user);
	}

}
