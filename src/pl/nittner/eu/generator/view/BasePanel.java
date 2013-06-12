/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author greg
 *
 */
@SuppressWarnings("serial")
public class BasePanel extends JPanel {
	public JTextArea title;
	public JTextArea shortDescription;
	public JTextArea keywords;
	public JTextField area;
	
	protected JLabel createLabel(int gridx, int gridy, String text, GridBagConstraints c) {
		JLabel label = new JLabel(text);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = gridx;
		c.gridy = gridy;
		c.weightx = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 5, 10);
		return label;
	}

	protected JTextArea createTextArea(JPanel parent, int gridx, int gridy, String text, GridBagConstraints c) {
		JTextArea textArea = new JTextArea(text);
		textArea.setColumns(60);
		textArea.setRows(30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.insets = new Insets(5, 10, 5, 10);
		textArea.setMinimumSize(textArea.getPreferredSize());
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setMinimumSize(new Dimension(300, 300));
		
		parent.add(scroll, c);
		return textArea;
	}

	protected JTextField createTextField(JPanel parent, int gridx, int gridy, String text, GridBagConstraints c) {
		JTextField label = new JTextField(text);
		label.setColumns(60);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.insets = new Insets(5, 10, 5, 10);
		label.setMinimumSize(label.getPreferredSize());

		parent.add(label, c);
		return label;
	}

}
