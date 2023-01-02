package rs.ac.uns.acs.smpuos.KonkursServis.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "prijavljeniKandidati")
public class PrijavljenKandidat {

	@Id
	private String id;
	
	@Field(value = "studijskiProgramNaKonkursuId")
	private String studijskiProgramNaKonkursuId;
	
	@Field(value = "kandidatId")
	private String kandidatId;
	
	@Field(value = "datumPrijave")
	private Date datumPrijave;
	
	@Field(value = "poeniSaPrijemnogIspita")
	private double poeniSaPrijemnogIspita;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudijskiProgramNaKonkursuId() {
		return studijskiProgramNaKonkursuId;
	}

	public void setStudijskiProgramNaKonkursuId(String studijskiProgramNaKonkursuId) {
		this.studijskiProgramNaKonkursuId = studijskiProgramNaKonkursuId;
	}

	public String getKandidatId() {
		return kandidatId;
	}

	public void setKandidatId(String kandidatId) {
		this.kandidatId = kandidatId;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public double getPoeniSaPrijemnogIspita() {
		return poeniSaPrijemnogIspita;
	}

	public void setPoeniSaPrijemnogIspita(double poeniSaPrijemnogIspita) {
		this.poeniSaPrijemnogIspita = poeniSaPrijemnogIspita;
	}

	@Override
	public String toString() {
		return "RegistrovaniKandidat [id=" + id + ", studijskiProgramNaKonkursuId=" + studijskiProgramNaKonkursuId
				+ ", kandidatId=" + kandidatId + ", datumPrijave=" + datumPrijave + ", poeniSaPrijemnogIspita="
				+ poeniSaPrijemnogIspita + "]";
	}
}
