package rs.ac.uns.acs.smpuos.LoadBalancerServis.konfiguracija;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PrilagodjenaKonfiguracijaZaKorisnickiServis {

	@Bean
	@Primary
	ServiceInstanceListSupplier dobavljacListeServisInstanci() {
		return new DobavljacListeKorisnikServisInstanci("korisnik-servis");
	}
}
