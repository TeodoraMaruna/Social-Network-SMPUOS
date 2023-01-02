package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.KonkursServis.model.StudijskiProgramNaKonkursu;
import rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum.StudijskiProgramNaKonkursuRepozitorijum;

@Service
public class StudijskiProgramNaKonkursuServis implements IStudijskiProgramNaKonkursuServis {

	@Autowired
	StudijskiProgramNaKonkursuRepozitorijum studijskiProgramNaKonkursuRepozitorijum;
	
	public Optional<StudijskiProgramNaKonkursu> findById(String id) {
		return studijskiProgramNaKonkursuRepozitorijum.findById(id);
	}
	
	public List<StudijskiProgramNaKonkursu> findAll() {
		return studijskiProgramNaKonkursuRepozitorijum.findAll();
	}

	public void insert(StudijskiProgramNaKonkursu studijskiProgramNaKonkursu) {
		studijskiProgramNaKonkursuRepozitorijum.insert(studijskiProgramNaKonkursu);
	}
	
	public void insertAll(List<StudijskiProgramNaKonkursu> studijskiProgramiNaKonkursima) {
		studijskiProgramNaKonkursuRepozitorijum.insert(studijskiProgramiNaKonkursima);
	}

	public void deleteAll() {
		studijskiProgramNaKonkursuRepozitorijum.deleteAll();
	}
}
