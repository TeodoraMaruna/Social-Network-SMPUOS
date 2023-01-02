package rs.ac.uns.acs.smpuos.KonkursServis.repozitorijum;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Konkurs;

public interface KonkursRepozitorijum extends MongoRepository<Konkurs, String> {

}
