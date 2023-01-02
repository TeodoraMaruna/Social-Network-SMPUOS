package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.KonkursServis.model.StudijskiProgramNaKonkursu;

public interface IStudijskiProgramNaKonkursuServis {

	Optional<StudijskiProgramNaKonkursu> findById(String id);
	
	List<StudijskiProgramNaKonkursu> findAll();
	
	void insert(StudijskiProgramNaKonkursu studijskiProgramNaKonkursu);
	
	void insertAll(List<StudijskiProgramNaKonkursu> studijskiProgramiNaKonkursima);
	
	void deleteAll();
}
