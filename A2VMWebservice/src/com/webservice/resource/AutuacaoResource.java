package com.webservice.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.webservice.exception.NoContentException;
import com.webservice.model.Autuacao;
import com.webservice.model.AutuacaoBusiness;

@Path ("/autuacao")
public class AutuacaoResource {
	
	@GET
	@Produces("text/plain")
	public String testeWs() {
		return "Webservice A2VM autuacao OK";
	}
	
	@GET
	@Path ("/buscarTodos")
	@Produces ("application/json")
	public ArrayList<Autuacao> selTodos() {
		return new AutuacaoBusiness().buscarTodos();
	}
	
	@GET
	@Path ("/buscarTodosGSON")
	@Produces ("application/json")
	public String selTodosGSON() {
		return new Gson().toJson(new AutuacaoBusiness().buscarTodos());
	}
	
	@GET
	@Path ("/{placa}")
	@Produces ("application/json")
	public Autuacao getAutuacao (@PathParam("placa") String placa) {
		Autuacao autuacao = new AutuacaoBusiness().buscar(placa);
				
		if (autuacao == null)
			throw new NoContentException ("Registro não encontrado !");
		
	return autuacao;
	}
	
	@POST
	@Path ("/inserir")
	@Produces ("application/json")
	@Consumes ("application/json")
	public String inserirAutuacao (Autuacao autuacao) {
		return new AutuacaoBusiness().inserir(autuacao);
	}
	
}