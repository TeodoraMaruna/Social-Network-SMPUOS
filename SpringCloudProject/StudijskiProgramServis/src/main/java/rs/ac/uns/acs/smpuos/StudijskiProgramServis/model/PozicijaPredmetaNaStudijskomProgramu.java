package rs.ac.uns.acs.smpuos.StudijskiProgramServis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pozicijePredmetaNaStudijskimProgramima")
public class PozicijaPredmetaNaStudijskomProgramu {
	
	@Id
	private String id;
	
	@Field(value = "studijskiProgramId")
	private String studijskiProgramId;
	
	@Field(value = "pozicijaId")
	private String pozicijaId;
	
	@Field(value = "predmetId")
	private String predmetId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudijskiProgramId() {
		return studijskiProgramId;
	}

	public void setStudijskiProgramId(String studijskiProgramId) {
		this.studijskiProgramId = studijskiProgramId;
	}

	public String getPozicijaId() {
		return pozicijaId;
	}

	public void setPozicijaId(String pozicijaId) {
		this.pozicijaId = pozicijaId;
	}

	public String getPredmetId() {
		return predmetId;
	}

	public void setPredmetId(String predmetId) {
		this.predmetId = predmetId;
	}

	@Override
	public String toString() {
		return "PozicijaPredmetaNaStudijskomProgramu [id=" + id + ", studijskiProgramId=" + studijskiProgramId
				+ ", pozicijaId=" + pozicijaId + ", predmetId=" + predmetId + "]";
	}
}
