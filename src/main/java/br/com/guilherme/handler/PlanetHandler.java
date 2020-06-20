package br.com.guilherme.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.guilherme.model.Planet;
import br.com.guilherme.repository.PlanetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PlanetHandler {

	@Autowired
	private PlanetRepository planetRepository;

	public Mono<ServerResponse> save(ServerRequest request) {
		return request.bodyToMono(Planet.class).doOnNext(planetRepository::save).flatMap(planet -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(planet)));
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<ServerResponse> findByName(ServerRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Flux<Planet> findAll(ServerRequest request) {
		// TODO Auto-generated method stub
		return planetRepository.findAll();
	}
}
