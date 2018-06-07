package org.costa.progadvisor.dialogs;

import org.costa.progadvisor.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

public class DialogPrinter {

	public static void printError (Shell shell, Exception e, String error) {
		Status status = new Status(IStatus.ERROR, "EasyInterface", 0,
				e.getMessage(), null);
		ErrorDialog.openError(shell, "EasyInterface Error", error, status);		
		
//		MessageDialog.openInformation(
//				window.getShell(),
//				"Problem",
//				"An error has ocurred: " + e.getMessage());
	}
	
	public static void logError (Exception e) {
		Status status = new Status(IStatus.ERROR, "EasyInterface", 0,
				e.getMessage(), e);
		Activator.getDefault().getLog().log(status);
	}
	
	public static void printMessage (Shell shell, String message) {
		MessageDialog.openInformation(shell, "EasyInterface message", message);		
	}

	public static void printWarning (Shell shell, String message) {
		MessageDialog.openWarning(shell, "EasyInterface  message",message);		
	}

	
}
