package rs.ac.uns.acs.smpuos.KonkursServis.kontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.KonkursServis.model.StudijskiProgramNaKonkursu;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IStudijskiProgramNaKonkursuServis;

@RestController
public class StudijskiProgramNaKonkursuKontroler {

	@Autowired
	private IStudijskiProgramNaKonkursuServis studijskiProgramNaKonkursuServis;
	
	@RequestMapping(value = "/get-studijski-program-na-konkursu", method = RequestMethod.GET)
	public Optional<StudijskiProgramNaKonkursu> getStudijskiProgramNaKonkursu(
	@RequestParam(name = "id", required = true) String id) {
		return studijskiProgramNaKonkursuServis.findById(id);
	}
	
	@RequestMapping(value = "/get-studijski-programi-na-konkursima", method = RequestMethod.GET)
	public List<StudijskiProgramNaKonkursu> getStudijskiProgramiNaKonkursima() {
		return studijskiProgramNaKonkursuServis.findAll();
	}
	
	@RequestMapping(value = "/add-studijski-program-na-konkursu", method = RequestMethod.POST)
	public void addStudijskiProgramNaKonkursu(@RequestBody StudijskiProgramNaKonkursu studijskiProgramNaKonkursu) {
		studijskiProgramNaKonkursuServis.insert(studijskiProgramNaKonkursu);
	}
	
	@RequestMapping(value = "/add-studijski-programi-na-konkursima", method = RequestMethod.POST)
	public void addStudijskiProgramiNaKonkursima(@RequestBody List<StudijskiProgramNaKonkursu> studijskiProgramiNaKonkursima) {
		studijskiProgramNaKonkursuServis.insertAll(studijskiProgramiNaKonkursima);
	}
}
