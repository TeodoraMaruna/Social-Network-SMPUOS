package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Predmet;

public interface IPredmetServis {

	Optional<Predmet> findById(String id);
	
	List<Predmet> findAll();
	
	void insert(Predmet predmet);
	
	void insertAll(List<Predmet> predmeti);
	
	void deleteAll();
}
