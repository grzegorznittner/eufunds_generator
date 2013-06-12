/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pl.nittner.eu.generator.data.ProjectIdentificationVO;

/**
 * @author greg
 *
 */
@SuppressWarnings("serial")
public class ProjectIdentificationPanel extends BasePanel {
	public JTextArea tytul;
	public JTextArea krotkiOpis;
	public JTextArea slowaKluczowe;
	public JTextField obszarGospodarki;
	private JTextArea neutralnoscUzasad;
	private JTextArea inneProjekty;
	private JTextArea projektyPowiazane;
	private JTextField wojewodztwo;
	private JTextField powiat;
	private JTextField gmina;
	private JTextField miejscowosc;
	private JTextField kodPocztowy;
	
	private ProjectIdentificationVO vo;
	
	public ProjectIdentificationPanel(ProjectIdentificationVO vo) {
		this.vo = vo;
		init();
	}
	
	public ProjectIdentificationVO getVO() {
		vo.tytul = tytul.getText();
		vo.krotkiOpis = krotkiOpis.getText();
		vo.slowaKluczowe = slowaKluczowe.getText();
		vo.obszarGospodarki = obszarGospodarki.getText();
		vo.neutralnoscUzasad = neutralnoscUzasad.getText();
		vo.inneProjekty = inneProjekty.getText();
		vo.projektyPowiazane = projektyPowiazane.getText();
		vo.wojewodztwo = wojewodztwo.getText();
		vo.powiat = powiat.getText();
		vo.gmina = gmina.getText();
		vo.miejscowosc = miejscowosc.getText();
		vo.kodPocztowy = kodPocztowy.getText();
		
		return vo;
	}
	
	public void init() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		int posx = 0;
		
		JLabel label = createLabel(0, posx++, "1. Identifykacja projektu", c);
		add(label, c);

		label = createLabel(0, posx++, "a). Tytuł projektu", c);
		add(label, c);
		tytul = createTextArea(this, 0, posx++, vo.tytul, c);
		
		label = createLabel(0, posx++, "b) Krótki opis projektu", c);
		add(label, c);
		krotkiOpis = createTextArea(this, 0, posx++, vo.krotkiOpis, c);

		label = createLabel(0, posx++, "c) Słowa kluczowe", c);
		add(label, c);
		slowaKluczowe = createTextArea(this, 0, posx++, vo.slowaKluczowe, c);

		label = createLabel(0, posx++, "d) Obszar gospodarki", c);
		add(label, c);
		obszarGospodarki = createTextField(this, 0, posx++, vo.obszarGospodarki, c);

		label = createLabel(0, posx++, "6. Projekt ma co najmniej neutralny wpływ na polityki horyzontalne " +
				"Unii Europejskiej wymienione w art. 16 i 17 rozporzadzenia 1083/2006 (4000 znaków):  TAK", c);
		add(label, c);

		label = createLabel(0, posx++, "Uzasadnienie wybranej odpowiedzi", c);
		add(label, c);
		neutralnoscUzasad = createTextArea(this, 0, posx++, vo.neutralnoscUzasad, c);

		label = createLabel(0, posx++, "7. Rodzaj i poziom innowacyjnoci projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "Rodzaj innowacji", c);
		add(label, c);
		label = createLabel(0, posx++, "Poziom innowacyjności", c);
		add(label, c);
		
		label = createLabel(0, posx++, "7. Powiazanie projektu z innymi projektami w ramach NSRO 2007-2013", c);
		add(label, c);
		
		label = createLabel(0, posx++, "a) Informacje o innych projektach Wnioskodawcy (4000 znaków)", c);
		add(label, c);
		inneProjekty = createTextArea(this, 0, posx++, vo.inneProjekty, c);

		label = createLabel(0, posx++, "b) Informacje o projektach podmiotów powiazanych z Wnioskodawca (6000 znaków)", c);
		add(label, c);
		projektyPowiazane = createTextArea(this, 0, posx++, vo.projektyPowiazane, c);

		label = createLabel(0, posx++, "9. Lokalizacja projektu", c);
		add(label, c);
		label = createLabel(0, posx++, "Województwo", c);
		add(label, c);
		wojewodztwo = createTextField(this, 0, posx++, vo.wojewodztwo, c);
		
		label = createLabel(0, posx++, "Powiat", c);
		add(label, c);
		powiat = createTextField(this, 0, posx++, vo.powiat, c);

		label = createLabel(0, posx++, "Gmina", c);
		add(label, c);
		gmina = createTextField(this, 0, posx++, vo.gmina, c);
		
		label = createLabel(0, posx++, "Miejscowość", c);
		add(label, c);
		miejscowosc = createTextField(this, 0, posx++, vo.miejscowosc, c);
		
		label = createLabel(0, posx++, "Kod pocztowy", c);
		add(label, c);
		kodPocztowy = createTextField(this, 0, posx++, vo.kodPocztowy, c);
	}
	
}
