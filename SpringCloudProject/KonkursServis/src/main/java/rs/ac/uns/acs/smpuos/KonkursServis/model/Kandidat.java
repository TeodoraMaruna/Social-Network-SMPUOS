package rs.ac.uns.acs.smpuos.KonkursServis.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "kandidati")
public class Kandidat {

	@Id
	private String id;
	
	@Field(value = "ime")
	private String ime;
	
	@Field(value = "prezime")
	private String prezime;
	
	@Field(value = "datumRodjenja")
	private Date datumRodjenja;
	
	@Field(value = "adresa")
	private String adresa;
	
	@Field(value = "poeniIzSrednjeSkole")
	private double poeniIzSrednjeSkole;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public double getPoeniIzSrednjeSkole() {
		return poeniIzSrednjeSkole;
	}

	public void setPoeniIzSrednjeSkole(double poeniIzSrednjeSkole) {
		this.poeniIzSrednjeSkole = poeniIzSrednjeSkole;
	}

	@Override
	public String toString() {
		return "Kandidat [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja
				+ ", adresa=" + adresa + ", poeniIzSrednjeSkole=" + poeniIzSrednjeSkole + "]";
	}
}
