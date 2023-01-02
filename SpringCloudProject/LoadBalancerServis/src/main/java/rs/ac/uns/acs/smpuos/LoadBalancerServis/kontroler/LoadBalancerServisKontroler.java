package rs.ac.uns.acs.smpuos.LoadBalancerServis.kontroler;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class LoadBalancerServisKontroler {

	private WebClient.Builder loadBalancerWebClientBuilder;
	private ReactorLoadBalancerExchangeFilterFunction loadBalancerFunkcija;

	public LoadBalancerServisKontroler(WebClient.Builder loadBalancerWebClientBuilder, ReactorLoadBalancerExchangeFilterFunction loadBalancerFunkcija) {
		this.loadBalancerWebClientBuilder = loadBalancerWebClientBuilder;
		this.loadBalancerFunkcija = loadBalancerFunkcija;
	}
	
	//Opcija 1: upotreba load balancer web klijenta
	@RequestMapping("/pozdrav")
	public Mono<String> pozdrav(@RequestParam(value = "ime", defaultValue = "Јована") String ime) {
		return loadBalancerWebClientBuilder
			.build().get().uri("http://korisnik-servis/pozdrav")
			.retrieve().bodyToMono(String.class)
			.map(pozdrav -> String.format("%s, %s!", pozdrav, ime));
	}

	//Opcija 2: upotreba web klijenta sa funkcijom load balancer-a
	//Isti rezultat ce biti ostvaren kao sa opcijom 1
	@RequestMapping("/pozdrav-sa-funkcijom")
	public Mono<String> pozdravSaFunkcijom(@RequestParam(value = "ime", defaultValue = "Ивана") String ime) {
		return WebClient.builder()
			.filter(loadBalancerFunkcija)
			.build().get().uri("http://korisnik-servis/pozdrav")
			.retrieve().bodyToMono(String.class)
			.map(pozdrav -> String.format("%s, %s!", pozdrav, ime));
	}
}
