/**
 * 
 */
package pl.nittner.eu.generator.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import pl.nittner.eu.generator.data.Project81VO;

/**
 * @author greg
 *
 */
public class ProjectView extends ScrollablePanel {
	private List<Project81VO> projectVO = new ArrayList<Project81VO>();
	private List<DetailedProjectDescPanel> projectView = new ArrayList<DetailedProjectDescPanel>();
	
	public ProjectView() {
		super(new GridBagLayout());
	}
	
	public void addProject(Project81VO vo) {
		projectVO.add(vo);
		projectView.add(new DetailedProjectDescPanel(vo.detailedDesc));
	}
	
	public List<Project81VO> getProjectsVO() {
		for (int i = 0; i < projectView.size(); i++) {
			projectVO.get(i).detailedDesc = projectView.get(i).getVO();
		}
		return projectVO;
	}
	
	public void showAllProjects() {
		removeAll();
		setScrollableHeight(ScrollableSizeHint.NONE);
		setScrollableWidth(ScrollableSizeHint.FIT);
		setScrollableUnitIncrement(VERTICAL, new IncrementInfo(IncrementType.PIXELS, 50));
		setScrollableBlockIncrement(VERTICAL, IncrementType.PIXELS, 400);
		
		int posx = 0;
		for (DetailedProjectDescPanel view : projectView) {
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = posx++;
			c.gridy = 0;
			c.weightx = 1 / projectView.size();
			c.insets = new Insets(5, 10, 5, 10);
			add(view, c);
		}
		
		invalidate();
		doLayout();
	}
}
