package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Kandidat;
import rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum.KandidatRepozitorijum;

@Service
public class KandidatServis implements IKandidatServis {

	@Autowired
	KandidatRepozitorijum kandidatRepozitorijum;
	
	public Optional<Kandidat> findById(String id) {
		return kandidatRepozitorijum.findById(id);
	}
	
	public List<Kandidat> findAll() {
		return kandidatRepozitorijum.findAll();
	}

	public void insert(Kandidat kandidat) {
		kandidatRepozitorijum.insert(kandidat);
	}
	
	public void insertAll(List<Kandidat> kandidati) {
		kandidatRepozitorijum.insert(kandidati);
	}

	public void deleteAll() {
		kandidatRepozitorijum.deleteAll();
	}
}
