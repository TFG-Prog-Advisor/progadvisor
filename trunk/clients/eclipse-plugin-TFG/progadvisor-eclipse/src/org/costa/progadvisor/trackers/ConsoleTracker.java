package org.costa.progadvisor.trackers;

import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.console.ConsoleHandler;



public class ConsoleTracker extends CommandTracker{

	public ConsoleTracker(ConsoleCommand command) {
		super(command);
	}

	@Override
	public void track() {
		//ConsoleHandler2.write2(getLevel(),getContent());
		ConsoleHandler.write2(getLevel(),getContent(),getConsoleid());
	}

	@Override
	public void clean() {
	}

	public static Collection<String> getMarkers() {
		return new ArrayList<String>();
	}

}
