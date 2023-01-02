package rs.ac.uns.acs.smpuos.KorisnikServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KorisnikServisAplikacija {

	public static void main(String[] args) {
		SpringApplication.run(KorisnikServisAplikacija.class, args);
	}
}
