package br.com.guilherme.handler;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping(value = "/planet")
	public Mono<Planet> save(@RequestBody Planet planet) throws InterruptedException, ExecutionException {
		planet.setId(UUID.randomUUID().toString());
		final StarWarsWebClient client = new StarWarsWebClient();
		Mono<PlanetListDto> future = client.findPlanetByNameAndCountAppearances(planet.getName());
		CompletableFuture<Mono<Planet>> compl = future.toFuture().whenCompleteAsync((r, ex) -> {
			if (ex == null) {
				planet.setFilmAppearances(r.getResults().get(0).getFilms().length);
//				planetRepository.save(planet);
			}
		}).thenApplyAsync(e -> planetRepository.save(planet));
		return compl.get();

//		planet.setFilmAppearances();
//		return planetRepository.save(planet);

//		return request.bodyToMono(Planet.class).doFirst(planet -> {
//			planet.setId(UUID.randomUUID().toString());
//			planetRepository.save(planet);
//		}).flatMap(planet -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//				.body(BodyInserters.fromValue(planet)));
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

	public Mono<ServerResponse> findAll(ServerRequest request) {
//		return null;
		Flux<Planet> planets = planetRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(planets, Planet.class);
	}
}
