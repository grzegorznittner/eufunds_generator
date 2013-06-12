/**
 * 
 */
package pl.nittner.eu.generator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

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
				int reply = JOptionPane.showConfirmDialog(EUGenMainForm.frameInstance(), "Plik projektu " + projectFile.getName() + " istnije",
						"Czy chcesz nadpisać istnijące dane?", JOptionPane.YES_NO_OPTION);
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
