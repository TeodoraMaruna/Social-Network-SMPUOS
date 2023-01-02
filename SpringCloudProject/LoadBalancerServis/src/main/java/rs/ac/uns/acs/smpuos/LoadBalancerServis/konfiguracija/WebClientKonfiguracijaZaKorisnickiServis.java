package rs.ac.uns.acs.smpuos.LoadBalancerServis.konfiguracija;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClient(name = "korisnik-servis", configuration = PrilagodjenaKonfiguracijaZaKorisnickiServis.class)
//@LoadBalancerClient(name = "korisnik-servis")
public class WebClientKonfiguracijaZaKorisnickiServis {

	@Bean
	@LoadBalanced
	WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
