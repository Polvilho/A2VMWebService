package com.webservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.webservice.exception.NoContentException;
import com.webservice.model.Usuario;
import com.webservice.model.UsuarioBusiness;

@Path ("/usuario")
public class UsuarioResource {
	
	@GET
	@Produces("text/plain")
	public String testeWs() {
		return "Webservice A2VM usuario OK";
	}
	
	@GET
	@Path ("/{usuario}")
	@Produces ("application/json")
	public Usuario getUsuario (@PathParam("usuario") String user) {
		Usuario usuario = new UsuarioBusiness().buscar(user);
		
		if (usuario == null)
			throw new NoContentException("Registro não encontrado !");
		
	return usuario;
	}
	
}
