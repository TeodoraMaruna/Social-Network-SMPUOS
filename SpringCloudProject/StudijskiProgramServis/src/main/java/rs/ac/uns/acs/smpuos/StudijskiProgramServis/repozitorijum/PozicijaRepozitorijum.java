package rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Pozicija;

public interface PozicijaRepozitorijum extends MongoRepository<Pozicija, String> {

}
