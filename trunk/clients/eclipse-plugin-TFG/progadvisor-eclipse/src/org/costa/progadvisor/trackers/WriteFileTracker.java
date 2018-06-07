package org.costa.progadvisor.trackers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.AlertmsgCommand;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.Content;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.WritefileCommand;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.structures.CostabsConstants;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;


public class WriteFileTracker extends CommandTracker{

	public WriteFileTracker(WritefileCommand command) {
		super(command);
	}
	

	@Override
	public void track() {	
		
		try {
			IFile file;
			
			file = createFile(this.getFilename());			
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
	} catch (CoreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	
	@Override
	public void clean() {
	}

	public static Collection<String> getMarkers() {
		return new ArrayList<String>();
	}

}
