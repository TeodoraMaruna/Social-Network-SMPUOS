package rs.ac.uns.acs.smpuos.StudijskiProgramServis.kontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Pozicija;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IPozicijaServis;

@RestController
public class PozicijaKontroler {

	@Autowired
	private IPozicijaServis pozicijaServis;
	
	@RequestMapping(value = "/get-pozicija", method = RequestMethod.GET)
	public Optional<Pozicija> getPozicija(
	@RequestParam(name = "id", required = true) String id) {
		return pozicijaServis.findById(id);
	}
	
	@RequestMapping(value = "/get-pozicije", method = RequestMethod.GET)
	public List<Pozicija> getPozicije() {
		return pozicijaServis.findAll();
	}
	
	@RequestMapping(value = "/add-pozicija", method = RequestMethod.POST)
	public void addPozicija(@RequestBody Pozicija pozicija) {
		pozicijaServis.insert(pozicija);
	}
	
	@RequestMapping(value = "/add-pozicije", method = RequestMethod.POST)
	public void addPozicije(@RequestBody List<Pozicija> pozicije) {
		pozicijaServis.insertAll(pozicije);
	}
}
