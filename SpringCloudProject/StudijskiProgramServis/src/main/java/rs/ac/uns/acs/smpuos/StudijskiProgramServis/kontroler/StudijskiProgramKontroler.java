package rs.ac.uns.acs.smpuos.StudijskiProgramServis.kontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.StudijskiProgram;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IStudijskiProgramServis;

@RestController
public class StudijskiProgramKontroler {

	@Autowired
	private IStudijskiProgramServis studijskiProgramServis;
	
	@RequestMapping(value = "/get-studijski-program", method = RequestMethod.GET)
	public Optional<StudijskiProgram> getStudijskiProgram(
	@RequestParam(name = "id", required = true) String id) {
		return studijskiProgramServis.findById(id);
	}
	
	@RequestMapping(value = "/get-studijski-programi", method = RequestMethod.GET)
	public List<StudijskiProgram> getStudijskiProgrami() {
		return studijskiProgramServis.findAll();
	}
	
	@RequestMapping(value = "/add-studijski-program", method = RequestMethod.POST)
	public void addStudijskiProgram(@RequestBody StudijskiProgram studijskiProgram) {
		studijskiProgramServis.insert(studijskiProgram);
	}
	
	@RequestMapping(value = "/add-studijski-programi", method = RequestMethod.POST)
	public void addStudijskiProgrami(@RequestBody List<StudijskiProgram> studijskiProgrami) {
		studijskiProgramServis.insertAll(studijskiProgrami);
	}
}
