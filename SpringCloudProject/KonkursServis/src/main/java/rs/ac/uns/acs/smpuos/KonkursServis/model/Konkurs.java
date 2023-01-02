package rs.ac.uns.acs.smpuos.KonkursServis.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "konkursi")
public class Konkurs {

	@Id
	private String id;
	
	@Field(value = "naziv")
	private String naziv;
	
	@Field(value = "opis")
	private String opis;
	
	@Field(value = "skolskaGodina")
	private String skolskaGodina;
	
	@Field(value = "redniBroj")
	private int redniBroj;
	
	@Field(value = "datumPocetka")
	private Date datumPocetka;
	
	@Field(value = "datumZavrsetka")
	private Date datumZavrsetka;

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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	@Override
	public String toString() {
		return "Konkurs [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", skolskaGodina=" + skolskaGodina
				+ ", redniBroj=" + redniBroj + ", datumPocetka=" + datumPocetka + ", datumZavrsetka=" + datumZavrsetka
				+ "]";
	}
}
