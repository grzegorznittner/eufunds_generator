/**
 * 
 */
package pl.nittner.eu.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import com.tutego.jrtf.Rtf;
import com.tutego.jrtf.RtfDocfmt;
import com.tutego.jrtf.RtfText;

import pl.nittner.eu.generator.data.EServiceParamsVO;
import pl.nittner.eu.generator.data.Project81VO;

/**
 * @author greg
 *
 */
public class FileStorage {
	public static void storeProject(Project81VO project, String absolutePath) {
		try {
            File projectFile = new File(absolutePath);
			if (projectFile.exists()) {
				int reply = JOptionPane.showConfirmDialog(EUGenMainForm.frameInstance(), "Plik projektu " + projectFile.getName() + " istnieje",
						"Czy chcesz nadpisać istniejące dane?", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.NO_OPTION) {
					return;
				}
			}

			ObjectMapper mapper = getMapper();
			mapper.writeValue(projectFile, project);
		} catch (Exception e) {
			EUGenMainForm.showErrorDialog("Error while storing project 8.1", e);
		}
	}

	public static void storeProjectAsRTF(Project81VO project, String absolutePath) {
		try {
            File projectFile = new File(absolutePath);
			if (projectFile.exists()) {
				int reply = JOptionPane.showConfirmDialog(EUGenMainForm.frameInstance(), "Plik " + projectFile.getName() + " istnieje",
						"Czy chcesz nadpisać istniejące dane?", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.NO_OPTION) {
					return;
				}
			}

			RtfText text = RtfText.text(RtfText.bold("Nazwa e-usługi: "), project.detailedDesc.nazwaEUslugi, "\n", "\n");
			
			text = RtfText.text(text, RtfText.bold("A. INFORMACJE OGÓLNE O PROJEKCIE"), "\n");
			text = RtfText.text(text, RtfText.bold("1. Identyfikacja projektu"), "\n");
			text = RtfText.text(text, RtfText.bold("a) Tytuł projektu"), "\n");
			text = RtfText.text(text, project.detailedDesc.tytul, "\n", "\n");
			
			text = RtfText.text(text, RtfText.bold("b) Krótki opis projektu"), "\n");
			text = RtfText.text(text, project.detailedDesc.krotkiOpis, "\n");
			
			text = RtfText.text(text, RtfText.bold("c) Słowa kluczowe"), "\n");
			text = RtfText.text(text, project.detailedDesc.slowaKluczowe, "\n", "\n");
			
			text = RtfText.text(text, RtfText.bold("d) Obszar e-gospodarki"), "\n");
			text = RtfText.text(text, project.detailedDesc.obszarGospodarki, "\n");
			
			text = RtfText.text(text, RtfText.bold("6. Projekt ma co najmniej neutralny wpływ na polityki horyzontalne Unii Europejskiej " +
					"wymienione w art. 16 i 17 rozporzadzenia 1083/2006(4000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.neutoralnoscUzasad, "\n");
			
			text = RtfText.text(text, RtfText.bold("8. Powiązanie projektu z innymi projektami w ramach NSRO 2007-2013"), "\n");
			text = RtfText.text(text, RtfText.bold("a) Informacje o innych projektach Wnioskodawcy (4000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.inneProjekty, "\n");
			
			text = RtfText.text(text, RtfText.bold("b) Informacje o projektach podmiotów powiązanych z Wnioskodawca (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.projektyPowiazane, "\n", "\n");
			
			text = RtfText.text(text, RtfText.bold("C. SZCZEGÓŁOWY OPIS PROJEKTU"), "\n");
			text = RtfText.text(text, RtfText.bold("15. E-usługa, jako cel projektu na poziomie produktu"), "\n");
			text = RtfText.text(text, RtfText.bold("a) Geneza pomysłu, podstawowy opis planowanych e-usług (10000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.geneza, "\n", "\n");
			
			text = RtfText.text(text, RtfText.bold("b) Sposób świadczenia e-usług - opis funkcjonalny serwisu (12000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.sposobSwiadczenia, "\n");
			
			text = RtfText.text(text, RtfText.bold("Projekt ma na celu przygotowanie, wdrożenie i świadczenie zaawansowanych" +
					" e-usług o znaczeniu krajowym lub międzynarodowym (4000 znaków/pole opisowe)"), "\n", "\n");
			text = RtfText.text(text, RtfText.bold("Nazwa e-usługi:"), "\n", "\n");
			text = RtfText.text(text, project.detailedDesc.nazwaEUslugi, "\n");

			int i = 1;
			for(EServiceParamsVO param : project.detailedDesc.eusluga) {
				text = RtfText.text(text, RtfText.bold("Cecha " + i), "\n");
				text = RtfText.text(text, param.cecha, "\n");

				text = RtfText.text(text, RtfText.bold("Korzyść " + i), "\n");
				text = RtfText.text(text, param.korzysc, "\n");

				text = RtfText.text(text, RtfText.bold("Parametr " + i), "\n");
				text = RtfText.text(text, param.parametr, "\n", "\n");
				i++;
			}

			text = RtfText.text(text, RtfText.bold("16. Model biznesowy i analiza rynku"), "\n", "\n");
			
			text = RtfText.text(text, RtfText.bold("a) Identyfikacja, charakterystyka i analiza potrzeb grupy docelowej (12000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.analizaPotrzeb, "\n");

			text = RtfText.text(text, RtfText.bold("b) Zidentyfikowane zródła przychodów (8000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.zrodlaPrzychodow, "\n");

			text = RtfText.text(text, RtfText.bold("c) Analiza otoczenia konkurencyjnego, substytuty (10000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.analizaOtoczenia, "\n");

			text = RtfText.text(text, RtfText.bold("d) Analiza SWOT (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.analizaSWOT, "\n");

			text = RtfText.text(text, RtfText.bold("e) Zarys strategii marketingowej, analiza popytu na e-usługę oraz polityka cenowa firmy (10000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.strategiaMarketingowa, "\n");

			text = RtfText.text(text, RtfText.bold("f) Koncepcja promocji, pozyskiwanie klientów i utrzymywania relacji z klientami (10000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.koncepcjaPromocji, "\n");

			text = RtfText.text(text, RtfText.bold("g) Kalkulacja przychodów projektu w czasie (w poszczególnych etapach i okresie trwałosci, " +
					"w podziale na zródła), rentowność, okres zwrotu (12000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.kalkulacjaProjektuWCzasie, "\n", "\n");

			text = RtfText.text(text, RtfText.bold("17. Przygotowanie do realizacji projektu"), "\n", "\n");
			text = RtfText.text(text, RtfText.bold("a) Zasoby wiedzy, identyfikacja i preferencje odnośnie technologii informatycznych (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.zasobyWiedzy, "\n");

			text = RtfText.text(text, RtfText.bold("b) Zasoby materialne dla realizacji projektu – stan obecny, analiza potrzeb (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.zasobyMaterialne, "\n");

			text = RtfText.text(text, RtfText.bold("c) Zasoby osobowe dla realizacji projektu – stan obecny i analiza potrzeb (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.zasobyOsobowe, "\n");

			text = RtfText.text(text, RtfText.bold("d) Zapotrzebowanie w zakresie usług obcych (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.uslugiObce, "\n");

			text = RtfText.text(text, RtfText.bold("e) Metodologia i uzasadnienie oszacowania wysokości poszczególnych wydatków (12000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.metodologiaOszacowania, "\n");

			text = RtfText.text(text, RtfText.bold("f) Świadczenie planowanych e-usług w aspekcie prawnym i etycznym (4000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.swiadczenieUslug, "\n", "\n");

			text = RtfText.text(text, RtfText.bold("18. Realizacja projektu"), "\n");
			text = RtfText.text(text, RtfText.bold("a) Opis realizacji poszczególnych etapów projektu – działania i ich finansowanie (12000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.opisRealizacji, "\n");

			text = RtfText.text(text, RtfText.bold("b) Identyfikacja i neutralizacja zagrożeń dla planowej realizacji działań (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.identyfikacjaZagrozen, "\n");

			text = RtfText.text(text, RtfText.bold("c) Rezultaty realizacji projektu – opis celów (10000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.rezultatyRealizacji, "\n", "\n");

			text = RtfText.text(text, RtfText.bold("19. Trwałość projektu"), "\n");
			text = RtfText.text(text, RtfText.bold("a) Opis zasobów oraz inwestycji odtworzeniowych niezbędnych dla kontynuacji świadczenia e-usługi (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.inwestycjeOdtworzeniowe, "\n");

			text = RtfText.text(text, RtfText.bold("b) Cykl życia produktu, wizja ewolucji serwisu i e-usługi (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.cyklZycia, "\n");

			text = RtfText.text(text, RtfText.bold("c) Trwałość zródeł przychodów i zarys długookresowej strategii marketingowej (6000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.trwaloscPrzychodow, "\n");

			text = RtfText.text(text, RtfText.bold("d) Koszty stałe i zmienne w okresie trwałości - kalkulacja (8000 znaków)"), "\n");
			text = RtfText.text(text, project.detailedDesc.kosztyWOkresieTrwalosci, "\n");

			Rtf rtf = Rtf.rtf();
			rtf.p(text);
			rtf.out(new FileWriter(absolutePath));
		} catch (Exception e) {
			EUGenMainForm.showErrorDialog("Error while storing project 8.1", e);
		}
	}

	public static Project81VO load81Project(String absolutePath) {
		try {
			ObjectMapper mapper = getMapper();
			Project81VO project = mapper.readValue(new File(absolutePath), Project81VO.class);
			return project;
		} catch (IOException e) {
            EUGenMainForm.showErrorDialog("Error while loading project 8.1", e);
            return null;
        }
	}

	private static ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	// "Jan 15, 2013 10:16:20 AM"
    	mapper.setDateFormat(new SimpleDateFormat("MMM d, yyyy h:mm:ss aaa"));
    	return mapper;
	}

}
