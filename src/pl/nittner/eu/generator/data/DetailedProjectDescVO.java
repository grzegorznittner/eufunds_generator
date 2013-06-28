/**
 * 
 */
package pl.nittner.eu.generator.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author greg
 *
 */
public class DetailedProjectDescVO {
	public DetailedProjectDescVO() {
		eusluga.add(new EServiceParamsVO());
	}
	
	public String tytul;
	public String krotkiOpis;
	public String slowaKluczowe;
	public String obszarGospodarki;
	public String neutoralnoscUzasad;
	public String inneProjekty;
	public String projektyPowiazane;
	
	public String geneza = "";
	public String sposobSwiadczenia = "";
	public String nazwaEUslugi = "";
	
	public List<EServiceParamsVO> eusluga = new ArrayList<EServiceParamsVO>();

	public String analizaPotrzeb = "";
	public String zrodlaPrzychodow = "";
	public String analizaOtoczenia = "";
	public String analizaSWOT = "";
	public String strategiaMarketingowa = "";
	public String koncepcjaPromocji = "";
	public String kalkulacjaProjektuWCzasie = "";
	public String zasobyWiedzy = "";
	public String zasobyMaterialne = "";
	public String zasobyOsobowe = "";
	public String uslugiObce = "";
	public String metodologiaOszacowania = "";
	public String swiadczenieUslug = "";
	public String opisRealizacji = "";
	public String identyfikacjaZagrozen = "";
	public String rezultatyRealizacji = "";
	public String inwestycjeOdtworzeniowe = "";
	public String cyklZycia = "";
	public String kosztyWOkresieTrwalosci = "";
	public String trwaloscPrzychodow = "";
}
