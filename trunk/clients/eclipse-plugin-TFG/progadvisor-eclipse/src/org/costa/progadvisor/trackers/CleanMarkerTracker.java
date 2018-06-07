package org.costa.progadvisor.trackers;

import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.exceptions.CostabsException;



public class CleanMarkerTracker extends CommandTracker{

	public CleanMarkerTracker(ConsoleCommand command) {
		super(command);
	}

	@Override
	public void track() throws CostabsException {
		OutputManager.getInstance().getOutputTracker(getiFile()).cleanMarkers();
	}

	@Override
	public void clean() {
	}

	public static Collection<String> getMarkers() {
		return new ArrayList<String>();
	}

}
