package rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.KonkursServis.model.StudijskiProgramNaKonkursu;

public interface StudijskiProgramNaKonkursuRepozitorijum extends MongoRepository<StudijskiProgramNaKonkursu, String> {

}
