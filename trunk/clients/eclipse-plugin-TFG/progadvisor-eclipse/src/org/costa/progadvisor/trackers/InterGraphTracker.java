package org.costa.progadvisor.trackers;

import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.Command;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.structures.CostabsGraph;

import com.kitfox.svg.SVGElementException;



public class InterGraphTracker extends CommandTracker{

	public InterGraphTracker(ConsoleCommand command) {
		super(command);
	}

	@Override
	public void track() throws CostabsException {
		OutputManager.getInstance().getOutputTracker(getiFile()).getGraph().interMarkNode(getNodes().getNodes(), CostabsGraph.COLOR_SOURCE_MARKED);
	}

	@Override
	public void clean() {
	}

	public static Collection<String> getMarkers() {
		return new ArrayList<String>();
	}

}
