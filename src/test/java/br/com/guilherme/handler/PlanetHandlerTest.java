package br.com.guilherme.handler;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.guilherme.config.ApplicationConfig;
import br.com.guilherme.model.Planet;
import br.com.guilherme.repository.PlanetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest({ PlanetHandler.class, ApplicationConfig.class })
public class PlanetHandlerTest {

	@MockBean
	PlanetRepository planetRepository;

	WebTestClient client;

	@BeforeEach
	public void setUp(ApplicationContext context) {
		client = WebTestClient.bindToApplicationContext(context).build();
	}

	@Test
	public void planetShouldBeSavedTest() {
		final Planet planetSaved = new Planet();
		planetSaved.setName("Tatooine");
		planetSaved.setTerrain("Planicies");
		planetSaved.setClimate("Tropical");
		planetSaved.setFilmAppearances(5);

		Mockito.when(planetRepository.save(ArgumentMatchers.any())).thenReturn(Mono.just(planetSaved));

		final Planet planet = new Planet();
		planet.setName("Tatooine");
		planet.setTerrain("Planicies");
		planet.setClimate("Tropical");
		client.post().uri("/planet").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(planet), Planet.class).exchange().expectStatus().isOk().expectBody().jsonPath("$.id")
				.isNotEmpty().jsonPath("$.name").isEqualTo("Tatooine").jsonPath("$.filmAppearances").isNotEmpty();
	}

	@Test
	public void planetShouldBeFoundByIdTest() {
		final Planet planet = new Planet();
		planet.setId("cc4de310-15b3-4c74-a471-65f6d827ea25");
		planet.setName("Tatooine");
		planet.setTerrain("Planicies");
		planet.setClimate("Tropical");
		Mockito.when(planetRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(planet));

		client.get().uri("/planet/cc4de310-15b3-4c74-a471-65f6d827ea25").accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk().expectBody().jsonPath("$.name").isEqualTo("Tatooine");
	}

	@Test
	public void planetShouldBeFoundByNameTest() {
		final Planet planet = new Planet();
		planet.setId("cc4de310-15b3-4c74-a471-65f6d827ea25");
		planet.setName("Tatooine");
		planet.setTerrain("Planicies");
		planet.setClimate("Tropical");

		Mockito.when(planetRepository.findByName(ArgumentMatchers.anyString())).thenReturn(Mono.just(planet));

		client.get().uri("/planet/filterByName/Tatooine").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk().expectBody().jsonPath("$.id").isEqualTo("cc4de310-15b3-4c74-a471-65f6d827ea25")
				.jsonPath("$.name").isEqualTo("Tatooine");
	}

	@Test
	public void planetShouldBeDeletedTest() {

		Mockito.when(planetRepository.deleteById(ArgumentMatchers.anyString())).thenReturn(Mono.empty());

		client.get().uri("/planet/cc4de310-15b3-4c74-a471-65f6d827ea25/delete").accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk();
	}

	@Test
	public void shouldListAllPlanetsTest() {
		final Planet planet = new Planet();
		planet.setId("cc4de310-15b3-4c74-a471-65f6d827ea25");
		planet.setName("Tatooine");
		planet.setTerrain("Planicies");
		planet.setClimate("Tropical");
		planet.setFilmAppearances(5);

		final Planet planet2 = new Planet();
		planet2.setId("gtjy6767-15b3-4c74-a471-dfsfrt44556");
		planet2.setName("Alderaan");
		planet2.setTerrain("grasslands, mountains");
		planet2.setClimate("temperate");
		planet2.setFilmAppearances(2);

		Mockito.when(planetRepository.findAll()).thenReturn(Flux.just(planet, planet2));

		client.get().uri("/planets").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBodyList(Planet.class).hasSize(2).returnResult().getResponseBody()
				.containsAll(Arrays.asList(planet, planet2));
	}
}
