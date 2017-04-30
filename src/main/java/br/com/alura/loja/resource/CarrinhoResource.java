package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("/carrinhos")
public class CarrinhoResource {

	@GET
	@Path("/xml/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho buscaPorIdXml(@PathParam("id") long id) {
		Carrinho carrinhoBuscado = new CarrinhoDAO().busca(id);
		return carrinhoBuscado;
	}

	@GET
	@Path("/json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho buscaPorIdJson(@PathParam("id") long id) {
		Carrinho carrinhoBuscado = new CarrinhoDAO().busca(id);
		return carrinhoBuscado;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {
		new CarrinhoDAO().adiciona(carrinho);
		URI location = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(location).build();
	}

	@Path("/{id}/produtos/{idProduto}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("idProduto") long idProduto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(idProduto);

		return Response.ok().build();
	}

	@Path("/{id}/produtos/{idProduto}")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response atualizaProduto(Produto produto, @PathParam("id") long id, @PathParam("idProduto") long idProduto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.troca(produto);

		return Response.ok().build();
	}
	
	@Path("/{id}/produtos/{idProduto}/quantidade")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response atualizaQuantidade(Produto produto, @PathParam("id") long id, @PathParam("idProduto") long idProduto){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(produto);
		
		return Response.ok().build();
	}
}
