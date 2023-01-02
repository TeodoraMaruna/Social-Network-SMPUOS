package rs.ac.uns.acs.smpuos.KorisnikServis.kontroler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KorisnikServisKontroler {

	private Environment okruzenje;
	
    public KorisnikServisKontroler(Environment okruzenje) {
        this.okruzenje = okruzenje;
    }
    
    @GetMapping("/pozdrav")
    public String pozdrav() {
        List<String> pozdravi = Arrays.asList("Поздрав", "Ћао", "Здраво", "Хеј");

        Random generatorNasumicnihBrojeva = new Random();
        int nasumicniBroj = generatorNasumicnihBrojeva.nextInt(pozdravi.size());

        return pozdravi.get(nasumicniBroj) + " [Порт: " + okruzenje.getProperty("local.server.port") + "]";
    }
}
