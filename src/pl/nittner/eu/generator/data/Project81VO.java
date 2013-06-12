/**
 * 
 */
package pl.nittner.eu.generator.data;

import java.util.Date;

/**
 * @author greg
 *
 */
public class Project81VO {
	public int wersja = 1;
	public Date modyfikacja = new Date();
	public ProjectIdentificationVO projectIdentification = new ProjectIdentificationVO();
	public DetailedProjectDescVO detailedDesc = new DetailedProjectDescVO();
	
	public String path = null;
	
	public Project81VO() {
		wersja = 1;
		modyfikacja = new Date();
	}
}
