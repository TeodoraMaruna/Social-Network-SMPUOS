package rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.KonkursServis.model.PrijavljenKandidat;

public interface PrijavljenKandidatRepozitorijum extends MongoRepository<PrijavljenKandidat, String> {

	List<PrijavljenKandidat> findByStudijskiProgramNaKonkursuId(String studijskiProgramNaKonkursuId);
}
