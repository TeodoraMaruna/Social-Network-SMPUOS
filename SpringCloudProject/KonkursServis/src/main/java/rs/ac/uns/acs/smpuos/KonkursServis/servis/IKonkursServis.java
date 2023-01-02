package rs.ac.uns.acs.smpuos.KonkursServis.servis;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.acs.smpuos.KonkursServis.model.Konkurs;

public interface IKonkursServis {

	Optional<Konkurs> findById(String id);
	
	List<Konkurs> findAll();
	
	void insert(Konkurs konkurs);
	
	void insertAll(List<Konkurs> konkursi);
	
	void deleteAll();
}
