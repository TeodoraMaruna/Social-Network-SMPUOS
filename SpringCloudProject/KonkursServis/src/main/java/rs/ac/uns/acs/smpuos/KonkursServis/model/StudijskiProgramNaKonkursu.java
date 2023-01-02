package rs.ac.uns.acs.smpuos.KonkursServis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "studijskiProgramiNaKonkursima")
public class StudijskiProgramNaKonkursu {

	@Id
	private String id;
	
	@Field(value = "konkursId")
	private String konkursId;
	
	@Field(value = "studijskiProgramId")
	private String studijskiProgramId;
	
	@Field(value = "jezikStudija")
	private String jezikStudija;
	
	@Field(value = "brojBudzetskihMesta")
	private int brojBudzetskihMesta;
	
	@Field(value = "brojSamofinansirajucihMesta")
	private int brojSamofinansirajucihMesta;
	
	@Field(value = "skolarinaZaDomaceStudente")
	private double skolarinaZaDomaceStudente;
	
	@Field(value = "skolarinaZaStraneStudente")
	private double skolarinaZaStraneStudente;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKonkursId() {
		return konkursId;
	}

	public void setKonkursId(String konkursId) {
		this.konkursId = konkursId;
	}

	public String getStudijskiProgramId() {
		return studijskiProgramId;
	}

	public void setStudijskiProgramId(String studijskiProgramId) {
		this.studijskiProgramId = studijskiProgramId;
	}

	public String getJezikStudija() {
		return jezikStudija;
	}

	public void setJezikStudija(String jezikStudija) {
		this.jezikStudija = jezikStudija;
	}

	public int getBrojBudzetskihMesta() {
		return brojBudzetskihMesta;
	}

	public void setBrojBudzetskihMesta(int brojBudzetskihMesta) {
		this.brojBudzetskihMesta = brojBudzetskihMesta;
	}

	public int getBrojSamofinansirajucihMesta() {
		return brojSamofinansirajucihMesta;
	}

	public void setBrojSamofinansirajucihMesta(int brojSamofinansirajucihMesta) {
		this.brojSamofinansirajucihMesta = brojSamofinansirajucihMesta;
	}

	public double getSkolarinaZaDomaceStudente() {
		return skolarinaZaDomaceStudente;
	}

	public void setSkolarinaZaDomaceStudente(double skolarinaZaDomaceStudente) {
		this.skolarinaZaDomaceStudente = skolarinaZaDomaceStudente;
	}

	public double getSkolarinaZaStraneStudente() {
		return skolarinaZaStraneStudente;
	}

	public void setSkolarinaZaStraneStudente(double skolarinaZaStraneStudente) {
		this.skolarinaZaStraneStudente = skolarinaZaStraneStudente;
	}

	@Override
	public String toString() {
		return "StudijskiProgramNaKonkursu [id=" + id + ", konkursId=" + konkursId + ", studijskiProgramId="
				+ studijskiProgramId + ", jezikStudija=" + jezikStudija + ", brojBudzetskihMesta=" + brojBudzetskihMesta
				+ ", brojSamofinansirajucihMesta=" + brojSamofinansirajucihMesta + ", skolarinaZaDomaceStudente="
				+ skolarinaZaDomaceStudente + ", skolarinaZaStraneStudente=" + skolarinaZaStraneStudente + "]";
	}
}
