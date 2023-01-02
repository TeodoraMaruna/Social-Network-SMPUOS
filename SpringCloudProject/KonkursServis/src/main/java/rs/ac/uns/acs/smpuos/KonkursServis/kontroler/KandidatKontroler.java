package rs.ac.uns.acs.smpuos.KonkursServis.kontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Kandidat;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IKandidatServis;

@RestController
public class KandidatKontroler {

	@Autowired
	private IKandidatServis kandidatServis;
	
	@RequestMapping(value = "/get-kandidat", method = RequestMethod.GET)
	public Optional<Kandidat> getKandidat(
	@RequestParam(name = "id", required = true) String id) {
		return kandidatServis.findById(id);
	}
	
	@RequestMapping(value = "/get-kandidati", method = RequestMethod.GET)
	public List<Kandidat> getKandidati() {
		return kandidatServis.findAll();
	}
	
	@RequestMapping(value = "/add-kandidat", method = RequestMethod.POST)
	public void addKandidat(@RequestBody Kandidat kandidat) {
		kandidatServis.insert(kandidat);
	}
	
	@RequestMapping(value = "/add-kandidati", method = RequestMethod.POST)
	public void addKandidati(@RequestBody List<Kandidat> kandidati) {
		kandidatServis.insertAll(kandidati);
	}
}
