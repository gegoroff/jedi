package br.com.guilherme.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class StarWarsWebClient {

	private WebClient client = WebClient.create("https://swapi.dev/api");

	private Mono<ClientResponse> result = client.get()
      .uri("/people/")
      .accept(MediaType.APPLICATION_JSON)
      .exchange();

	public String getResult() {
		return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
	}
}
