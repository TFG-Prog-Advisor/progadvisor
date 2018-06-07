package org.costa.progadvisor.trackers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import org.costa.progadvisor.awt.ScrollableSwingComposite;
import org.costa.progadvisor.beans.CostabsOutput;
import org.costa.progadvisor.beans.CostabsOutput2_new;
import org.costa.progadvisor.beans.Ei_out;
import org.costa.progadvisor.beans.Ei_response;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.panels.CostabsGraphPanel;
import org.costa.progadvisor.rulers.CostabsLineNumberColumn;
import org.costa.progadvisor.structures.CostabsConstants;
import org.costa.progadvisor.structures.CostabsXMLFrontend;
import org.eclipse.core.resources.IFile;
import org.eclipse.swt.browser.Browser;



public class OutputManager {

	private HashMap<IFile, OutputTracker> trackers;

	private static OutputManager manager;

//	private GraphPanelHandler graphPanel;
	
	private CostabsLineNumberColumn column;
	
	private ScrollableSwingComposite scroll;
	
	private CostabsGraphPanel panel;
	
	private String htmlxml;	
	
	private Browser pbrowser;

	public OutputManager() {
		trackers = new HashMap<IFile, OutputTracker>();
	}
	
	public static OutputManager getInstance() {
		if (OutputManager.manager == null) {
			OutputManager.manager = new OutputManager();
		}
		return OutputManager.manager;
	}

	public void loadResults(IFile file, String res) throws CostabsException {
		if (trackers.containsKey(file)) {
			OutputTracker tracker = trackers.get(file);
			if (tracker != null) {
				tracker.cleanTrack();
			}
		}		
		
		//File f = new File(CostabsConstants2.OUTPUT_XML);
		//CostabsOutput2_new output = CostabsXMLFrontend2.readOutput(f);
		//CostabsOutput2_new output = CostabsXMLFrontend2.readOutput(f);
		
		InputStream is = new ByteArrayInputStream(res.getBytes());
		Ei_response output = CostabsXMLFrontend.readOutput(is);
		
		System.out.println("output = "+output);		
		
		trackers.put(file, new OutputTracker(file, output));
		
	}

	public void cleanAll (IFile file) throws CostabsException{
		OutputTracker tracker = trackers.get(file);
		if (tracker == null) {
			return;
		}
		tracker.cleanAllInfo();
		trackers.remove(file);
		
		updateView(file);
	}
	
	public OutputTracker getOutputTracker(IFile file) {
		return trackers.get(file);
	}

//	public void setGraphPanel(GraphPanelHandler graphPanel) {
//		this.graphPanel = graphPanel;
//	}
	
	public void setPanel(CostabsGraphPanel panel) {
		this.panel = panel;
	}

	
	public void setColumn(CostabsLineNumberColumn column) {
		this.column = column;
	}

	public void setScroll(ScrollableSwingComposite scroll) {
		this.scroll = scroll;
	}

	public void updateView(IFile file) {
//		if (graphPanel != null) {
//			graphPanel.showPanel(file);
//			graphPanel.repaint();
//		}
		if (panel != null) {
			panel.setFile(file);
			panel.repaint();
		}
		if (scroll != null) {
			scroll.setViewportView(panel);
		}
		
		if (column != null) {
			column.redraw();
		}
	}

	public String getHtmlxml() {
		return htmlxml;
	}

	public void setHtmlxml(String htmlxml) {
		this.htmlxml = htmlxml;
	}

	public Browser getPbrowser() {		
		return pbrowser;
	}

	public void setPbrowser(Browser pbrowser) {		
		this.pbrowser = pbrowser;
	}
	
	

}
