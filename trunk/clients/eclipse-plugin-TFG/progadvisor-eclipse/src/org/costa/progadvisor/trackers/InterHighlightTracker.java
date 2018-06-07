package org.costa.progadvisor.trackers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.HighlightCommand;
import org.eclipse.core.resources.IMarker;




public class InterHighlightTracker extends HighlightTracker {

	public static final String MARKER_INTER_HIGHLIGHT = "org.costa.progadvisor.marker.inter.highlight";
	public static final String MARKER_INTER_SELECTED = "org.costa.progadvisor.marker.selected";
	public static final String MARKER_INTER_HIGHLIGHT_WARN = "org.costa.progadvisor.marker.inter.highlightwarn";
	
	public static HashMap<String, String> MARKER_IDS = new HashMap<String, String>();

	{
		InterHighlightTracker.MARKER_IDS.put("info", MARKER_INTER_HIGHLIGHT);
		InterHighlightTracker.MARKER_IDS.put("warning", MARKER_INTER_HIGHLIGHT_WARN);
		InterHighlightTracker.MARKER_IDS.put("selected", MARKER_INTER_SELECTED);
	}
	
		
	public InterHighlightTracker(HighlightCommand command) {
		super(command);
	}
	
	public static Collection<String> getMarkers() {
		if (InterHighlightTracker.MARKER_IDS == null) {
			return new ArrayList<String>();
		}
		return InterHighlightTracker.MARKER_IDS.values();
	}

	@Override
	protected String getMarker (String level) {
		return InterHighlightTracker.MARKER_IDS.get(level);
	}
	
}
