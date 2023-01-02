package rs.ac.uns.acs.smpuos.LoadBalancerServis.konfiguracija;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;

import reactor.core.publisher.Flux;

public class DobavljacListeKorisnikServisInstanci implements ServiceInstanceListSupplier {

	private String servisId;

	public DobavljacListeKorisnikServisInstanci(String servisId) {
		this.servisId = servisId;
	}

	@Override
	public String getServiceId() {
		return servisId;
	}

	@Override
	public Flux<List<ServiceInstance>> get() {
		return Flux.just(Arrays.asList(
			new DefaultServiceInstance(servisId + "1", servisId, "localhost", 8080, false),
			new DefaultServiceInstance(servisId + "3", servisId, "localhost", 8082, false)));
	}
}

