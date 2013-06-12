package pl.nittner.eu.generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import pl.nittner.eu.generator.data.Project81VO;
import pl.nittner.eu.generator.view.ProjectView;


@SuppressWarnings("serial")
public class EUGenMainForm extends JPanel implements ActionListener{
	private static final String APP_NAME = "EU Funds - 8.1 Generator";
	private static final String DEFAULT_PROJECTS_FOLDER = "/home/greg/projects/eu_funds_generator/";
	
	static protected JFrame frame;

	protected JTable table;
	protected JScrollPane scroller;
	protected ProjectView mainPane;
	protected JPanel projectPane1, projectPane2;
	
	protected JMenu mainMenu;
	
	public static JFrame frameInstance() {
		return frame;
	}

	public static void showOptionPane(String title, String msg, int messageType) {
	    JOptionPane optionPane = new JOptionPane();
	    optionPane.setMessage(msg);
	    optionPane.setMessageType(messageType);
	    JDialog dialog = optionPane.createDialog(frame, title);
	    dialog.setVisible(true);
	}
	
	public static void showErrorDialog(String title, Exception e) {
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		textArea.setEditable(false);
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		textArea.setText(writer.toString());
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(500, 200));
		
		
		JOptionPane.showMessageDialog(frame, scrollPane, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public EUGenMainForm() {
		if (frame == null) {
			throw new InstantiationError("frame is not set!");
		}
		initComponent();
		setPreferredSize(new Dimension(800, 600));
		setSize(800, 600);
		frame.setJMenuBar(createMenu());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				askToExitApp();
			}
		});
	}

	public void initComponent() {
		try {
			setBackground(Color.LIGHT_GRAY);
			setOpaque(true);
			setLayout(new BorderLayout());

			mainPane = new ProjectView();

			scroller = new JScrollPane(mainPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(scroller, BorderLayout.CENTER);
			
			//Provide minimum sizes for the two components in the split pane
			Dimension minimumSize = new Dimension(100, 50);
			scroller.setMinimumSize(minimumSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if ("save".equals(action)) {
	    } else if ("cancel".equals(action)) {
	    } else if ("undo".equals(action)) {
	    } else if ("add_translation".equals(action)) {
	    } else if ("new_project".equals(action)) {
	    	mainPane.addProject(new Project81VO());
	    	mainPane.showAllProjects();
 	    } else if ("load".equals(action)) {
	    	JFileChooser chooser = new JFileChooser(); 
	        chooser.setCurrentDirectory(new java.io.File(DEFAULT_PROJECTS_FOLDER));
	        chooser.setDialogTitle("Select project file");
	        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				System.out.println("Loading project from " + chooser.getSelectedFile());
				Project81VO project = FileStorage.load81Project(chooser.getSelectedFile().getAbsolutePath());
				project.path = chooser.getSelectedFile().getAbsolutePath();
				mainPane.addProject(project);
		    	mainPane.showAllProjects();
			}
	    } else if ("save_all".equals(action)) {
	    	List<Project81VO> projects = mainPane.getProjectsVO();
	    	for (Project81VO project : projects) {
	    		if (project.path == null) {
			    	JFileChooser chooser = new JFileChooser(); 
			        chooser.setCurrentDirectory(new java.io.File(DEFAULT_PROJECTS_FOLDER));
			        chooser.setDialogTitle("Select project file");
			        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			        chooser.setApproveButtonText("Save project");
			        chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
						System.out.println("Storing project in " + chooser.getSelectedFile());
						FileStorage.storeProject(project, chooser.getSelectedFile().getAbsolutePath());
						project.path = chooser.getSelectedFile().getAbsolutePath();
					}
	    		} else {
	    			System.out.println("Storing project in " + project.path);
					FileStorage.storeProject(project, project.path);
	    		}
	    	}
	    } else if ("exit".equals(action)) {
	    	askToExitApp();
	    }
		
	}

	private void askToExitApp() {
		int reply = JOptionPane.showConfirmDialog(frame, "Do you want to quit the application?", "Exit?", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void importXLSFile() {
		/*
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File(DEFAULT_TRANSLATIONS_FOLDER));
		chooser.setDialogTitle("Select XLS file to import");
		chooser.addChoosableFileFilter(new WildcardFileFilter(new String[]{ "*.xls", "*.xlsx"}));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			String selectedFile = chooser.getSelectedFile().getAbsolutePath();
			if (tableModel != null) {
				XLSImportDialog dialog = new XLSImportDialog(frame, tableModel, selectedFile);
				if (dialog.changesConfirmed()) {
					
					for (TranslationFile file : tableModel.getTranslations()) {
						file.updateEnablerButton();
					}
				}
			}
		} */
	}

	private void exportXLSFile() {
		/*
		JFileChooser chooser = new JFileChooser();
		String filePath = tableModel.getTranslations().get(0).getTranslationFilePath();
		chooser.setCurrentDirectory(new File(filePath).getParentFile().getAbsoluteFile());
		chooser.setDialogTitle("Select XLS file to export");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(new WildcardFileFilter(new String[]{ "*.xls", "*.xlsx"}));
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			String selectedFile = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("Exporting XLS file " + selectedFile);
			try {
				ResourceExport.exportToXLS(selectedFile, tableModel.getTranslations());
			} catch (Exception e) {
				e.printStackTrace();
	    		showErrorDialog("Resource export error!", e);
			}
		}
		 */
	}

	private void exportHTMLFile() {
		/*
		JFileChooser chooser = new JFileChooser();
		String filePath = tableModel.getTranslations().get(0).getTranslationFilePath();
		chooser.setCurrentDirectory(new File(filePath).getParentFile().getAbsoluteFile());
		chooser.setDialogTitle("Select HTML file to export");
		chooser.addChoosableFileFilter(new WildcardFileFilter(new String[]{ "*.htm", "*.html"}));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			String selectedFile = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("Exporting HTML file " + selectedFile);
			try {
				ResourceExport.exportToHTML(selectedFile, tableModel.getTranslations());
			} catch (Exception e) {
				e.printStackTrace();
	    		showErrorDialog("Resource export error!", e);
			}
		}
		*/
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItem = new JMenuItem("New project", KeyEvent.VK_L);
		menuItem.setActionCommand("new_project");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		menu.addSeparator();
		
		menuItem = new JMenuItem("Load project", KeyEvent.VK_L);
		menuItem.setActionCommand("load");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save all projects", KeyEvent.VK_S);
		menuItem.setActionCommand("save_all");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menu.addSeparator();
				
		menuItem = new JMenuItem("Exit", KeyEvent.VK_Q);
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		mainMenu = new JMenu("Translations");
		menu.setMnemonic(KeyEvent.VK_T);
		menuBar.add(mainMenu);
		
		// updateTranslationsMenu();

		return menuBar;
	}

	private void updateTranslationsMenu() {
		mainMenu.removeAll();
		
		JMenuItem menuItem;
		menuItem = new JMenuItem("show all");
		menuItem.setActionCommand("show_all");
		menuItem.addActionListener(this);
		mainMenu.add(menuItem);
		
		menuItem = new JMenuItem("hide all");
		menuItem.setActionCommand("hide_all");
		menuItem.addActionListener(this);
	}

	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
			frame = new JFrame();
			frame.setTitle(APP_NAME);
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			frame.setResizable(true);
			frame.setBackground(Color.LIGHT_GRAY);
			frame.getContentPane().add(new EUGenMainForm());
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
