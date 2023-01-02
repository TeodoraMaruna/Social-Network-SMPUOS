package rs.ac.uns.acs.smpuos.EurekaServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServisAplikacija {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServisAplikacija.class, args);
	}
}
