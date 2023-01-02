package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.KonkursServis.model.PrijavljenKandidat;

public interface IPrijavljenKandidatServis {

	Optional<PrijavljenKandidat> findById(String id);
	
	List<PrijavljenKandidat> findAll();
	
	void insert(PrijavljenKandidat prijavljenKandidat);
	
	void insertAll(List<PrijavljenKandidat> prijavljeniKandidati);
	
	void deleteAll();
	
	List<PrijavljenKandidat> findByStudijskiProgramNaKonkursuId(String studijskiProgramNaKonkursuId);
}
