package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Pozicija;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum.PozicijaRepozitorijum;

@Service
public class PozicijaServis implements IPozicijaServis {
	
	@Autowired
	PozicijaRepozitorijum pozicijaRepozitorijum;
	
	public Optional<Pozicija> findById(String id) {
		return pozicijaRepozitorijum.findById(id);
	}
	
	public List<Pozicija> findAll() {
		return pozicijaRepozitorijum.findAll();
	}

	public void insert(Pozicija pozicija) {
		pozicijaRepozitorijum.insert(pozicija);
	}
	
	public void insertAll(List<Pozicija> pozicije) {
		pozicijaRepozitorijum.insert(pozicije);
	}

	public void deleteAll() {
		pozicijaRepozitorijum.deleteAll();
	}
}
