package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.StudijskiProgram;

public interface IStudijskiProgramServis {
	
	Optional<StudijskiProgram> findById(String id);
	
	List<StudijskiProgram> findAll();
	
	void insert(StudijskiProgram studijskiProgram);
	
	void insertAll(List<StudijskiProgram> studijskiProgrami);
	
	void deleteAll();
}
