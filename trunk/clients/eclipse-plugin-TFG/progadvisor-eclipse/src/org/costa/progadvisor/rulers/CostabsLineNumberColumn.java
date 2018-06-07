package org.costa.progadvisor.rulers;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.costa.progadvisor.beans.CodelineAction;
import org.costa.progadvisor.beans.Command;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.HighlightCommand;
import org.costa.progadvisor.beans.Interaction;
import org.costa.progadvisor.beans.Line;
import org.costa.progadvisor.beans.Lines;
import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.structures.CostabsConstants;
import org.costa.progadvisor.trackers.CommandFactory;
import org.costa.progadvisor.trackers.CommandTracker;
import org.costa.progadvisor.trackers.HighlightTracker;
import org.costa.progadvisor.trackers.InterHighlightTracker;
import org.costa.progadvisor.trackers.OutputManager;
import org.costa.progadvisor.trackers.OutputTracker;
import org.costa.progadvisor.utils.SourceUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;




public class CostabsLineNumberColumn extends LineNumberRulerColumn {

	private static String ICON_FILE = "icons"+File.separator+"costabs_rule_marker.png";

//	private boolean visible = false;

//	private List<Interaction> interactions;

//	private TreeSet<Integer> linesSet;
	
	private LineNumberListener listener;
	
	public static Image COLUMN_IMAGE; 

	{
		try {
			ImageDescriptor image = AbstractUIPlugin.imageDescriptorFromPlugin(CostabsConstants.PLUGIN_ID, CostabsLineNumberColumn.ICON_FILE);
			COLUMN_IMAGE = image.createImage();
		} catch (Exception e){
			DialogPrinter.logError(new Exception(CostabsLineNumberColumn.ICON_FILE + " cannot be found: " + e.getMessage(), e));
		}
	}

	public CostabsLineNumberColumn() {
		OutputManager.getInstance().setColumn(this);
	}

	@Override
	protected String createDisplayString(int line) {
		return "";
	}

	@Override
	protected int computeNumberOfDigits() {
		//return super.computeNumberOfDigits() + 1;
		return 2;
	}

	/*
	 * @see org.eclipse.jface.text.source.IVerticalRulerInfo#getLineOfLastMouseButtonActivity()
	 */
	public int getLineOfLastMouseButtonActivity() {
		return getParentRuler().getLineOfLastMouseButtonActivity();
	}

//	@Override
//	protected void paintLine(int line, int y, int lineheight, GC gc,
//			Display display) {
//		super.paintLine(line, y, lineheight, gc, display);
//	}

	@Override
	protected void paintLine(int line, int y, int lineheight, GC gc, Display display) {
		int l = line + 1;
		IFile activeFile = SourceUtils.getActiveFile();
		
		OutputTracker tracker = OutputManager.getInstance().getOutputTracker(activeFile);
		listener.setFile(activeFile);
		if (tracker != null) {
			TreeSet<Integer> linesSet = tracker.getLinesSet();
			
			if (linesSet.contains(l)) {
				gc.drawImage(CostabsLineNumberColumn.COLUMN_IMAGE, 0, y);
			}
			
		}
		
		super.paintLine(line, y, lineheight, gc, display);
	}

	@Override
	public Control createControl(CompositeRuler parentRuler,
			Composite parentControl) {
		Control c = super.createControl(parentRuler, parentControl);
		listener = new LineNumberListener(this);
		c.addMouseListener(listener);
		return c;

	}
}

class LineNumberListener implements MouseListener {

	private CostabsLineNumberColumn column;
	
	private IFile file;

	LineNumberListener (CostabsLineNumberColumn column) {
		this.column = column;
	}

	public void setFile(IFile file) {
		this.file = file;
	}

	@Override
	public void mouseUp(MouseEvent e) {}

	@Override
	public void mouseDown(MouseEvent e) {}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		//System.out.println("mouseDoubleClick");
		int line = column.getLineOfLastMouseButtonActivity() + 1;
		
		IFile activeFile = SourceUtils.getActiveFile();
		OutputTracker tracker = OutputManager.getInstance().getOutputTracker(activeFile);
		
		
		if (tracker == null) {
			return;
		}
		
		TreeSet<Integer> linesSet = tracker.getLinesSet();
		

		if (!linesSet.contains(line)) {
			return;
		}

		if(tracker.getOutput().getEi_app_output().getEiout()==null){
			return;
		}
		for (CodelineAction inter: tracker.getOutput().getEi_app_output().getEiout().getInteractions().getCodeline()) {
			
			if (inter.getLines() == null) {
				continue;
			}

			boolean found = false;
			for(Line ll: inter.getLines().getLines()) {
				if (Integer.parseInt(ll.getFrom()) == line) {
					found = true;
				}
			}

			if (!found) {
				continue;
			}
			
			if (!Interaction.NO_CLEAN_INTERACTION_VALUE.equals(inter.getAutoclean())) {
				try {
					tracker.cleanInteractions();
				} catch (CostabsException e1) {
					ConsoleHandler.write("warning", "ERROR: There was a problem trying to clean previous markers");
				}
			}
			
			
			//List<ConsoleCommand> consolecommands = inter.getCommands().getConsoleCommand();
			List<HighlightCommand> highlightCommands = inter.getCommands().getHighlightline();
			//ArrayList<CommandTracker2> trackers = new ArrayList<CommandTracker2>();
			
			// Command to highlight the line selected  
			//ConsoleCommand c = new ConsoleCommand ();
			HighlightCommand c = new HighlightCommand ();
			c.setType(CommandFactory.HIGHLIGHT);
			ArrayList<Line> ls = new ArrayList<Line>();
			Line l = new Line ();
			l.setFrom(String.valueOf(line));
			
			
			
			System.out.println("line " + String.valueOf(line));
			
			ls.add(l);
			Lines lss = new Lines();
			lss.setLines(ls);
			c.setLines(lss);
			c.setLevel(CommandFactory.HIGHLIGHT);
			highlightCommands.add(c);			
			
		
			for(HighlightCommand command: highlightCommands) {				
				CommandTracker ctracker =  CommandFactory.getInterTracker(command);				
				
				try {
					
					ctracker.setiFile(activeFile);					
					ctracker.track();
					
					//trackers.add(ctracker);					
				}
				catch (CostabsException e1) {
					ConsoleHandler.write("warning", "ERROR: Marker with text '" + ctracker.getContent().getText()  + "cannot be printed: " + e1.getMessage());
				}
			}
		
			List<DialogboxCommand> dialogboxCommands = inter.getCommands().getDialogbox();
			if(dialogboxCommands != null){
				
				for(DialogboxCommand dialogboxCommand: dialogboxCommands) {
					CommandTracker ctracker = CommandFactory.getTracker(dialogboxCommand); 
					try {
						ctracker.setiFile(file);						
						ctracker.track();						
					}
					catch (CostabsException e1) {
						if (ctracker.getiFile() != null) {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while reading the file " + ctracker.getiFile());
						}
						else {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while printing the marker with text " + ctracker.getContent().getText() + " and level " + ctracker.getLevel());
						}
					}
				}
			}
		
		} 
		
		
		
		OutputManager.getInstance().updateView(file);

	}
}
