package rs.ac.uns.acs.smpuos.LoadBalancerServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoadBalancerServisAplikacija {
    
	public static void main(String[] args) {
		SpringApplication.run(LoadBalancerServisAplikacija.class, args);
	}
}
