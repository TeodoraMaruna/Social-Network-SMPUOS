package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Kandidat;

public interface IKandidatServis {

	Optional<Kandidat> findById(String id);
	
	List<Kandidat> findAll();
	
	void insert(Kandidat kandidat);
	
	void insertAll(List<Kandidat> kandidati);
	
	void deleteAll();
}
