package rs.ac.uns.acs.smpuos.StudijskiProgramServis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pozicije")
public class Pozicija {
	
	@Id
	private String id;
	
	@Field(value = "studijskiProgramId")
	private String studijskiProgramId;
	
	@Field(value = "redniBroj")
	private int redniBroj;
	
	@Field(value = "semestar")
	private int semestar;
	
	@Field(value = "status")
	private String status;

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

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public int getSemestar() {
		return semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pozicija [id=" + id + ", studijskiProgramId=" + studijskiProgramId + ", redniBroj=" + redniBroj
				+ ", semestar=" + semestar + ", status=" + status + "]";
	}
}
