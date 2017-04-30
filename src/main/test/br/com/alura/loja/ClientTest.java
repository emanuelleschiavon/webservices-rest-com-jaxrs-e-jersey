package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClientTest {

	private HttpServer servidor;
	private Client client;
	private WebTarget target;

	@Before
	public void startaServidor() {
		servidor = Servidor.startaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080");
	}

	@After
	public void paraServidor() throws IOException {
		System.in.read();
		servidor.stop();
	}

	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		Carrinho carrinhoBuscado = target.path("/carrinhos").request().get(Carrinho.class);
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinhoBuscado.getRua());
	}

	@Test
	public void testaQueBuscarOCarrinhoUmTrazOCarrinhoEsperado() {
		Carrinho carrinhoBuscado = target.path("/carrinhos/1").request().get(Carrinho.class);
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinhoBuscado.getRua());
	}

	@Test
	public void testaSeOCarrinhoFoiAdicionado() {
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");

		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);
		Response response = target.path("/carrinhos").request().post(entity);

		assertEquals(201, response.getStatus());
	}

}
