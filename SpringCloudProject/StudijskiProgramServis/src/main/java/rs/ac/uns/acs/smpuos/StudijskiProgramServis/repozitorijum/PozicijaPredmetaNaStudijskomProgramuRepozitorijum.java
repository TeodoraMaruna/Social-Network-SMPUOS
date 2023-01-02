package rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.PozicijaPredmetaNaStudijskomProgramu;

public interface PozicijaPredmetaNaStudijskomProgramuRepozitorijum extends MongoRepository<PozicijaPredmetaNaStudijskomProgramu, String> {
	
	List<PozicijaPredmetaNaStudijskomProgramu> findByStudijskiProgramId(String studijskiProgramId);
}
