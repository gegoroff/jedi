package br.com.guilherme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.guilherme.handler.PlanetHandler;

@Configuration
public class ApplicationConfig {

	@Bean
	public RouterFunction<ServerResponse> route(PlanetHandler planetHandler) {
		return RouterFunctions
//				.route(RequestPredicates.POST("/planet").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//						planetHandler::save)
				.route(RequestPredicates.GET("/planets"), planetHandler::findAll)
				.andRoute(RequestPredicates.GET("/planet/{id}"), planetHandler::findById)
				.andRoute(RequestPredicates.GET("/planet/filterByName/{name}"), planetHandler::findByName)
				.andRoute(RequestPredicates.GET("/planet/{id}/delete"), planetHandler::delete);
	}
}
