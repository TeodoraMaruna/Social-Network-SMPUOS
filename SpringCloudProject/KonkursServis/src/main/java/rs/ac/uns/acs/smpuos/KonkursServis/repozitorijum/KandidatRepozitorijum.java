package rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Kandidat;

public interface KandidatRepozitorijum extends MongoRepository<Kandidat, String> {

}
