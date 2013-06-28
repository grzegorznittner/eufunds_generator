/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import pl.nittner.eu.generator.data.Project81VO;

/**
 * @author greg
 *
 */
public class ProjectView extends ScrollablePanel {
	private List<Project81VO> projectVO = new ArrayList<Project81VO>();
	private List<DetailedProjectDescPanel> projectView = new ArrayList<DetailedProjectDescPanel>();

	private int fontSize = 14;

	public ProjectView() {
		super(new GridBagLayout());
	}
	
	public void addProject(Project81VO vo) {
		projectVO.add(vo);
		projectView.add(new DetailedProjectDescPanel(this, vo.detailedDesc));
	}
	
	public List<Project81VO> getProjectsVO() {
		for (int i = 0; i < projectView.size(); i++) {
			projectVO.get(i).detailedDesc = projectView.get(i).getVO();
		}
		return projectVO;
	}
	
	public int getMaxCechaKorzyscParam() {
		int max = 0;
		for (Project81VO project : projectVO) {
			if (max < project.detailedDesc.eusluga.size()) {
				max = project.detailedDesc.eusluga.size();
			}
		}
		return max;
	}
	
	private int heightUpdateCounter = 0;
	
	public void showAllProjects() {
		removeAll();
		setScrollableHeight(ScrollableSizeHint.NONE);
		setScrollableWidth(ScrollableSizeHint.FIT);
		setScrollableUnitIncrement(VERTICAL, new IncrementInfo(IncrementType.PIXELS, 50));
		setScrollableBlockIncrement(VERTICAL, IncrementType.PIXELS, 400);
		
		int posx = 0;
		for (DetailedProjectDescPanel view : projectView) {
			view.buildUI();
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = posx++;
			c.gridy = 0;
			c.weightx = (1.0 / projectView.size()) - 0.05;
			//c.insets = new Insets(5, 5, 5, 5);
			view.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			add(view, c);
		}
		
		heightUpdateCounter = 2;
		updateHeight();
	}

	private void updateHeight() {
		if (heightUpdateCounter <= 0) {
			return;
		}
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	if (projectView.size() > 0) {
            		Dimension prefSize = getPreferredSize();
            		prefSize.height = 0;
            		int i = 0;
            		for (DetailedProjectDescPanel view : projectView) {
            			System.out.println("Project " + (i++) + ": bounds h: " + view.getBounds().height
            					+ ", pref h: " + view.getPreferredSize().height + ", half: " + (view.getPreferredSize().height/2));
            			if (prefSize.height < view.getBounds().height) {
            				prefSize.height = view.getBounds().height;
            			}
            			if (prefSize.height < view.getPreferredSize().height/2) {
            				prefSize.height = view.getPreferredSize().height/2;
            			}
            		}
            		prefSize.height += (getMaxCechaKorzyscParam() - 1) * 25;
            		setPreferredSize(prefSize);
            	}
            	heightUpdateCounter--;
            	updateHeight();
            }
        });
	}
	
	public int getFontSizeForComponents() {
		return fontSize;
	}

	public void increaseFontSize() {
		fontSize += 2;
		showAllProjects();
	}

	public void decreaseFontSize() {
		fontSize -= 2;
		showAllProjects();
	}
}
