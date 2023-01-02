package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.StudijskiProgram;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum.StudijskiProgramRepozitorijum;

@Service
public class StudijskiProgramServis implements IStudijskiProgramServis {
	
	@Autowired
	StudijskiProgramRepozitorijum studijskiProgramRepozitorijum;
	
	public Optional<StudijskiProgram> findById(String id) {
		return studijskiProgramRepozitorijum.findById(id);
	}
	
	public List<StudijskiProgram> findAll() {
		return studijskiProgramRepozitorijum.findAll();
	}

	public void insert(StudijskiProgram studijskiProgram) {
		studijskiProgramRepozitorijum.insert(studijskiProgram);
	}
	
	public void insertAll(List<StudijskiProgram> studijskiProgrami) {
		studijskiProgramRepozitorijum.insert(studijskiProgrami);
	}

	public void deleteAll() {
		studijskiProgramRepozitorijum.deleteAll();
	}
}
