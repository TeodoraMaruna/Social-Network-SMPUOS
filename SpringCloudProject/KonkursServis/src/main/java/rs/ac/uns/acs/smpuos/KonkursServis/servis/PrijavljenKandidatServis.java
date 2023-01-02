package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.KonkursServis.model.PrijavljenKandidat;
import rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum.PrijavljenKandidatRepozitorijum;

@Service
public class PrijavljenKandidatServis implements IPrijavljenKandidatServis {

	@Autowired
	PrijavljenKandidatRepozitorijum prijavljenKandidatRepozitorijum;
	
	public Optional<PrijavljenKandidat> findById(String id) {
		return prijavljenKandidatRepozitorijum.findById(id);
	}
	
	public List<PrijavljenKandidat> findAll() {
		return prijavljenKandidatRepozitorijum.findAll();
	}

	public void insert(PrijavljenKandidat prijavljenKandidat) {
		prijavljenKandidatRepozitorijum.insert(prijavljenKandidat);
	}
	
	public void insertAll(List<PrijavljenKandidat> prijavljeniKandidati) {
		prijavljenKandidatRepozitorijum.insert(prijavljeniKandidati);
	}

	public void deleteAll() {
		prijavljenKandidatRepozitorijum.deleteAll();
	}
	
	public List<PrijavljenKandidat> findByStudijskiProgramNaKonkursuId(String studijskiProgramNaKonkursuId) {
		return prijavljenKandidatRepozitorijum.findByStudijskiProgramNaKonkursuId(studijskiProgramNaKonkursuId);
	}
}
