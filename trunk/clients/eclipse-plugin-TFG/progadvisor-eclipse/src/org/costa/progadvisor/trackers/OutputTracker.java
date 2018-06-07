package org.costa.progadvisor.trackers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JPanel;

import org.costa.progadvisor.beans.AddmarkerCommand;
import org.costa.progadvisor.beans.CodelineAction;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.Ei_response;
import org.costa.progadvisor.beans.GetProjectCommand;
import org.costa.progadvisor.beans.HighlightCommand;
import org.costa.progadvisor.beans.Interactions;
import org.costa.progadvisor.beans.Line;
import org.costa.progadvisor.beans.OnclickAction;
import org.costa.progadvisor.beans.WritefileCommand;
import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.structures.CostabsGraph;
import org.costa.progadvisor.structures.CostabsHTMLGraph;
import org.costa.progadvisor.structures.CostabsSVGGraph;
import org.costa.progadvisor.utils.SourceUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;



public class OutputTracker {

	private Ei_response output;

	private IFile file;

	private TreeSet<Integer> linesSet;

	private ArrayList<CommandTracker> trackers = new ArrayList<CommandTracker>();
	
	private CostabsGraph graph;
	
	private CostabsHTMLGraph graph2;


	public OutputTracker(IFile file, Ei_response output) {
		super();
		this.output = output;
		this.file = file;
		setLines ();
	}

	
	
	
	public void setOutput(Ei_response output) {
		this.output = output;
	}



	public void setFile (IFile file) {
		this.file = file;

	}
	public void cleanTrack () {
		if (trackers == null) {
			file = null;
			return;
		}
		for(CommandTracker tracker: trackers) {
			tracker.clean();
		}
		trackers = null; 
		file = null; 
	}

	public void trackResults () {
		trackers = new ArrayList<CommandTracker>();
		
		if(output.getEi_app_output().getEiout()==null)
			return;
		
		
		if(output.getEi_app_output().getEi_error()!=null){			
			ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR:  " + output.getEi_app_output().getEi_error());
		}
		
		List<ConsoleCommand> consolecommands = output.getEi_app_output().getEiout().getCommands().getConsoleCommand();
		List<HighlightCommand> highlightcommands = output.getEi_app_output().getEiout().getCommands().getHighlightline();
		List<AddmarkerCommand> addmarkerCommands = output.getEi_app_output().getEiout().getCommands().getAddmarker();
		List<DialogboxCommand> dialogboxCommands = output.getEi_app_output().getEiout().getCommands().getDialogbox();
		List<GetProjectCommand> getprojectcommands = output.getEi_app_output().getEiout().getCommands().getGetProject();
		//??????????
		List<AddmarkerCommand> addnlinemarkerCommands = output.getEi_app_output().getEiout().getCommands().getAddnlinemarker();
		List<WritefileCommand> writefileCommands = output.getEi_app_output().getEiout().getCommands().getWritefile();
		
		if (consolecommands != null) {			
			for(ConsoleCommand consolecommand: consolecommands) {
				CommandTracker tracker = CommandFactory.getTracker(consolecommand); 
				try {
					//System.out.println("consolecommand getConsoleid " + consolecommand.getConsoleid());
					//System.out.println("consolecommand getOutclass " + consolecommand.getOutclass());
					
					//System.out.println(tracker.getClass().getName());
					
					if(consolecommand.getConsoleid() != null)
						tracker.setConsoleid(consolecommand.getConsoleid());
										
					tracker.setiFile(file);					
					tracker.track();
					trackers.add(tracker);
				}
				catch (CostabsException e1) {
					if (tracker.getiFile() != null) {
						ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR:1  while reading the file " + tracker.getiFile());
					}
					else {
						ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 1 while printing the marker with text " + tracker.getContent().getText() + " and level " + tracker.getLevel());
					}
				}
				
				
				
				
			}
		}
		//else{
		
			if(highlightcommands != null){
				
				for(HighlightCommand highlightcommand: highlightcommands) {
					CommandTracker tracker = CommandFactory.getTracker(highlightcommand); 
					try {
						tracker.setiFile(file);						
						tracker.track();
						trackers.add(tracker);
					}
					catch (CostabsException e1) {
						if (tracker.getiFile() != null) {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 2 while reading the file " + tracker.getiFile());
						}
						else {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 2 while printing the marker with text " + tracker.getContent().getText() + " and level " + tracker.getLevel());
						}
					}
				}
			//}
			//else{
			//	System.out.println("else highlightcommands == null");
			//	return;
			//}
		}
			
			
			if(addmarkerCommands != null){
				
				for(AddmarkerCommand addmarkerCommand: addmarkerCommands) {
					CommandTracker tracker = CommandFactory.getTracker(addmarkerCommand); 
					try {
						tracker.setiFile(file);						
						tracker.track();
						trackers.add(tracker);
					}
					catch (CostabsException e1) {
						if (tracker.getiFile() != null) {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while reading the file " + tracker.getiFile());
						}
						else {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while printing the marker with text " + tracker.getContent().getText() + " and level " + tracker.getLevel());
						}
					}
				}
			}
			
			if(addnlinemarkerCommands != null){
				
				for(AddmarkerCommand addmarkerCommand: addnlinemarkerCommands) {
					CommandTracker tracker = CommandFactory.getTracker(addmarkerCommand); 
					try {
						tracker.setiFile(file);						
						tracker.track();
						trackers.add(tracker);
					}
					catch (CostabsException e1) {
						if (tracker.getiFile() != null) {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while reading the file " + tracker.getiFile());
						}
						else {
							ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while printing the marker with text " + tracker.getContent().getText() + " and level " + tracker.getLevel());
						}
					}
				}
			}
				
				
				if(dialogboxCommands != null){
					
					for(DialogboxCommand dialogboxCommand: dialogboxCommands) {
						CommandTracker tracker = CommandFactory.getTracker(dialogboxCommand); 
						try {
							tracker.setiFile(file);						
							tracker.track();
							trackers.add(tracker);
						}
						catch (CostabsException e1) {
							if (tracker.getiFile() != null) {
								ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while reading the file " + tracker.getiFile());
							}
							else {
								ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 3 while printing the marker with text " + tracker.getContent().getText() + " and level " + tracker.getLevel());
							}
						}
					}
				}
				
				
				if (writefileCommands != null) {
					
					for(WritefileCommand writefilecommand: writefileCommands) {
						CommandTracker tracker = CommandFactory.getTracker(writefilecommand); 
						try {
							//System.out.println("writefilecommand getPath " + writefilecommand.getPath());
							//System.out.println("writefilecommand getOutclass " + writefilecommand.getTexto());
							
							//System.out.println(tracker.getClass().getName());
							
							if(writefilecommand.getPath() != null)
								tracker.setPath(writefilecommand.getPath());
												
							tracker.setiFile(file);					
							tracker.track();
							trackers.add(tracker);
						}
						catch (CostabsException e1) {
							if (tracker.getiFile() != null) {
								ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR:1  while reading the file " + tracker.getiFile());
							}
							else {
								ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR: 1 while printing the marker with text " + tracker.getContent().getText() + " and level " + tracker.getLevel());
							}
						}
					}
				}
				
				if (getprojectcommands != null) {
					System.out.println("ENTRA EN EL GETPROJECTCOMMANDS");
					for(GetProjectCommand getprojectcommand: getprojectcommands) {
						CommandTracker tracker = CommandFactory.getTracker(getprojectcommand); 
						try {
							//System.out.println("consolecommand getConsoleid " + consolecommand.getConsoleid());
							//System.out.println("consolecommand getOutclass " + consolecommand.getOutclass());
							
							//System.out.println(tracker.getClass().getName());
							
							if(getprojectcommand.getPath() != null)
								tracker.setPath(getprojectcommand.getPath());
							if(getprojectcommand.getTexto() != null)
								tracker.setText(getprojectcommand.getTexto());				
							tracker.track();
							trackers.add(tracker);
						}
						catch (CostabsException e1) {
							if (tracker.getText() != null) {
								ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR:1  while reading the text " + tracker.getText());
							}
						}
						
						
						
						
					}
				}
			//}
			//else{
			//	System.out.println("else highlightcommands == null");
			//	return;
			//}
		}
		
		
		
	

	public void cleanInteractions () throws CostabsException {
		try {
			for(String s: InterMarkerTracker.getMarkers()) {
				file.deleteMarkers(s, false, IResource.DEPTH_INFINITE);
			}
			for(String s: InterHighlightTracker.getMarkers()) {
				file.deleteMarkers(s, false, IResource.DEPTH_INFINITE);
			}
			if (graph != null) {
				graph.cleanMarked();
			}
		}
		catch (CoreException e) {
			throw new CostabsException ("An error has ocurred while cleaning the information: " + e.getMessage());
		}
	}

	public void cleanMarkers () throws CostabsException {
		try {
			IFile file = SourceUtils.getActiveFile();
			Collection<String> markers = CommandFactory.getMarkerIds();
			for (String s: markers) {
				file.deleteMarkers(s, false, IResource.DEPTH_INFINITE);
			}
		}
		catch (CoreException e) {
			throw new CostabsException ("An error has ocurred while cleaning the information: " + e.getMessage());
		}
	}

	public void cleanAllInfo () throws CostabsException {
		cleanMarkers();
		cleanInteractions();
		linesSet = new TreeSet<Integer>();
		if (graph != null) {
			graph.cleanGraph();
		}
	}

	public Ei_response getOutput () {
		return output;
	}

	public IFile getFile () {
		return file;
	}

	public TreeSet<Integer> getLinesSet() {
		return linesSet;
	}

	private void setLines () {
		linesSet = new TreeSet<Integer>();
		if(output.getEi_app_output().getEiout()!=null){
			Interactions interactions = output.getEi_app_output().getEiout().getInteractions();			
			
			
			if (interactions == null){
				return;
			}			
		
			
			/*for(Interaction2 inter: interactions.getInteractions()){
				if (!CommandFactory2.TYPE_SOURCE.equals(inter.getType())) {
					continue;
				}
				List<Line> lines = inter.getCodeline().getLines();

				for(Line l: lines) {
					System.out.println("setLines()" + l.getFrom());
					System.out.println("setLines()" + l.getTo());
					
					linesSet.add(Integer.parseInt(l.getFrom()));
				}
			}*/
			//System.out.println("interactions.getCodeline() "+ interactions.getCodeline());
			
			if(interactions.getCodeline()!=null){				
				for(CodelineAction inter: interactions.getCodeline()){
					if (!CommandFactory.TYPE_SOURCE.equals(inter.getType())) {
						continue;
					}
					List<Line> lines = inter.getLines().getLines();

					for(Line l: lines) {
						//System.out.println("setLines()" + l.getFrom());
						//System.out.println("setLines()" + l.getTo());

						linesSet.add(Integer.parseInt(l.getFrom()));
					}
				}
			}
			
			
		}
		else{
			if (output.getEi_app_output().getEi_error()!=null){
				
				ConsoleHandler.write(ConsoleHandler.ERROR, "ERROR:  " + output.getEi_app_output().getEi_error());
			}
			else{
				System.out.println("Ei_error == null && Eiout()==null");
			}
		}
	}
	
	
	public CostabsGraph getGraph () {
		return graph;
	}
	
	public void createGraph (ConsoleCommand command) throws CostabsException{
		graph = new CostabsSVGGraph();
		graph.loadGraph(file,command);
	}
	
	//PRUEBA
	public void createGraphHTML (ConsoleCommand command) throws CostabsException{
		graph2 = new CostabsHTMLGraph();
		graph2.loadHTML(file,command);
	}
	
	public void createGraphHTML (DialogboxCommand command) throws CostabsException{
		graph2 = new CostabsHTMLGraph();
		graph2.loadHTML(file,command);
	}

}
