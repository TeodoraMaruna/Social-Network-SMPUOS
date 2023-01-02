package rs.ac.uns.acs.smpuos.KonkursServis.kontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Konkurs;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IKonkursServis;

@RestController
public class KonkursKontroler {

	@Autowired
	private IKonkursServis konkursServis;
	
	@RequestMapping(value = "/get-konkurs", method = RequestMethod.GET)
	public Optional<Konkurs> getKonkurs(
	@RequestParam(name = "id", required = true) String id) {
		return konkursServis.findById(id);
	}
	
	@RequestMapping(value = "/get-konkursi", method = RequestMethod.GET)
	public List<Konkurs> getKonkursi() {
		return konkursServis.findAll();
	}
	
	@RequestMapping(value = "/add-konkurs", method = RequestMethod.POST)
	public void addKonkurs(@RequestBody Konkurs konkurs) {
		konkursServis.insert(konkurs);
	}
	
	@RequestMapping(value = "/add-konkursi", method = RequestMethod.POST)
	public void addKonkursi(@RequestBody List<Konkurs> konkursi) {
		konkursServis.insertAll(konkursi);
	}
}
