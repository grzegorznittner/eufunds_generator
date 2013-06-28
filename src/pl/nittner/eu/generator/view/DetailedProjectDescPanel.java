/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import pl.nittner.eu.generator.data.DetailedProjectDescVO;
import pl.nittner.eu.generator.data.EServiceParamsVO;

/**
 * @author greg
 *
 */
@SuppressWarnings("serial")
public class DetailedProjectDescPanel extends BasePanel implements ActionListener {
	private enum UI {TYTUL, KROTKI_OPIS, SLOWA_KLUCZOWE, OBSZAR_GOSPODARKI, NEUTRALNOSC_UZASAD,
		INNE_PROJEKTY, PROJEKTY_POWIAZANE, 
		GENEZA, SPOSOB_SWIADCZENIA, NAZWA_EUSLUGI, CECHY, ANALIZA_POTRZEB, ZRODLA_PRZYCHODOW,
		ANALIZA_OTOCZENIA, ANALIZA_SWOT, STRATEGIA_MARKETINGOWA, KONCEPCJA_PROMOCJI, KALKULACJA_PROJEKTU_W_CZASIE,
		ZASOBY_WIEDZY, ZASOBY_MATERIALNE, ZASOBY_OSOBOWE, USLUGI_OBCE, METODOLOGIA_OSZACOWANIA,
		SWIADCZENIE_USLUG, OPIS_REALIZACJI, IDENTYFIKACJA_ZAGROZEN, REZULTATY_REALIZACJI,
		INWESTYCJE_ODTWORZENIOWE, CYKL_ZYCIA, KOSZTY_W_OKRESIE_TRWALOSCI, TRWALOSC_PRZYCHODOW};
	
	private Map<UI, JTextComponent> textComp = new HashMap<UI, JTextComponent>();
	
	public List<JTextArea> cecha = new ArrayList<JTextArea>();
	public List<JTextArea> korzysc = new ArrayList<JTextArea>();
	public List<JTextArea> parametr = new ArrayList<JTextArea>();
		
	private DetailedProjectDescVO vo;
	private boolean uiCreated = false;
	
	public DetailedProjectDescPanel(ProjectView project, DetailedProjectDescVO vo) {
		this.vo = vo;
		this.project = project;
	}
	
	public DetailedProjectDescVO getVO() {
		vo.tytul = textComp.get(UI.TYTUL).getText();
		vo.krotkiOpis = textComp.get(UI.KROTKI_OPIS).getText();
		vo.slowaKluczowe = textComp.get(UI.SLOWA_KLUCZOWE).getText();
		vo.obszarGospodarki = textComp.get(UI.OBSZAR_GOSPODARKI).getText();
		vo.neutoralnoscUzasad = textComp.get(UI.NEUTRALNOSC_UZASAD).getText();
		vo.inneProjekty = textComp.get(UI.INNE_PROJEKTY).getText();
		vo.projektyPowiazane = textComp.get(UI.PROJEKTY_POWIAZANE).getText();
		
		vo.geneza = textComp.get(UI.GENEZA).getText();
		vo.sposobSwiadczenia = textComp.get(UI.SPOSOB_SWIADCZENIA).getText();
		vo.nazwaEUslugi = textComp.get(UI.NAZWA_EUSLUGI).getText();
		
		vo.eusluga = new ArrayList<EServiceParamsVO>();
		for (int i = 0; i < cecha.size(); i++) {
			EServiceParamsVO param = new EServiceParamsVO();
			param.cecha = cecha.get(i).getText();
			param.korzysc = korzysc.get(i).getText();
			param.parametr = parametr.get(i).getText();
			
			vo.eusluga.add(param);
		}
		
		vo.analizaPotrzeb = textComp.get(UI.ANALIZA_POTRZEB).getText();
		vo.zrodlaPrzychodow = textComp.get(UI.ZRODLA_PRZYCHODOW).getText();
		vo.analizaOtoczenia = textComp.get(UI.ANALIZA_OTOCZENIA).getText();
		vo.analizaSWOT = textComp.get(UI.ANALIZA_SWOT).getText();
		vo.strategiaMarketingowa = textComp.get(UI.STRATEGIA_MARKETINGOWA).getText();
		vo.koncepcjaPromocji = textComp.get(UI.KONCEPCJA_PROMOCJI).getText();
		vo.kalkulacjaProjektuWCzasie = textComp.get(UI.KALKULACJA_PROJEKTU_W_CZASIE).getText();
		vo.zasobyWiedzy = textComp.get(UI.ZASOBY_WIEDZY).getText();
		vo.zasobyMaterialne = textComp.get(UI.ZASOBY_MATERIALNE).getText();
		vo.zasobyOsobowe = textComp.get(UI.ZASOBY_OSOBOWE).getText();
		vo.uslugiObce = textComp.get(UI.USLUGI_OBCE).getText();
		vo.metodologiaOszacowania = textComp.get(UI.METODOLOGIA_OSZACOWANIA).getText();
		vo.swiadczenieUslug = textComp.get(UI.SWIADCZENIE_USLUG).getText();
		vo.opisRealizacji = textComp.get(UI.OPIS_REALIZACJI).getText();
		vo.identyfikacjaZagrozen = textComp.get(UI.IDENTYFIKACJA_ZAGROZEN).getText();
		vo.rezultatyRealizacji = textComp.get(UI.REZULTATY_REALIZACJI).getText();
		vo.inwestycjeOdtworzeniowe = textComp.get(UI.INWESTYCJE_ODTWORZENIOWE).getText();
		vo.cyklZycia = textComp.get(UI.CYKL_ZYCIA).getText();
		vo.kosztyWOkresieTrwalosci = textComp.get(UI.KOSZTY_W_OKRESIE_TRWALOSCI).getText();
		vo.trwaloscPrzychodow = textComp.get(UI.TRWALOSC_PRZYCHODOW).getText();
		
		return vo;
	}
	
	public void buildUI() {
		if (uiCreated) {
			recreateUI();
		} else {
			createUI();
			uiCreated = true;
		}
	}
	
	private void createUI() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		int posx = 0;
		
		JLabel label = createLabel(0, posx++, "A. INFORMACJE OGÓLNE O PROJEKCIE", c);
		add(label, c);
		label = createLabel(0, posx++, "1. Identyfikacja projektu", c);
		add(label, c);
		
		label = createLabel(0, posx++, "a) Tytuł projektu", c);
		add(label, c);
		textComp.put(UI.TYTUL, createTextArea(this, 0, posx++, vo.tytul, c, 3));
		
		label = createLabel(0, posx++, "b) Krótki opis projektu", c);
		add(label, c);
		textComp.put(UI.KROTKI_OPIS, createTextArea(this, 0, posx++, vo.krotkiOpis, c));
		
		label = createLabel(0, posx++, "c) Słowa kluczowe", c);
		add(label, c);
		textComp.put(UI.SLOWA_KLUCZOWE, createTextArea(this, 0, posx++, vo.slowaKluczowe, c, 2));
		
		label = createLabel(0, posx++, "d) Obszar e-gospodarki", c);
		add(label, c);
		textComp.put(UI.OBSZAR_GOSPODARKI, createTextArea(this, 0, posx++, vo.obszarGospodarki, c, 3));
		
		label = createLabel(0, posx++, "6. Projekt ma co najmniej neutralny wpływ na polityki horyzontalne Unii Europejskiej " +
				"wymienione w art. 16 i 17 rozporzadzenia 1083/2006(4000 znaków)", c);
		add(label, c);
		textComp.put(UI.NEUTRALNOSC_UZASAD, createTextArea(this, 0, posx++, vo.neutoralnoscUzasad, c, 10));
		
		label = createLabel(0, posx++, "8. Powiazanie projektu z innymi projektami w ramach NSRO 2007-2013", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Informacje o innych projektach Wnioskodawcy (4000 znaków)", c);
		add(label, c);
		textComp.put(UI.INNE_PROJEKTY, createTextArea(this, 0, posx++, vo.inneProjekty, c, 10));
		
		label = createLabel(0, posx++, "b) Informacje o projektach podmiotów powiazanych z Wnioskodawca (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.PROJEKTY_POWIAZANE, createTextArea(this, 0, posx++, vo.projektyPowiazane, c, 10));
		
		label = createLabel(0, posx++, "C. SZCZEGÓŁOWY OPIS PROJEKTU", c);
		add(label, c);

		label = createLabel(0, posx++, "15. E-usługa, jako cel projektu na poziomie produktu", c);
		add(label, c);
		
		label = createLabel(0, posx++, "a) Geneza pomysłu, podstawowy opis planowanych e-usług (10000 znaków)", c);
		add(label, c);
		textComp.put(UI.GENEZA, createTextArea(this, 0, posx++, vo.geneza, c, 40));
		
		label = createLabel(0, posx++, "b) Sposób swiadczenia e-usług - opis funkcjonalny serwisu (12000 znaków)", c);
		add(label, c);
		textComp.put(UI.SPOSOB_SWIADCZENIA, createTextArea(this, 0, posx++, vo.sposobSwiadczenia, c, 40));

		label = createLabel(0, posx++, "Projekt ma na celu przygotowanie, wdrozenie i swiadczenie zaawansowanych" +
				" e-usług o znaczeniu krajowym lub miedzynarodowym (4000 znaków/pole opisowe)", c);
		add(label, c);
		label = createLabel(0, posx++, "Nazwa e-usługi:", c);
		add(label, c);
		textComp.put(UI.NAZWA_EUSLUGI, createTextField(this, 0, posx++, vo.nazwaEUslugi, c));

		JButton dodaj = createButton(0, posx++, "Dodaj cecha/korzyść/parametr 0", c);
		dodaj.setActionCommand("add" + 0);
		dodaj.addActionListener(this);
		add(dodaj, c);
		for (int i = 1; i <= project.getMaxCechaKorzyscParam(); i++) {
			if (i > vo.eusluga.size()) {
				posx = createCechaKorzyscParameter(false, new EServiceParamsVO(), c, posx, i);
			} else {
				posx = createCechaKorzyscParameter(true, vo.eusluga.get(i - 1), c, posx, i);
			}
		}

		label = createLabel(0, posx++, "16. Model biznesowy i analiza rynku", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Identyfikacja, charakterystyka i analiza potrzeb grupy docelowej (12000 znaków)", c);
		add(label, c);
		textComp.put(UI.ANALIZA_POTRZEB, createTextArea(this, 0, posx++, vo.analizaPotrzeb, c));
		
		label = createLabel(0, posx++, "b) Zidentyfikowane zródła przychodów (8000 znaków)", c);
		add(label, c);
		textComp.put(UI.ZRODLA_PRZYCHODOW, createTextArea(this, 0, posx++, vo.zrodlaPrzychodow, c));
		
		label = createLabel(0, posx++, "c) Analiza otoczenia konkurencyjnego, substytuty (10000 znaków)", c);
		add(label, c);
		textComp.put(UI.ANALIZA_OTOCZENIA, createTextArea(this, 0, posx++, vo.analizaOtoczenia, c));
		
		label = createLabel(0, posx++, "d) Analiza SWOT (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.ANALIZA_SWOT, createTextArea(this, 0, posx++, vo.analizaSWOT, c));
		
		label = createLabel(0, posx++, "e) Zarys strategii marketingowej, analiza popytu na e-usługe oraz polityka cenowa firmy (10000 znaków)", c);
		add(label, c);
		textComp.put(UI.STRATEGIA_MARKETINGOWA, createTextArea(this, 0, posx++, vo.strategiaMarketingowa, c));
		
		label = createLabel(0, posx++, "f) Koncepcja promocji, pozyskiwanie klientów i utrzymywania relacji z klientami (10000 znaków)", c);
		add(label, c);
		textComp.put(UI.KONCEPCJA_PROMOCJI, createTextArea(this, 0, posx++, vo.koncepcjaPromocji, c));
		
		label = createLabel(0, posx++, "g) Kalkulacja przychodów projektu w czasie (w poszczególnych etapach i okresie trwałosci, " +
				"w podziale na zródła), rentownosc, okres zwrotu (12000 znaków)", c);
		add(label, c);
		textComp.put(UI.KALKULACJA_PROJEKTU_W_CZASIE, createTextArea(this, 0, posx++, vo.kalkulacjaProjektuWCzasie, c));
		
		label = createLabel(0, posx++, "17. Przygotowanie do realizacji projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Zasoby wiedzy, identyfikacja i preferencje odnosnie technologii informatycznych (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.ZASOBY_WIEDZY, createTextArea(this, 0, posx++, vo.zasobyWiedzy, c));
		
		label = createLabel(0, posx++, "b) Zasoby materialne dla realizacji projektu – stan obecny, analiza potrzeb (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.ZASOBY_MATERIALNE, createTextArea(this, 0, posx++, vo.zasobyMaterialne, c));
		
		label = createLabel(0, posx++, "c) Zasoby osobowe dla realizacji projektu – stan obecny i analiza potrzeb (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.ZASOBY_OSOBOWE, createTextArea(this, 0, posx++, vo.zasobyOsobowe, c));
		
		label = createLabel(0, posx++, "d) Zapotrzebowanie w zakresie usług obcych (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.USLUGI_OBCE, createTextArea(this, 0, posx++, vo.uslugiObce, c));
		
		label = createLabel(0, posx++, "e) Metodologia i uzasadnienie oszacowania wysokosci poszczególnych wydatków (12000 znaków)", c);
		add(label, c);
		textComp.put(UI.METODOLOGIA_OSZACOWANIA, createTextArea(this, 0, posx++, vo.metodologiaOszacowania, c));
		
		label = createLabel(0, posx++, "f) Swiadczenie planowanych e-usług w aspekcie prawnym i etycznym (4000 znaków)", c);
		add(label, c);
		textComp.put(UI.SWIADCZENIE_USLUG, createTextArea(this, 0, posx++, vo.swiadczenieUslug, c));
		
		label = createLabel(0, posx++, "18. Realizacja projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Opis realizacji poszczególnych etapów projektu – działania i ich finansowanie (12000 znaków)", c);
		add(label, c);
		textComp.put(UI.OPIS_REALIZACJI, createTextArea(this, 0, posx++, vo.opisRealizacji, c));
		
		label = createLabel(0, posx++, "b) Identyfikacja i neutralizacja zagrozen dla planowej realizacji działan (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.IDENTYFIKACJA_ZAGROZEN, createTextArea(this, 0, posx++, vo.identyfikacjaZagrozen, c));
		
		label = createLabel(0, posx++, "c) Rezultaty realizacji projektu – opis celów (10000 znaków)", c);
		add(label, c);
		textComp.put(UI.REZULTATY_REALIZACJI, createTextArea(this, 0, posx++, vo.rezultatyRealizacji, c));
		
		label = createLabel(0, posx++, "19. Trwałosc projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Opis zasobów oraz inwestycji odtworzeniowych niezbednych dla kontynuacji swiadczenia e-usługi (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.INWESTYCJE_ODTWORZENIOWE, createTextArea(this, 0, posx++, vo.inwestycjeOdtworzeniowe, c));
		
		label = createLabel(0, posx++, "b) Cykl zycia produktu, wizja ewolucji serwisu i e-usługi (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.CYKL_ZYCIA, createTextArea(this, 0, posx++, vo.cyklZycia, c));
		
		label = createLabel(0, posx++, "c) Trwałosc zródeł przychodów i zarys długookresowej strategii marketingowej (6000 znaków)", c);
		add(label, c);
		textComp.put(UI.TRWALOSC_PRZYCHODOW, createTextArea(this, 0, posx++, vo.trwaloscPrzychodow, c));
		
		label = createLabel(0, posx++, "d) Koszty stałe i zmienne w okresie trwałosci - kalkulacja (8000 znaków)", c);
		add(label, c);
		textComp.put(UI.KOSZTY_W_OKRESIE_TRWALOSCI, createTextArea(this, 0, posx++, vo.kosztyWOkresieTrwalosci, c));
	}

	private int createCechaKorzyscParameter(boolean active, EServiceParamsVO param, GridBagConstraints c, int posx, int i) {
		JLabel label;
		JButton dodaj;
		label = createLabel(0, posx++, "" + i + ". cecha", c);
		label.setEnabled(active);
		add(label, c);
		JTextArea textArea = createTextArea(this, 0, posx++, param.cecha, c, 10);
		textArea.setEnabled(active);
		if (active) {
			cecha.add(textArea);
		} else {
			textArea.setBackground(Color.LIGHT_GRAY);
		}

		label = createLabel(0, posx++, "" + i + ". korzyść", c);
		label.setEnabled(active);
		add(label, c);
		textArea = createTextArea(this, 0, posx++, param.korzysc, c, 10);
		textArea.setEnabled(active);
		if (active) {
			korzysc.add(textArea);
		} else {
			textArea.setBackground(Color.LIGHT_GRAY);
		}
		
		label = createLabel(0, posx++, "" + i + ". parametr", c);
		label.setEnabled(active);
		add(label, c);
		textArea = createTextArea(this, 0, posx++, param.parametr, c, 7);
		textArea.setEnabled(active);
		if (active) {
			parametr.add(textArea);
		} else {
			textArea.setBackground(Color.LIGHT_GRAY);
		}
		
		dodaj = createButton(0, posx++, "Dodaj cecha/korzyść/parametr " + (i+1), c);
		dodaj.setActionCommand("add" + i);
		dodaj.addActionListener(this);
		dodaj.setEnabled(active);
		add(dodaj, c);
		
		JButton usun = createButton(0, posx++, "Usun cecha/korzyść/parametr " + i, c);
		usun.setActionCommand("remove" + (i - 1));
		usun.addActionListener(this);
		usun.setEnabled(active);
		add(usun, c);
		return posx;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.startsWith("add")) {
			int index = Integer.valueOf(cmd.substring(3));
			this.vo = getVO();
			this.vo.eusluga.add(index, new EServiceParamsVO());
			
			recreateUI();
		} else if (cmd.startsWith("remove")) {
			this.vo = getVO();
			int index = Integer.valueOf(cmd.substring(6));
			this.vo = getVO();
			this.vo.eusluga.remove(index);
			
			recreateUI();
		}
	}

	private void recreateUI() {
		textComp.clear();
		cecha.clear();
		korzysc.clear();
		parametr.clear();
		removeAll();
		createUI();
		
		invalidate();
		doLayout();
	}
	
}
