package br.com.guilherme.handler;

import org.junit.jupiter.api.Test;

import br.com.guilherme.service.dto.PlanetListDto;
import reactor.core.publisher.Mono;

class StarWarsWebClientTest {

	@Test
	void test() {
		StarWarsWebClient gwc = new StarWarsWebClient();
		Mono<PlanetListDto> completable = gwc.findPlanetByNameAndCountAppearances("Yavin IV");
		System.out.println(completable.block().getResults().stream().findFirst().get().getFilms().length);
	}

}
