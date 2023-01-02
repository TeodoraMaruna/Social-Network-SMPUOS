package rs.ac.uns.acs.smpuos.GatewayServis.kontroler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class GatewayServisKontroler {

	@RequestMapping("/korisnik-servis-circuit-breaker")
	public Mono<String> korisnikServisCircuitBreaker() {
		return Mono.just("Кориснички сервис биће ускоро доступан.");
	}
}
