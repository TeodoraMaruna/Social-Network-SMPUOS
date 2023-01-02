package rs.ac.uns.acs.smpuos.KonkursServis.dto;

public class StudijskiProgramDTO {

	private String id;
	private String naziv;
	private String nivoStudija;
	private String zvanjeKojeSeStice;
	private String obrazovnoPolje;
	private int brojSemestara;
	private int ukupanBrojESPB;
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
		return "StudijskiProgramDTO [id=" + id + ", naziv=" + naziv + ", nivoStudija=" + nivoStudija
				+ ", zvanjeKojeSeStice=" + zvanjeKojeSeStice + ", obrazovnoPolje=" + obrazovnoPolje + ", brojSemestara="
				+ brojSemestara + ", ukupanBrojESPB=" + ukupanBrojESPB + ", ocekivaniBrojKandidata="
				+ ocekivaniBrojKandidata + "]";
	}
}
