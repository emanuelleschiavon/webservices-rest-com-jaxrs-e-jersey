package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	private static HttpServer server;

	public static void main(String[] args) throws IOException {
		server = startaServidor();
		mataServidor();
	}

	static HttpServer startaServidor() {
		URI uri = URI.create("http://localhost:8080");
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Rodando servidor");
		return server;
	}
	
	static void mataServidor() throws IOException {
		System.in.read();
		server.stop();
	}
}
