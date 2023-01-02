package rs.ac.uns.acs.smpuos.StudijskiProgramServis.kontroler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Pozicija;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.PozicijaPredmetaNaStudijskomProgramu;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.Predmet;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.model.StudijskiProgram;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IPozicijaPredmetaNaStudijskomProgramuServis;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IPredmetServis;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IPozicijaServis;
import rs.ac.uns.acs.smpuos.StudijskiProgramServis.servis.IStudijskiProgramServis;

@RestController
public class PozicijaPredmetaNaStudijskomProgramuKontroler {

	@Autowired
	private IPozicijaPredmetaNaStudijskomProgramuServis pozicijaPredmetaNaStudijskomProgramuServis;
	
	@Autowired
	private IStudijskiProgramServis studijskiProgramServis;
	
	@Autowired
	private IPozicijaServis pozicijaServis;
	
	@Autowired
	private IPredmetServis predmetServis;
	
	@RequestMapping(value = "/get-pozicija-predmeta-na-studijskom-programu", method = RequestMethod.GET)
	public Optional<PozicijaPredmetaNaStudijskomProgramu> getPozicijaPredmetaNaStudijskomProgramu(
	@RequestParam(name = "id", required = true) String id) {
		return pozicijaPredmetaNaStudijskomProgramuServis.findById(id);
	}
	
	@RequestMapping(value = "/get-pozicije-predmeta-na-studijskim-programima", method = RequestMethod.GET)
	public List<PozicijaPredmetaNaStudijskomProgramu> getPozicijePredmetaNaStudijskimProgramima() {
		return pozicijaPredmetaNaStudijskomProgramuServis.findAll();
	}
	
	@RequestMapping(value = "/add-pozicija-predmeta-na-studijskom-programu", method = RequestMethod.POST)
	public void addPozicijaPredmetaNaStudijskomProgramu(@RequestBody PozicijaPredmetaNaStudijskomProgramu pozicijaPredmetaNaStudijskomProgramu) {
		pozicijaPredmetaNaStudijskomProgramuServis.insert(pozicijaPredmetaNaStudijskomProgramu);
	}
	
	@RequestMapping(value = "/add-pozicije-predmeta-na-studijskim-programima", method = RequestMethod.POST)
	public void addPozicijePredmetaNaStudijskimProgramima(@RequestBody List<PozicijaPredmetaNaStudijskomProgramu> pozicijePredmetaNaStudijskimProgramima) {
		pozicijaPredmetaNaStudijskomProgramuServis.insertAll(pozicijePredmetaNaStudijskimProgramima);
	}
	
	@RequestMapping(value = "/get-predmeti-za-studijksi-program", method = RequestMethod.GET)
	public String getPredmetiZaStudijksiProgram(
	@RequestParam(name = "studijskiProgramId", required = true) String studijskiProgramId) {
		
		String povratnaVrednost = "<html><head><title>Predmeti odabranog studijskog programa</title></head><body><p>";
		
		List<PozicijaPredmetaNaStudijskomProgramu> predmetiNaPozicijamaStudijskogPrograma = pozicijaPredmetaNaStudijskomProgramuServis.findByStudijskiProgramId(studijskiProgramId);
		
		Optional<StudijskiProgram> opcioniStudijskiProgram = studijskiProgramServis.findById(studijskiProgramId);
		StudijskiProgram studijskiProgram = new StudijskiProgram();
		if(opcioniStudijskiProgram.isPresent())
			studijskiProgram = opcioniStudijskiProgram.get();
		
		povratnaVrednost = povratnaVrednost + "Студијски програм: " + studijskiProgram.getNaziv() + "</br>"
			+ "&emsp; - Ниво студија: " + studijskiProgram.getNivoStudija() + "</br>"
			+ "&emsp; - Звање које се стиче: " + studijskiProgram.getZvanjeKojeSeStice() + "</br>"
			+ "&emsp; - Образовно поље: " + studijskiProgram.getObrazovnoPolje() + "</br>"
			+ "&emsp; - Трајање (год/сем): " + studijskiProgram.getBrojSemestara()/2 + "/" + studijskiProgram.getBrojSemestara() + "</br>"
			+ "&emsp; - ЕСПБ: " + studijskiProgram.getUkupanBrojESPB() + "</br>"
			+ "<hr><hr>";
		
		List<String> pozicijeId = new ArrayList<>();
		List<Pozicija> pozicije = new ArrayList<>();
		
		for(PozicijaPredmetaNaStudijskomProgramu pozicijaPredmetaNaStudijskomProgramu : predmetiNaPozicijamaStudijskogPrograma) {
			if(!pozicijeId.contains(pozicijaPredmetaNaStudijskomProgramu.getPozicijaId())) {
				pozicijeId.add(pozicijaPredmetaNaStudijskomProgramu.getPozicijaId());
				
				Optional<Pozicija> opcionaPozicija = pozicijaServis.findById(pozicijaPredmetaNaStudijskomProgramu.getPozicijaId());
				if(opcionaPozicija.isPresent())
					pozicije.add(opcionaPozicija.get());
			}
		}
		
		pozicije.sort(Comparator.comparing(Pozicija::getRedniBroj));
		
		for(Pozicija pozicija : pozicije) {
			povratnaVrednost = povratnaVrednost + "Позиција: " + pozicija.getRedniBroj() + "</br>"
				+ "&emsp; - Семестар: " + pozicija.getSemestar() + "</br>"
				+ "&emsp; - Статус: " + pozicija.getStatus() + "</br></br>";
			
			for(PozicijaPredmetaNaStudijskomProgramu pozicijaPredmetaNaStudijskomProgramu : predmetiNaPozicijamaStudijskogPrograma) {
				if(pozicija.getId().equals(pozicijaPredmetaNaStudijskomProgramu.getPozicijaId())) {
					Optional<Predmet> opcioniPredmet = predmetServis.findById(pozicijaPredmetaNaStudijskomProgramu.getPredmetId());
					Predmet predmet = new Predmet();
					if(opcioniPredmet.isPresent())
						predmet = opcioniPredmet.get();
					
					povratnaVrednost = povratnaVrednost + "&emsp; - Предмет: " + predmet.getNaziv() + " (" + predmet.getSifra() + ")</br>"
						+ "&emsp; &emsp; - Опис: " + predmet.getOpis() + "</br>"
						+ "&emsp; &emsp; - Фонд предавања: " + predmet.getFondPredavanja() + "</br>"
						+ "&emsp; &emsp; - Фонд аудиторних вежби: " + predmet.getFondAuditornihVezbi() + "</br>"
						+ "&emsp; &emsp; - Други облици наставе: " + predmet.getFondDrugihOblikaNastave() + "</br>"
						+ "&emsp; &emsp; - ЕСПБ: " + predmet.getEspb() + "</br></br>";
				}
			}
			
			povratnaVrednost = povratnaVrednost + "<hr>";
		}
		
		povratnaVrednost = povratnaVrednost + "</p></body></html>";
		
		return povratnaVrednost;
	}
}
