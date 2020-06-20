package br.com.guilherme.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class PlanetHandlerTest {

	WebTestClient client;

	@BeforeEach
	public void setUp(ApplicationContext context) {
		client = WebTestClient.bindToApplicationContext(context).build();
	}

	@Test
	public void saveTest() {
//		final Planet planet = new Planet();
//		planet.setName("Terra");
//		planet.setTerrain("Teste");
//		planet.setClimate("Teste2");
//		client.post().uri("/planet").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//				.body(Mono.just(planet), Planet.class).exchange().expectStatus().isOk();
	}

}
