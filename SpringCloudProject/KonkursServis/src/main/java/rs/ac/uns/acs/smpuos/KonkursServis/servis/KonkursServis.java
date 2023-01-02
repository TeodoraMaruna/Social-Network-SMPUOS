package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Konkurs;
import rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum.KonkursRepozitorijum;

@Service
public class KonkursServis implements IKonkursServis {

	@Autowired
	KonkursRepozitorijum konkursRepozitorijum;
	
	public Optional<Konkurs> findById(String id) {
		return konkursRepozitorijum.findById(id);
	}
	
	public List<Konkurs> findAll() {
		return konkursRepozitorijum.findAll();
	}

	public void insert(Konkurs konkurs) {
		konkursRepozitorijum.insert(konkurs);
	}
	
	public void insertAll(List<Konkurs> konkursi) {
		konkursRepozitorijum.insert(konkursi);
	}

	public void deleteAll() {
		konkursRepozitorijum.deleteAll();
	}
}
