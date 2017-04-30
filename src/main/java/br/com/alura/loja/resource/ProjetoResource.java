package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	@GET
	@Path("xml/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Projeto buscaPorIdXml(@PathParam("id") long id) {
		Projeto projetoBuscado = new ProjetoDAO().busca(id);
		return projetoBuscado;
	}

	@GET
	@Path("json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Projeto buscaPorIdJson(@PathParam("id") long id) {
		Projeto projetoBuscado = new ProjetoDAO().busca(id);
		return projetoBuscado;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI location = URI.create("/projetos/" + projeto.getId());
		return Response.created(location).build();
	}
	
	@DELETE
	public Response remove(@PathParam("id") long id){
		new ProjetoDAO().remove(id);
		return Response.ok().build();
	}

}
