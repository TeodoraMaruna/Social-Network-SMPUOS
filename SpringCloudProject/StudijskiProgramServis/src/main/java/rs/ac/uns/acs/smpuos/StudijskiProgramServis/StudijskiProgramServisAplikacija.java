package rs.ac.uns.acs.smpuos.StudijskiProgramServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StudijskiProgramServisAplikacija {

	public static void main(String[] args) {
		SpringApplication.run(StudijskiProgramServisAplikacija.class, args);
	}
}
