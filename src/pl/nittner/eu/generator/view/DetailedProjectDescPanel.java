/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pl.nittner.eu.generator.data.DetailedProjectDescVO;
import pl.nittner.eu.generator.data.EServiceParamsVO;

/**
 * @author greg
 *
 */
@SuppressWarnings("serial")
public class DetailedProjectDescPanel extends BasePanel {
	public JTextArea geneza;
	public JTextArea sposobSwiadczenia;
	public JTextField nazwaEUslugi;
	public List<JTextArea> cecha = new ArrayList<JTextArea>();
	public List<JTextArea> korzysc = new ArrayList<JTextArea>();
	public List<JTextArea> parametr = new ArrayList<JTextArea>();
	
	private JTextArea analizaPotrzeb;
	private JTextArea zrodlaPrzychodow;
	private JTextArea analizaOtoczenia;
	private JTextArea analizaSWOT;
	private JTextArea strategiaMarketingowa;
	private JTextArea koncepcjaPromocji;
	private JTextArea kalkulacjaProjektuWCzasie;
	private JTextArea zasobyWiedzy;
	private JTextArea zasobyMaterialne;
	private JTextArea zasobyOsobowe;
	private JTextArea uslugiObce;
	private JTextArea metodologiaOszacowania;
	private JTextArea swiadczenieUslug;
	private JTextArea opisRealizacji;
	private JTextArea identyfikacjaZagrozen;
	private JTextArea rezultatyRealizacji;
	private JTextArea inwestycjeOdtworzeniowe;
	private JTextArea cyklZycia;
	private JTextArea kosztyWOkresieTrwalosci;
	private JTextArea trwaloscPrzychodow;
	
	private DetailedProjectDescVO vo;
	
	public DetailedProjectDescPanel(DetailedProjectDescVO vo) {
		this.vo = vo;
		init();
	}
	
	
	
	public DetailedProjectDescVO getVO() {
		vo.geneza = geneza.getText();
		vo.sposobSwiadczenia = sposobSwiadczenia.getText();
		vo.nazwaEUslugi = nazwaEUslugi.getText();
		
		vo.eusluga = new ArrayList<EServiceParamsVO>();
		for (int i = 0; i < cecha.size(); i++) {
			EServiceParamsVO param = new EServiceParamsVO();
			param.cecha = cecha.get(i).getText();
			param.korzysc = korzysc.get(i).getText();
			param.parametr = parametr.get(i).getText();
			
			vo.eusluga.add(param);
		}
		
		vo.analizaPotrzeb = analizaPotrzeb.getText();
		vo.zrodlaPrzychodow = zrodlaPrzychodow.getText();
		vo.analizaOtoczenia = analizaOtoczenia.getText();
		vo.analizaSWOT = analizaSWOT.getText();
		vo.strategiaMarketingowa = strategiaMarketingowa.getText();
		vo.koncepcjaPromocji = koncepcjaPromocji.getText();
		vo.kalkulacjaProjektuWCzasie = kalkulacjaProjektuWCzasie.getText();
		vo.zasobyWiedzy = zasobyWiedzy.getText();
		vo.zasobyMaterialne = zasobyMaterialne.getText();
		vo.zasobyOsobowe = zasobyOsobowe.getText();
		vo.uslugiObce = uslugiObce.getText();
		vo.metodologiaOszacowania = metodologiaOszacowania.getText();
		vo.swiadczenieUslug = swiadczenieUslug.getText();
		vo.opisRealizacji = opisRealizacji.getText();
		vo.identyfikacjaZagrozen = identyfikacjaZagrozen.getText();
		vo.rezultatyRealizacji = rezultatyRealizacji.getText();
		vo.inwestycjeOdtworzeniowe = inwestycjeOdtworzeniowe.getText();
		vo.cyklZycia = cyklZycia.getText();
		vo.kosztyWOkresieTrwalosci = kosztyWOkresieTrwalosci.getText();
		vo.trwaloscPrzychodow = trwaloscPrzychodow.getText();
		
		return vo;
	}
	
	public void init() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		int posx = 0;
		
		JLabel label = createLabel(0, posx++, "C. SZCZEGÓŁOWY OPIS PROJEKTU", c);
		add(label, c);

		label = createLabel(0, posx++, "15. E-usługa, jako cel projektu na poziomie produktu", c);
		add(label, c);
		
		label = createLabel(0, posx++, "a) Geneza pomysłu, podstawowy opis planowanych e-usług (10000 znaków)", c);
		add(label, c);
		geneza = createTextArea(this, 0, posx++, vo.geneza, c);
		
		label = createLabel(0, posx++, "b) Sposób swiadczenia e-usług - opis funkcjonalny serwisu (12000 znaków)", c);
		add(label, c);
		sposobSwiadczenia = createTextArea(this, 0, posx++, vo.sposobSwiadczenia, c);

		label = createLabel(0, posx++, "Projekt ma na celu przygotowanie, wdrozenie i swiadczenie zaawansowanych" +
				" e-usług o znaczeniu krajowym lub miedzynarodowym (4000 znaków/pole opisowe)", c);
		add(label, c);
		label = createLabel(0, posx++, "Nazwa e-usługi:", c);
		add(label, c);
		nazwaEUslugi = createTextField(this, 0, posx++, vo.nazwaEUslugi, c);

		for (int i = 1; i <= vo.eusluga.size(); i++) {
			label = createLabel(0, posx++, "" + i + ". cecha", c);
			add(label, c);
			cecha.add(createTextArea(this, 0, posx++, vo.eusluga.get(i - 1).cecha, c));

			label = createLabel(0, posx++, "" + i + ". korzyść", c);
			add(label, c);
			korzysc.add(createTextArea(this, 0, posx++, vo.eusluga.get(i - 1).korzysc, c));
			
			label = createLabel(0, posx++, "" + i + ". parametr", c);
			add(label, c);
			parametr.add(createTextArea(this, 0, posx++, vo.eusluga.get(i - 1).parametr, c));
		}

		label = createLabel(0, posx++, "16. Model biznesowy i analiza rynku", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Identyfikacja, charakterystyka i analiza potrzeb grupy docelowej (12000 znaków)", c);
		add(label, c);
		analizaPotrzeb = createTextArea(this, 0, posx++, vo.analizaPotrzeb, c);
		
		label = createLabel(0, posx++, "b) Zidentyfikowane zródła przychodów (8000 znaków)", c);
		add(label, c);
		zrodlaPrzychodow = createTextArea(this, 0, posx++, vo.zrodlaPrzychodow, c);
		
		label = createLabel(0, posx++, "c) Analiza otoczenia konkurencyjnego, substytuty (10000 znaków)", c);
		add(label, c);
		analizaOtoczenia = createTextArea(this, 0, posx++, vo.analizaOtoczenia, c);
		
		label = createLabel(0, posx++, "d) Analiza SWOT (6000 znaków)", c);
		add(label, c);
		analizaSWOT = createTextArea(this, 0, posx++, vo.analizaSWOT, c);
		
		label = createLabel(0, posx++, "e) Zarys strategii marketingowej, analiza popytu na e-usługe oraz polityka cenowa firmy (10000 znaków)", c);
		add(label, c);
		strategiaMarketingowa = createTextArea(this, 0, posx++, vo.strategiaMarketingowa, c);
		
		label = createLabel(0, posx++, "f) Koncepcja promocji, pozyskiwanie klientów i utrzymywania relacji z klientami (10000 znaków)", c);
		add(label, c);
		koncepcjaPromocji = createTextArea(this, 0, posx++, vo.koncepcjaPromocji, c);
		
		label = createLabel(0, posx++, "g) Kalkulacja przychodów projektu w czasie (w poszczególnych etapach i okresie trwałosci, " +
				"w podziale na zródła), rentownosc, okres zwrotu (12000 znaków)", c);
		add(label, c);
		kalkulacjaProjektuWCzasie = createTextArea(this, 0, posx++, vo.kalkulacjaProjektuWCzasie, c);
		
		label = createLabel(0, posx++, "17. Przygotowanie do realizacji projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Zasoby wiedzy, identyfikacja i preferencje odnosnie technologii informatycznych (6000 znaków)", c);
		add(label, c);
		zasobyWiedzy = createTextArea(this, 0, posx++, vo.zasobyWiedzy, c);
		
		label = createLabel(0, posx++, "b) Zasoby materialne dla realizacji projektu – stan obecny, analiza potrzeb (6000 znaków)", c);
		add(label, c);
		zasobyMaterialne = createTextArea(this, 0, posx++, vo.zasobyMaterialne, c);
		
		label = createLabel(0, posx++, "c) Zasoby osobowe dla realizacji projektu – stan obecny i analiza potrzeb (6000 znaków)", c);
		add(label, c);
		zasobyOsobowe = createTextArea(this, 0, posx++, vo.zasobyOsobowe, c);
		
		label = createLabel(0, posx++, "d) Zapotrzebowanie w zakresie usług obcych (6000 znaków)", c);
		add(label, c);
		uslugiObce = createTextArea(this, 0, posx++, vo.uslugiObce, c);
		
		label = createLabel(0, posx++, "e) Metodologia i uzasadnienie oszacowania wysokosci poszczególnych wydatków (12000 znaków)", c);
		add(label, c);
		metodologiaOszacowania = createTextArea(this, 0, posx++, vo.metodologiaOszacowania, c);
		
		label = createLabel(0, posx++, "f) Swiadczenie planowanych e-usług w aspekcie prawnym i etycznym (4000 znaków)", c);
		add(label, c);
		swiadczenieUslug = createTextArea(this, 0, posx++, vo.swiadczenieUslug, c);
		
		label = createLabel(0, posx++, "18. Realizacja projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Opis realizacji poszczególnych etapów projektu – działania i ich finansowanie (12000 znaków)", c);
		add(label, c);
		opisRealizacji = createTextArea(this, 0, posx++, vo.opisRealizacji, c);
		
		label = createLabel(0, posx++, "b) Identyfikacja i neutralizacja zagrozen dla planowej realizacji działan (6000 znaków)", c);
		add(label, c);
		identyfikacjaZagrozen = createTextArea(this, 0, posx++, vo.identyfikacjaZagrozen, c);
		
		label = createLabel(0, posx++, "c) Rezultaty realizacji projektu – opis celów (10000 znaków)", c);
		add(label, c);
		rezultatyRealizacji = createTextArea(this, 0, posx++, vo.rezultatyRealizacji, c);
		
		label = createLabel(0, posx++, "19. Trwałosc projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "a) Opis zasobów oraz inwestycji odtworzeniowych niezbednych dla kontynuacji swiadczenia e-usługi (6000 znaków)", c);
		add(label, c);
		inwestycjeOdtworzeniowe = createTextArea(this, 0, posx++, vo.inwestycjeOdtworzeniowe, c);
		
		label = createLabel(0, posx++, "b) Cykl zycia produktu, wizja ewolucji serwisu i e-usługi (6000 znaków)", c);
		add(label, c);
		cyklZycia = createTextArea(this, 0, posx++, vo.cyklZycia, c);
		
		label = createLabel(0, posx++, "c) Trwałosc zródeł przychodów i zarys długookresowej strategii marketingowej (6000 znaków)", c);
		add(label, c);
		trwaloscPrzychodow = createTextArea(this, 0, posx++, vo.trwaloscPrzychodow, c);
		
		label = createLabel(0, posx++, "d) Koszty stałe i zmienne w okresie trwałosci - kalkulacja (8000 znaków)", c);
		add(label, c);
		kosztyWOkresieTrwalosci = createTextArea(this, 0, posx++, vo.kosztyWOkresieTrwalosci, c);
	}
	
}
