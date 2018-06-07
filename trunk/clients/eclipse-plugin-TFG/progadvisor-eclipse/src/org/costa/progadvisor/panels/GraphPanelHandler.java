package org.costa.progadvisor.panels;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.costa.progadvisor.trackers.OutputManager;
import org.eclipse.core.resources.IFile;

public class GraphPanelHandler extends JPanel {

	public static String EMPTY = "emptypanel";

	public GraphPanelHandler() {
		super();
		this.setLayout(new CardLayout());
		JPanel empty = new JPanel();
		add(empty, EMPTY);
		showEmptyPanel(EMPTY);

//		TestPanel panel = new TestPanel();
//		add(panel,EMPTY);
//		showEmptyPanel(EMPTY);
	}

	public void showPanel(IFile file) {
		String id = file.getFullPath().toOSString();
		if (OutputManager.getInstance().getOutputTracker(file) != null) {
			JLabel label = new JLabel (id);
			JPanel newpanel = new JPanel();
			newpanel.add(label);
			//JPanel newpanel = new GraphPanel(file, OutputManager.getInstance()
			//		.getOutputTracker(file));
			add(newpanel, id);
		} else {
			id = EMPTY;
		}

		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, id);

	}

	public void showEmptyPanel(String id) {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, EMPTY);
	}

}
