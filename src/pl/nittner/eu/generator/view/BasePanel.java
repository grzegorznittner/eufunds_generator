/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
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
	
	protected ProjectView project;
		
	protected JLabel createLabel(int gridx, int gridy, String text, GridBagConstraints c) {
		JLabel label = new JLabel(text);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = gridx;
		c.gridy = gridy;
		c.weightx = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 5, 10);
		return label;
	}
	
	protected JButton createButton(int gridx, int gridy, String text, GridBagConstraints c) {
		JButton button = new JButton(text);
		c.fill = GridBagConstraints.NONE;
		c.gridx = gridx;
		c.gridy = gridy;
		c.weightx = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 5, 10);
		return button;
	}

	protected JTextArea createTextArea(JPanel parent, int gridx, int gridy, String text, GridBagConstraints c) {
		return createTextArea(parent, gridx, gridy, text, c, 20);
	}
	
	protected JTextArea createTextArea(JPanel parent, int gridx, int gridy, String text, GridBagConstraints c, int rows) {
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(60);
		textArea.setRows(rows);
		setFontForComponent(textArea);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.insets = new Insets(5, 10, 5, 10);
		textArea.setMinimumSize(textArea.getPreferredSize());
		
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setMinimumSize(new Dimension(300, rows * 15));
		
		parent.add(scroll, c);
		return textArea;
	}

	protected JEditorPane createEditorPane(JPanel parent, int gridx, int gridy, String text, GridBagConstraints c) {
		JEditorPane editor = new JEditorPane();
		editor.setText(text);
		editor.setMinimumSize(new Dimension(200, 200));
		//textArea.setColumns(60);
		//textArea.setRows(30);
		setFontForComponent(editor);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.insets = new Insets(5, 10, 5, 10);
		//editor.setMinimumSize(editor.getPreferredSize());
		
		//JScrollPane scroll = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//scroll.setMinimumSize(new Dimension(300, 300));
		
		//parent.add(scroll, c);
		parent.add(editor, c);
		return editor;
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

	private void setFontForComponent(JComponent comp) {
		comp.setFont(Font.decode("Arial-PLAIN-" + project.getFontSizeForComponents()));
	}
}
