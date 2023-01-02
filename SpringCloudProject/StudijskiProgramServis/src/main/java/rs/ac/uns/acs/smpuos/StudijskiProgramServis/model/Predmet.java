package rs.ac.uns.acs.smpuos.StudijskiProgramServis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "predmeti")
public class Predmet {

	@Id
	private String id;
	
	@Field(value = "naziv")
	private String naziv;
	
	@Field(value = "sifra")
	private String sifra;
	
	@Field(value = "opis")
	private String opis;
	
	@Field(value = "fondPredavanja")
	private int fondPredavanja;
	
	@Field(value = "fondAuditornihVezbi")
	private int fondAuditornihVezbi;
	
	@Field(value = "fondDrugihOblikaNastave")
	private int fondDrugihOblikaNastave;
	
	@Field(value = "espb")
	private int espb;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getFondPredavanja() {
		return fondPredavanja;
	}

	public void setFondPredavanja(int fondPredavanja) {
		this.fondPredavanja = fondPredavanja;
	}

	public int getFondAuditornihVezbi() {
		return fondAuditornihVezbi;
	}

	public void setFondAuditornihVezbi(int fondAuditornihVezbi) {
		this.fondAuditornihVezbi = fondAuditornihVezbi;
	}

	public int getFondDrugihOblikaNastave() {
		return fondDrugihOblikaNastave;
	}

	public void setFondDrugihOblikaNastave(int fondDrugihOblikaNastave) {
		this.fondDrugihOblikaNastave = fondDrugihOblikaNastave;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	@Override
	public String toString() {
		return "Predmet [id=" + id + ", naziv=" + naziv + ", sifra=" + sifra + ", opis=" + opis + ", fondPredavanja="
				+ fondPredavanja + ", fondAuditornihVezbi=" + fondAuditornihVezbi + ", fondDrugihOblikaNastave="
				+ fondDrugihOblikaNastave + ", espb=" + espb + "]";
	}
}
