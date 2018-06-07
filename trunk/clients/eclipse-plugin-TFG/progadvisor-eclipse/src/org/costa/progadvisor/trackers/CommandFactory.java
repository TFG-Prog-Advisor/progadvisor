package org.costa.progadvisor.trackers;

import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.AddmarkerCommand;
import org.costa.progadvisor.beans.AlertmsgCommand;
import org.costa.progadvisor.beans.ChangecssCommand;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.GetProjectCommand;
import org.costa.progadvisor.beans.HighlightCommand;
import org.costa.progadvisor.beans.LineWidgetCommand;
import org.costa.progadvisor.beans.WritefileCommand;


public class CommandFactory {

	public static String CONSOLE = "console";
	public static String HIGHLIGHT = "highlight";
	public static String MARKER = "marker";
	public static String GRAPH = "graph";
	public static String MARK_NODE = "mark_node";
	public static String MESSAGE = "message";
	public static String SELECTED = "selected";
	public static String CLEAN_MARKERS = "clean_markers";
	public static String GETPROJECT = "get_project";
	public static String TYPE_GRAPH = "graph";
	public static String TYPE_SOURCE = "source";
	

	public static CommandTracker getTracker (ConsoleCommand command) {	
		if(command.getConsoleid() != null){
			if(command.getConsoleid().equals("Graph")){
			return new GraphTracker (command);
			}
		}
		//System.out.println(command.getContent().getTextFormat());
		if(command.getContent().getTextFormat().equals("html")){
			return new HTMLTracker (command);
		}
		return new ConsoleTracker (command); 		
	}
	public static CommandTracker getTracker (HighlightCommand command) {
		return new HighlightTracker (command); 
		
	}
	public static CommandTracker getTracker (AlertmsgCommand command) {
		return new MessageTracker (command); 
		
	}
	public static CommandTracker getTracker (WritefileCommand command) {
		//return null;
		return new WriteFileTracker (command); 
		
	}
	public static CommandTracker getTracker (ChangecssCommand command) {
		return null;
		//return new ChangecssTracker2 (command); 
		
	}
	public static CommandTracker getTracker (AddmarkerCommand command) {
		return new MarkerTracker (command); 
		
	}
	public static CommandTracker getTracker (LineWidgetCommand command) {
		return null;
		//return new LineWidgetTracker2 (command); 
		
	}
	public static CommandTracker getTracker (DialogboxCommand command) {
		//System.out.println(command.getContent().getTextFormat());
		if(command.getContent().getTextFormat().equals("html")){
			return new HTMLDialogTracker (command);
		}
		return new DialogboxTracker (command); 
		//return new DialogboxTracker2 (command); 
		
	}
	
	public static CommandTracker getTracker (GetProjectCommand command) {
		//return null;
		return new GetProjectTracker (command); 
		
	}
		
	public static CommandTracker getInterTracker (ConsoleCommand command) {
		if(command.getConsoleid()=="Graph")
			return new InterGraphTracker (command);
		return new ConsoleTracker (command); 

	}
	public static CommandTracker getInterTracker (HighlightCommand command) {
		//System.out.println("getInterTracker(HighlightCommand))");
		CommandTracker kk = null;
		try{
			kk = new InterHighlightTracker (command); 
			//System.out.println("kk");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return kk;

	}
	public static CommandTracker getInterTracker (AlertmsgCommand command) {
		return new MessageTracker (command); 

	}
	public static CommandTracker getInterTracker (WritefileCommand command) {
		//return null;
		return new WriteFileTracker (command); 

	}
	public static CommandTracker getInterTracker (ChangecssCommand command) {
		return null;
		//return new ChangecssTracker2 (command); 

	}
	public static CommandTracker getInterTracker (AddmarkerCommand command) {
		return new InterMarkerTracker (command); 

	}
	public static CommandTracker getInterTracker (LineWidgetCommand command) {
		return null;
		//return new LineWidgetTracker2 (command); 

	}
	public static CommandTracker getInterTracker (DialogboxCommand command) {
		return new DialogboxTracker (command); 
		//return new DialogboxTracker2 (command); 

	}


	public static Collection<String> getMarkerIds () {
		ArrayList<String> ids = new ArrayList<String>();
		for (String s: HighlightTracker.getMarkers()) {
			ids.add(s);
		}
		for (String s: MarkerTracker.getMarkers()) {
			ids.add(s);
		}
		for (String s: InterMarkerTracker.getMarkers()) {
			ids.add(s);
		}
		for (String s: InterHighlightTracker.getMarkers()) {
			ids.add(s);
		}
		for (String s: DialogboxTracker.getMarkers()) {
			ids.add(s);
		}
		return ids;
	}
	public static CommandTracker getInterTracker(Command_old command) {
		// TODO Auto-generated method stub
		return null;
	}
}
