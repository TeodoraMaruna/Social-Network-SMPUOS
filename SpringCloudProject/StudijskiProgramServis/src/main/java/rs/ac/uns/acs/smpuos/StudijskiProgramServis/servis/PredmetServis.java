package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Predmet;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum.PredmetRepozitorijum;

@Service
public class PredmetServis implements IPredmetServis {
	
	@Autowired
	PredmetRepozitorijum predmetRepozitorijum;
	
	public Optional<Predmet> findById(String id) {
		return predmetRepozitorijum.findById(id);
	}
	
	public List<Predmet> findAll() {
		return predmetRepozitorijum.findAll();
	}

	public void insert(Predmet predmet) {
		predmetRepozitorijum.insert(predmet);
	}
	
	public void insertAll(List<Predmet> predmeti) {
		predmetRepozitorijum.insert(predmeti);
	}

	public void deleteAll() {
		predmetRepozitorijum.deleteAll();
	}
}
