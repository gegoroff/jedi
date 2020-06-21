package br.com.guilherme.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.guilherme.model.Planet;
import br.com.guilherme.repository.PlanetRepository;
import reactor.core.publisher.Mono;

@WebFluxTest({ PlanetHandler.class })
public class PlanetHandlerTest {

	@MockBean
	PlanetRepository planetRepository;

	WebTestClient client;

	@BeforeEach
	public void setUp(ApplicationContext context) {
		client = WebTestClient.bindToApplicationContext(context).build();
	}

	@Test
	public void saveTest() {

		Mockito.when(planetRepository.save(ArgumentMatchers.any())).thenReturn(Mono.just(new Planet()));

		final Planet planet = new Planet();
		planet.setName("Yavin IV");
		planet.setTerrain("Teste");
		planet.setClimate("Teste2");
		client.post().uri("/planet").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(planet), Planet.class).exchange().expectStatus().isOk();
	}

}
