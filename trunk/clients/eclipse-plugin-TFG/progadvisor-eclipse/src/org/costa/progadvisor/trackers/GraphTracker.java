package org.costa.progadvisor.trackers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.Command;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.exceptions.CostabsException;
import org.eclipse.core.resources.IFile;



public class GraphTracker extends CommandTracker{

	private ConsoleCommand command;
	
	public GraphTracker(ConsoleCommand command) {
		super(command);
		this.command = command;
	}

	@Override
	public void track() throws CostabsException {
		IFile f =  (getiFile());
		
		//System.out.println("track graph");
		if (!f.exists()) {
			//System.out.println("tnot exist");
			ConsoleHandler.write("warning","WARN: File " + getiFile() + " does not exist");
			return;
		}
		
		OutputManager.getInstance().getOutputTracker(getiFile()).createGraph(command);
		//PRUEBA
		OutputManager.getInstance().getOutputTracker(getiFile()).createGraphHTML(command);
	}

	@Override
	public void clean() {
	}

	public static Collection<String> getMarkers() {
		return new ArrayList<String>();
	}

}
