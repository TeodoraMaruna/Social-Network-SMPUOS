package rs.ac.uns.acs.smpuos.StudijskiProgramServis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "studijskiProgrami")
public class StudijskiProgram {
	
	@Id
	private String id;
	
	@Field(value = "naziv")
	private String naziv;
	
	@Field(value = "nivoStudija")
	private String nivoStudija;
	
	@Field(value = "zvanjeKojeSeStice")
	private String zvanjeKojeSeStice;
	
	@Field(value = "obrazovnoPolje")
	private String obrazovnoPolje;
	
	@Field(value = "brojSemestara")
	private int brojSemestara;
	
	@Field(value = "ukupanBrojESPB")
	private int ukupanBrojESPB;
	
	@Field(value = "ocekivaniBrojKandidata")
	private int ocekivaniBrojKandidata;

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

	public String getNivoStudija() {
		return nivoStudija;
	}

	public void setNivoStudija(String nivoStudija) {
		this.nivoStudija = nivoStudija;
	}

	public String getZvanjeKojeSeStice() {
		return zvanjeKojeSeStice;
	}

	public void setZvanjeKojeSeStice(String zvanjeKojeSeStice) {
		this.zvanjeKojeSeStice = zvanjeKojeSeStice;
	}

	public String getObrazovnoPolje() {
		return obrazovnoPolje;
	}

	public void setObrazovnoPolje(String obrazovnoPolje) {
		this.obrazovnoPolje = obrazovnoPolje;
	}

	public int getBrojSemestara() {
		return brojSemestara;
	}

	public void setBrojSemestara(int brojSemestara) {
		this.brojSemestara = brojSemestara;
	}

	public int getUkupanBrojESPB() {
		return ukupanBrojESPB;
	}

	public void setUkupanBrojESPB(int ukupanBrojESPB) {
		this.ukupanBrojESPB = ukupanBrojESPB;
	}

	public int getOcekivaniBrojKandidata() {
		return ocekivaniBrojKandidata;
	}

	public void setOcekivaniBrojKandidata(int ocekivaniBrojKandidata) {
		this.ocekivaniBrojKandidata = ocekivaniBrojKandidata;
	}

	@Override
	public String toString() {
		return "StudijskiProgram [id=" + id + ", naziv=" + naziv + ", nivoStudija=" + nivoStudija
				+ ", zvanjeKojeSeStice=" + zvanjeKojeSeStice + ", obrazovnoPolje=" + obrazovnoPolje + ", brojSemestara="
				+ brojSemestara + ", ukupanBrojESPB=" + ukupanBrojESPB + ", ocekivaniBrojKandidata="
				+ ocekivaniBrojKandidata + "]";
	}
}
