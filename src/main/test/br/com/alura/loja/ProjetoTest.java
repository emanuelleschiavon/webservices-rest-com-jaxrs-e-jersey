package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {
	private HttpServer servidor;

	@Before
	public void startaServidor() {
		servidor = Servidor.startaServidor();
	}

	@After
	public void paraServidor() throws IOException {
		servidor.stop();
	}

	@Test
	public void buscaPrimeiroProjetoTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		Projeto projetoBuscado = target.path("projetos").request().get(Projeto.class);
		assertEquals("Minha loja", projetoBuscado.getNome());
	}
}
