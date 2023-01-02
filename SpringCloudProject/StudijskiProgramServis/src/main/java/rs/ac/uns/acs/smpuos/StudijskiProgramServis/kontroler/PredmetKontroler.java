package rs.ac.uns.acs.smpuos.StudijskiProgramServis.kontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Predmet;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IPredmetServis;

@RestController
public class PredmetKontroler {

	@Autowired
	private IPredmetServis predmetServis;
	
	@RequestMapping(value = "/get-predmet", method = RequestMethod.GET)
	public Optional<Predmet> getPredmet(
	@RequestParam(name = "id", required = true) String id) {
		return predmetServis.findById(id);
	}
	
	@RequestMapping(value = "/get-predmeti", method = RequestMethod.GET)
	public List<Predmet> getPredmeti() {
		return predmetServis.findAll();
	}
	
	@RequestMapping(value = "/add-predmet", method = RequestMethod.POST)
	public void addPredmet(@RequestBody Predmet predmet) {
		predmetServis.insert(predmet);
	}
	
	@RequestMapping(value = "/add-predmeti", method = RequestMethod.POST)
	public void addPredmeti(@RequestBody List<Predmet> predmeti) {
		predmetServis.insertAll(predmeti);
	}
}
