package pl.nittner.eu.generator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.swing.BorderFactory;
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
	private static final String DEFAULT_PROJECTS_FOLDER = ".";
	
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
			setLayout(new GridBagLayout());

			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1.0;
			c.weighty = 1.0;
			c.gridx = 0;
			c.gridy = 0;
			
			mainPane = new ProjectView();
			mainPane.setBorder(BorderFactory.createLineBorder(Color.RED));

			scroller = new JScrollPane(mainPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(scroller, c);
			
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
	    } else if ("increase-font".equals(action)) {
	    	mainPane.increaseFontSize();
	    } else if ("decrease-font".equals(action)) {
	    	mainPane.decreaseFontSize();
	    } else if ("new_project".equals(action)) {
	    	mainPane.addProject(new Project81VO());
	    	mainPane.showAllProjects();
	    	scroller.invalidate();
	    	scroller.doLayout();
 	    } else if ("load".equals(action)) {
	    	JFileChooser chooser = new JFileChooser(); 
	        chooser.setCurrentDirectory(new java.io.File(DEFAULT_PROJECTS_FOLDER));
	        chooser.setDialogTitle("Wybierz plik projektu");
	        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        chooser.setFileFilter(new WildcardFileFilter(new String[]{ "*.json"}));
	        //chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				System.out.println("Loading project from " + chooser.getSelectedFile());
				Project81VO project = FileStorage.load81Project(chooser.getSelectedFile().getAbsolutePath());
				project.path = chooser.getSelectedFile().getAbsolutePath();
				mainPane.addProject(project);
		    	mainPane.showAllProjects();
		    	scroller.invalidate();
		    	scroller.doLayout();
			}
	    } else if ("save_all".equals(action)) {
	    	List<Project81VO> projects = mainPane.getProjectsVO();
	    	for (Project81VO project : projects) {
	    		if (project.path == null) {
			    	JFileChooser chooser = new JFileChooser(); 
			        chooser.setCurrentDirectory(new java.io.File(DEFAULT_PROJECTS_FOLDER));
			        chooser.setDialogTitle("Select project file");
			        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			        chooser.setFileFilter(new WildcardFileFilter(new String[]{ "*.json"}));
			        chooser.setApproveButtonText("Zapisz projekt");
			        //chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
						String filePath = chooser.getSelectedFile().getAbsolutePath();
						if (!filePath.endsWith(".json")) {
							filePath += ".json";
						}
						System.out.println("Storing project in " + filePath);
						FileStorage.storeProject(project, filePath);
						project.path = filePath;
					}
	    		} else {
	    			System.out.println("Storing project in " + project.path);
					FileStorage.storeProject(project, project.path);
	    		}
	    	}
	    } else if ("save_rtf_all".equals(action)) {
	    	List<Project81VO> projects = mainPane.getProjectsVO();
	    	for (Project81VO project : projects) {
	    		String name = "<nowy>";
	    		if (project.path != null) {
	    			name = new File(project.path).getName();
	    		}
		    	JFileChooser chooser = new JFileChooser(); 
		        chooser.setCurrentDirectory(new java.io.File(DEFAULT_PROJECTS_FOLDER));
		        chooser.setDialogTitle("Projekt: " + name);
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        chooser.setFileFilter(new WildcardFileFilter(new String[]{ "*.rtf"}));
		        chooser.setApproveButtonText("Zapisz RTF");
		        //chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getSelectedFile().getAbsolutePath();
					if (!filePath.endsWith(".rtf")) {
						filePath += ".rtf";
					}
					System.out.println("Storing RTF in " + filePath);
					FileStorage.storeProjectAsRTF(project, filePath);
					project.path = chooser.getSelectedFile().getAbsolutePath();
				}
	    	}
	    } else if ("exit".equals(action)) {
	    	askToExitApp();
	    }
		
	}

	private void askToExitApp() {
		int reply = JOptionPane.showConfirmDialog(frame, "Czy zamknąć aplikację?", "Wyjście?", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		menu = new JMenu("Plik");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItem = new JMenuItem("Nowy projekt", KeyEvent.VK_L);
		menuItem.setActionCommand("new_project");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		menu.addSeparator();
		
		menuItem = new JMenuItem("Wczytaj projekt", KeyEvent.VK_L);
		menuItem.setActionCommand("load");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Zapisz wszystkie projekty", KeyEvent.VK_S);
		menuItem.setActionCommand("save_all");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Zapisz wszystkie jako RTF", KeyEvent.VK_R);
		menuItem.setActionCommand("save_rtf_all");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Zakończ", KeyEvent.VK_Q);
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menu = new JMenu("Widok");
		menu.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menu);

		menuItem = new JMenuItem("Zwiększ font", KeyEvent.VK_I);
		menuItem.setActionCommand("increase-font");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Zmniejsz font", KeyEvent.VK_D);
		menuItem.setActionCommand("decrease-font");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		// updateTranslationsMenu();

		return menuBar;
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
