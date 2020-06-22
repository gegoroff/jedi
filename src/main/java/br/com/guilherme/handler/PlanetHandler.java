package br.com.guilherme.handler;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.guilherme.model.Planet;
import br.com.guilherme.repository.PlanetRepository;
import br.com.guilherme.service.dto.PlanetListDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PlanetHandler {

	@Autowired
	private PlanetRepository planetRepository;

	public Mono<ServerResponse> save(ServerRequest request) {
		final Mono<Planet> monoPlanet = request.bodyToMono(Planet.class).flatMap(planet -> {
			final StarWarsWebClient client = new StarWarsWebClient();
			Mono<PlanetListDto> monoDto = client.findPlanetByName(planet.getName());
			return monoDto.flatMap(dto -> {
				if (dto.getCount() > 0) {
					planet.setFilmAppearances(dto.getResults().get(0).getFilms().length);
				} else {
					planet.setFilmAppearances(0);
				}
				planet.setId(UUID.randomUUID().toString());
				return planetRepository.save(planet);
			});
		});
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(monoPlanet, Planet.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		final Mono<Planet> planet = planetRepository.findById(request.pathVariable("id"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(planet, Planet.class);
	}

	public Mono<ServerResponse> findByName(ServerRequest request) {
		final Mono<Planet> planets = planetRepository.findByName(request.pathVariable("name"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(planets, Planet.class);
	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		final Mono<Void> deleted = planetRepository.deleteById(request.pathVariable("id"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(deleted, Void.class);
	}

	public Mono<ServerResponse> findAll(ServerRequest request) {
		final Flux<Planet> planets = planetRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(planets, Planet.class);
	}
}
