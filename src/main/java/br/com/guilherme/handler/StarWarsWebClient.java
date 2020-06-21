package br.com.guilherme.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.guilherme.service.dto.PlanetListDto;
import reactor.core.publisher.Mono;

public class StarWarsWebClient {

	private static final String BASE_URL = "https://swapi.dev/api";

	public Mono<PlanetListDto> findPlanetByNameAndCountAppearances(String name) {
		final WebClient client = WebClient.create(BASE_URL);
		return client.get().uri("/planets/?search=" + name).accept(MediaType.APPLICATION_JSON).exchange()
				.flatMap(res -> res.bodyToMono(PlanetListDto.class));
	}
}
