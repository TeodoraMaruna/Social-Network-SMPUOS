package rs.ac.uns.acs.smpuos.KonkursServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KonkursServisAplikacija {

	public static void main(String[] args) {
		SpringApplication.run(KonkursServisAplikacija.class, args);
	}
}
