package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Pozicija;

public interface IPozicijaServis {

	Optional<Pozicija> findById(String id);
	
	List<Pozicija> findAll();
	
	void insert(Pozicija pozicija);
	
	void insertAll(List<Pozicija> pozicije);
	
	void deleteAll();
}
