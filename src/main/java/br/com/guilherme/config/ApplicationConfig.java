package br.com.guilherme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.guilherme.handler.PlanetHandler;

@Configuration
@EnableReactiveCassandraRepositories
public class ApplicationConfig /* extends AbstractReactiveCassandraConfiguration */ {

//	@Value("${cassandra.contactpoints}")
//	private String contactPoints;
//
//	@Value("${cassandra.port}")
//	private int port;
//
//	@Value("${cassandra.keyspace}")
//	private String keyspace;

	@Bean
	public RouterFunction<ServerResponse> route(PlanetHandler planetHandler) {
		return RouterFunctions
//				.route(RequestPredicates.POST("/planet").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//						planetHandler::save)
				.route(RequestPredicates.GET("/planets"), planetHandler::findAll);
	}

//	@Override
//	protected String getKeyspaceName() {
//		return keyspace;
//	}
//
//	@Override
//	protected String getContactPoints() {
//		return contactPoints;
//	}
//
//	@Override
//	protected int getPort() {
//		return port;
//	}
}
