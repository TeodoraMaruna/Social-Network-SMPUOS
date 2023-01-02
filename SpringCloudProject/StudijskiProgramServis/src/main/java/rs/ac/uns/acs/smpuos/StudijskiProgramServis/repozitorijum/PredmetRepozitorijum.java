package rs.ac.uns.acs.smpuos.StudijskiProgramServis.repozitorijum;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Predmet;

public interface PredmetRepozitorijum extends MongoRepository<Predmet, String> {

}
