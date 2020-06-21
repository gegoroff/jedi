[1mdiff --git a/src/main/java/br/com/guilherme/handler/StarWarsWebClient.java b/src/main/java/br/com/guilherme/handler/StarWarsWebClient.java[m
[1mindex 97c2859..61bfaa8 100644[m
[1m--- a/src/main/java/br/com/guilherme/handler/StarWarsWebClient.java[m
[1m+++ b/src/main/java/br/com/guilherme/handler/StarWarsWebClient.java[m
[36m@@ -1,21 +1,33 @@[m
 package br.com.guilherme.handler;[m
 [m
[32m+[m[32mimport java.util.concurrent.CompletableFuture;[m
[32m+[m
 import org.springframework.http.MediaType;[m
 import org.springframework.web.reactive.function.client.ClientResponse;[m
 import org.springframework.web.reactive.function.client.WebClient;[m
 [m
[32m+[m[32mimport br.com.guilherme.service.dto.PlanetDto;[m
 import reactor.core.publisher.Mono;[m
 [m
 public class StarWarsWebClient {[m
 [m
[31m-	private WebClient client = WebClient.create("https://swapi.dev/api");[m
[32m+[m	[32mprivate static final String BASE_URL = "https://swapi.dev/api";[m
 [m
[31m-	private Mono<ClientResponse> result = client.get()[m
[31m-      .uri("/people/")[m
[31m-      .accept(MediaType.APPLICATION_JSON)[m
[31m-      .exchange();[m
[32m+[m	[32mpublic CompletableFuture<PlanetDto> findPlanetByNameAndCountAppearances(String name) {[m
[32m+[m		[32mfinal WebClient client = WebClient.create(BASE_URL);[m
[32m+[m		[32mfinal Mono<ClientResponse> result = client.get().uri("/planet/?search=" + name)[m
[32m+[m				[32m.accept(MediaType.APPLICATION_JSON).exchange();[m
 [m
[31m-	public String getResult() {[m
[31m-		return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();[m
[32m+[m		[32mfinal CompletableFuture<PlanetDto> planetDto = result.flatMap(res -> res.bodyToMono(PlanetDto.class))[m
[32m+[m				[32m.toFuture();[m
[32m+[m		[32mreturn planetDto;[m
[32m+[m[32m//		planetDto.whenCompleteAsync((r, ex) -> {[m
[32m+[m[32m//			if (ex != null) {[m
[32m+[m[32m//				planetDto.completeExceptionally(ex);[m
[32m+[m[32m//			} else {[m
[32m+[m[32m//				planetDto.complete(r);[m
[32m+[m[32m//			}[m
[32m+[m[32m//		});[m
[32m+[m[32m//		return planetDto.thengetFilms().size();[m
 	}[m
 }[m
