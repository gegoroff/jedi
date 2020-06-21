package br.com.guilherme.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import br.com.guilherme.model.Planet;
import reactor.core.publisher.Mono;

@Repository
public interface PlanetRepository extends ReactiveCassandraRepository<Planet, Integer> {

//	Mono<Planet> save(Planet planet);
//
//	Mono<Planet> findById(String id);

	@AllowFiltering
	Mono<Planet> findByName(String name);

//	Mono<Void> delete(String id);

//	Flux<List<Planet>> findAll();
}
