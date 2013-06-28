/**
 * 
 */
package pl.nittner.eu.generator;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

/**
 * @author "Grzegorz Nittner" <grzegorz.nittner@gmail.com>
 *
 */
public class WildcardFileFilter extends FileFilter {
	private String[] wildcards;
	
	public WildcardFileFilter(String[] wildcards) {
		this.wildcards = wildcards;
	}
	
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		final String name = file.getName();
		for (final String wildcard : wildcards) {
			if (FilenameUtils.wildcardMatch(name, wildcard, IOCase.INSENSITIVE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < wildcards.length; i++) {
			if (i > 0) {
				buf.append(",");
			}
			buf.append(wildcards[i]);
		}
		return buf.toString();
	}

}
