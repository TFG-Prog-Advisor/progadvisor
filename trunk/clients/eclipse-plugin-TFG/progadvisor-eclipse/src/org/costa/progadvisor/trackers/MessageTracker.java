package org.costa.progadvisor.trackers;

import java.util.ArrayList;
import java.util.Collection;

import org.costa.progadvisor.beans.AlertmsgCommand;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.structures.CostabsConstants;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;


public class MessageTracker extends CommandTracker{

	public MessageTracker(AlertmsgCommand command) {
		super(command);
	}
	

	@Override
	public void track() {
		
		if (CostabsConstants.LEVEL_WARN.equals(getLevel())) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			    public void run() {
				    Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				    DialogPrinter.printWarning(activeShell, getContent().getText());
				}
			});
		}
		else {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			    public void run() {
				    Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				    DialogPrinter.printMessage(activeShell, getContent().getText());
				}
			});
		}
//		if (CostabsConstants.LEVEL_WARN.equals(getLevel())) {
//			DialogPrinter.printWarning(null, getText());
//		}
//		else {
//			DialogPrinter.printMessage(null, getText());
//		}
	}

	@Override
	public void clean() {
	}

	public static Collection<String> getMarkers() {
		return new ArrayList<String>();
	}

}
