package br.com.guilherme.handler;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.guilherme.service.dto.PlanetListDto;
import reactor.core.publisher.Mono;

public class StarWarsWebClientTest {

	@Test
	public void findPlanetByNametest() {
		final StarWarsWebClient gwc = new StarWarsWebClient();
		final Mono<PlanetListDto> monoDto = gwc.findPlanetByName("Yavin IV");

		final PlanetListDto planetListDto = monoDto.block();
		Assert.assertEquals(Integer.valueOf(1), planetListDto.getCount());
		Assert.assertEquals(1, planetListDto.getResults().get(0).getFilms().length);
	}

	@Test
	public void findAllPlanetsTest() {
		final StarWarsWebClient gwc = new StarWarsWebClient();
		Mono<PlanetListDto> findAllPlanets = gwc.findAllPlanets();
		PlanetListDto planetListDto = findAllPlanets.block();
		Assert.assertNotNull(planetListDto.getCount());
		Assert.assertNotNull(planetListDto.getResults().get(0).getName());
	}

}
