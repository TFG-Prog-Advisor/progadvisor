package org.costa.progadvisor.panels;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.trackers.OutputTracker;
import org.eclipse.core.resources.IFile;

public class GraphPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private IFile file;
	
	private OutputTracker tracker;
	
	public GraphPanel(IFile file, OutputTracker tracker) {
		super();
		addMouseListener(this);
		this.file = file;
		this.tracker = tracker;
	}
	
	public void setFile(IFile file) {
		this.file = file;
	}

	@Override
	public int getWidth() {
		if (tracker.getGraph()!=null) {
			return tracker.getGraph().getWidth();
		}
		else {
			return 0;
		}
	}
	
	@Override
	public int getHeight() {
		if (tracker.getGraph()!=null) {
			return tracker.getGraph().getHeight();
		}
		else {
			return 0;
		}
	}

	@Override
	public void paint(Graphics g) {
		if (tracker.getGraph()!=null) {
			tracker.getGraph().paint(g, this);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (tracker.getGraph() != null) {
				tracker.getGraph().handleClick(e.getX(), e.getY());
			}
		} catch (CostabsException e1) {
			ConsoleHandler.write("Error handling mouseClicked event in the graph: " + e1.getMessage());
			DialogPrinter.logError(new CostabsException("Error handling mouseClicked event in the graph", e1));
		} catch (Exception e1) {
			ConsoleHandler.write("Error handling mouseClicked event in the graph: " + e1.getMessage());
			DialogPrinter.logError(new CostabsException("Error handling mouseClicked event in the graph", e1));
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
}
