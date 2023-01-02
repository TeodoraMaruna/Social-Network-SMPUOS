package rs.ac.uns.acs.smpuos.KonkursServis.kontroler;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.acs.smpuos.KonkursServis.dto.StudijskiProgramDTO;
import rs.ac.uns.acs.smpuos.KonkursServis.model.Kandidat;
import rs.ac.uns.acs.smpuos.KonkursServis.model.Konkurs;
import rs.ac.uns.acs.smpuos.KonkursServis.model.PrijavljenKandidat;
import rs.ac.uns.acs.smpuos.KonkursServis.model.StudijskiProgramNaKonkursu;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IKonkursServis;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IKandidatServis;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IPrijavljenKandidatServis;
import rs.ac.uns.acs.smpuos.KonkursServis.servis.IStudijskiProgramNaKonkursuServis;

@RestController
public class PrijavljenKandidatKontroler {

	@Autowired
	private IPrijavljenKandidatServis prijavljenKandidatServis;
	
	@Autowired
	private IStudijskiProgramNaKonkursuServis studijskiProgramNaKonkursuServis;
	
	@Autowired
	private IKonkursServis konkursServis;
	
	@Autowired
	private IKandidatServis kandidatServis;
	
	@RequestMapping(value = "/get-prijavljen-kandidat", method = RequestMethod.GET)
	public Optional<PrijavljenKandidat> getPrijavljenKandidat(
	@RequestParam(name = "id", required = true) String id) {
		return prijavljenKandidatServis.findById(id);
	}
	
	@RequestMapping(value = "/get-prijavljeni-kandidati", method = RequestMethod.GET)
	public List<PrijavljenKandidat> getPrijavljeniKandidati() {
		return prijavljenKandidatServis.findAll();
	}
	
	@RequestMapping(value = "/add-prijavljen-kandidat", method = RequestMethod.POST)
	public void addPrijavljenKandidat(@RequestBody PrijavljenKandidat prijavljenKandidat) {
		prijavljenKandidatServis.insert(prijavljenKandidat);
	}
	
	@RequestMapping(value = "/add-prijavljeni-kandidati", method = RequestMethod.POST)
	public void addPrijavljeniKandidati(@RequestBody List<PrijavljenKandidat> prijavljeniKandidati) {
		prijavljenKandidatServis.insertAll(prijavljeniKandidati);
	}
	
	@RequestMapping(value = "/get-prijavljeni-kandidati-na-konkursu-studijskog-programa", method = RequestMethod.GET)
	public String getPrijavljeniKandidatiNaKonkursuStudijskogPrograma(
	@RequestParam(name = "studijskiProgramNaKonkursuId", required = true) String studijskiProgramNaKonkursuId) {
		
		String povratnaVrednost = "<html><head><title>Prijavljeni kandidati na konkursu studijskog programa</title></head><body><p>";
		
		List<PrijavljenKandidat> prijavljeniKandidati = prijavljenKandidatServis.findByStudijskiProgramNaKonkursuId(studijskiProgramNaKonkursuId);
		
		Optional<StudijskiProgramNaKonkursu> opcioniStudijskiProgramNaKonkursu = studijskiProgramNaKonkursuServis.findById(studijskiProgramNaKonkursuId);
		StudijskiProgramNaKonkursu studijskiProgramNaKonkursu = new StudijskiProgramNaKonkursu();
		if(opcioniStudijskiProgramNaKonkursu.isPresent())
			studijskiProgramNaKonkursu = opcioniStudijskiProgramNaKonkursu.get();
		
		Optional<Konkurs> opcioniKonkurs = konkursServis.findById(studijskiProgramNaKonkursu.getKonkursId());
		Konkurs konkurs = new Konkurs();
		if(opcioniKonkurs.isPresent())
			konkurs = opcioniKonkurs.get();
		
		String sablon = "dd.MM.yyyy.";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sablon);
		
		povratnaVrednost = povratnaVrednost + konkurs.getNaziv() + "</br>"
				+ "&emsp; - Опис: " + konkurs.getOpis() + "</br>"
				+ "&emsp; - Школска година: " + konkurs.getSkolskaGodina() + "</br>"
				+ "&emsp; - Датум почетка пријава: " + simpleDateFormat.format(konkurs.getDatumPocetka()) + "</br>"
				+ "&emsp; - Датум завршетка пријава: " + simpleDateFormat.format(konkurs.getDatumZavrsetka()) + "</br>"
				+ "&emsp; - Језик студија: " + studijskiProgramNaKonkursu.getJezikStudija() + "</br>"
				+ "&emsp; - Број буџетских места: " + studijskiProgramNaKonkursu.getBrojBudzetskihMesta() + "</br>"
				+ "&emsp; - Број самофинансирајућих места: " + studijskiProgramNaKonkursu.getBrojSamofinansirajucihMesta() + "</br>"
				+ "&emsp; - Школарина за домаће студенте: " + studijskiProgramNaKonkursu.getSkolarinaZaDomaceStudente() + " Евра" + "</br>"
				+ "&emsp; - Школарина за стране студенте: " + studijskiProgramNaKonkursu.getSkolarinaZaStraneStudente() + " Евра" + "</br></br>";
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = restTemplateBuilder.build();
	    StudijskiProgramDTO studijskiProgram = restTemplate.getForObject(
	    		"http://localhost:9000/studijski-program-servis/get-studijski-program?id=" + studijskiProgramNaKonkursu.getStudijskiProgramId(), 
	    		StudijskiProgramDTO.class, 1);
	    
		povratnaVrednost = povratnaVrednost + "Студијски програм: " + studijskiProgram.getNaziv() + "</br>"
				+ "&emsp; - Ниво студија: " + studijskiProgram.getNivoStudija() + "</br>"
				+ "&emsp; - Звање које се стиче: " + studijskiProgram.getZvanjeKojeSeStice() + "</br>"
				+ "&emsp; - Образовно поље: " + studijskiProgram.getObrazovnoPolje() + "</br>"
				+ "&emsp; - Трајање (год/сем): " + studijskiProgram.getBrojSemestara()/2 + "/" + studijskiProgram.getBrojSemestara() + "</br>"
				+ "&emsp; - ЕСПБ: " + studijskiProgram.getUkupanBrojESPB() + "</br>"
				+ "<hr><hr>";
		
		double ukupanBrojPoena;
		
		for(PrijavljenKandidat prijavljenKandidat : prijavljeniKandidati) {
			Optional<Kandidat> opcioniKandidat = kandidatServis.findById(prijavljenKandidat.getKandidatId());
			Kandidat kandidat = new Kandidat();
			if(opcioniKandidat.isPresent())
				kandidat = opcioniKandidat.get();
			
			ukupanBrojPoena = kandidat.getPoeniIzSrednjeSkole() + prijavljenKandidat.getPoeniSaPrijemnogIspita();
			
			povratnaVrednost = povratnaVrednost + "Кандидат: " + kandidat.getIme() + " " + kandidat.getPrezime() + "</br>"
					+ "&emsp; - Датум рођења: " + simpleDateFormat.format(kandidat.getDatumRodjenja()) + "</br>"
					+ "&emsp; - Адреса: " + kandidat.getAdresa() + "</br>"
					+ "&emsp; - Датум пријаве: " + simpleDateFormat.format(prijavljenKandidat.getDatumPrijave()) + "</br>"
					+ "&emsp; - Број бодова из средње школе: " + kandidat.getPoeniIzSrednjeSkole() + "</br>"
					+ "&emsp; - Број бодова са пријемног испита: " + prijavljenKandidat.getPoeniSaPrijemnogIspita() + "</br>"
					+ "&emsp; - Укупан број бодова: " + ukupanBrojPoena + "</br>"
					+ "<hr>";
		}
		
		povratnaVrednost = povratnaVrednost + "</p></body></html>";
	
		return povratnaVrednost;
	}
}
