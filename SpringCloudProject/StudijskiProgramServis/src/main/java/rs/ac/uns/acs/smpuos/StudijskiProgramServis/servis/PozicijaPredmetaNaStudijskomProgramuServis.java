package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.PozicijaPredmetaNaStudijskomProgramu;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum.PozicijaPredmetaNaStudijskomProgramuRepozitorijum;

@Service
public class PozicijaPredmetaNaStudijskomProgramuServis implements IPozicijaPredmetaNaStudijskomProgramuServis {
	
	@Autowired
	PozicijaPredmetaNaStudijskomProgramuRepozitorijum pozicijaPredmetaNaStudijskomProgramuRepozitorijum;
	
	public Optional<PozicijaPredmetaNaStudijskomProgramu> findById(String id) {
		return pozicijaPredmetaNaStudijskomProgramuRepozitorijum.findById(id);
	}
	
	public List<PozicijaPredmetaNaStudijskomProgramu> findAll() {
		return pozicijaPredmetaNaStudijskomProgramuRepozitorijum.findAll();
	}

	public void insert(PozicijaPredmetaNaStudijskomProgramu pozicijaPredmetaNaStudijskomProgramu) {
		pozicijaPredmetaNaStudijskomProgramuRepozitorijum.insert(pozicijaPredmetaNaStudijskomProgramu);
	}
	
	public void insertAll(List<PozicijaPredmetaNaStudijskomProgramu> pozicijePredmetaNaStudijskimProgramima) {
		pozicijaPredmetaNaStudijskomProgramuRepozitorijum.insert(pozicijePredmetaNaStudijskimProgramima);
	}

	public void deleteAll() {
		pozicijaPredmetaNaStudijskomProgramuRepozitorijum.deleteAll();
	}
	
	public List<PozicijaPredmetaNaStudijskomProgramu> findByStudijskiProgramId(String studijskiProgramId) {
		return pozicijaPredmetaNaStudijskomProgramuRepozitorijum.findByStudijskiProgramId(studijskiProgramId);
	};
}
