package rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.PozicijaPredmetaNaStudijskomProgramu;

public interface IPozicijaPredmetaNaStudijskomProgramuServis {

	Optional<PozicijaPredmetaNaStudijskomProgramu> findById(String id);
	
	List<PozicijaPredmetaNaStudijskomProgramu> findAll();
	
	void insert(PozicijaPredmetaNaStudijskomProgramu pozicijaPredmetaNaStudijskomProgramu);
	
	void insertAll(List<PozicijaPredmetaNaStudijskomProgramu> pozicijePredmetaNaStudijskimProgramima);
	
	void deleteAll();
	
	List<PozicijaPredmetaNaStudijskomProgramu> findByStudijskiProgramId(String studijskiProgramId);
}
