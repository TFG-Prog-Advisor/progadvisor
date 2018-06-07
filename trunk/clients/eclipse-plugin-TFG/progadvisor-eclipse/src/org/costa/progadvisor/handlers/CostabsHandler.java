package org.costa.progadvisor.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.costa.progadvisor.Activator;
import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.console.CostabsShellCommand;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.dialogs.OptionsDialog;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.structures.CostabsConstants;
import org.costa.progadvisor.structures.CostabsXMLFrontend;
import org.costa.progadvisor.structures.ResultTracker;
import org.costa.progadvisor.trackers.GetProjectTracker;
import org.costa.progadvisor.trackers.OutputManager;
import org.costa.progadvisor.trackers.OutputTracker;
import org.costa.progadvisor.utils.SourceUtils;
import org.costa.progadvisor.utils.ZipUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

//import abs.backend.prolog.PrologBackend;
//import abs.frontend.ast.Model;

//JCF//import eu.hatsproject.absplugin.costabslink.CostabsLink;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CostabsHandler extends AbstractHandler {

	public static ResultTracker STORAGE_COSTABS = new ResultTracker();
	boolean filterUnreachable = false;
	// The default is true. In that case the whole project is compiled and the
	// frontend
	// calls Antonio's reachability filtering. Otherwise, there's no filtering and
	// it would
	// only work with a single ABS file

	/**
	 * The constructor.
	 */
	public CostabsHandler() {
	}

	/**
	 * the command has been executed, so extract the needed information from the
	 * application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Analizando");
		String res = "";

		final Shell shellEclipse = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
		CostabsShellCommand shell = new CostabsShellCommand();

		try {
			ConsoleHandler.defaultConsole = ConsoleHandler.findCostabsConsole();
			IEditorPart editor = SourceUtils.obtainActiveEditor();

			List<TreeViewer> viewers = new ArrayList<TreeViewer>();
			viewers = SourceUtils.getContentOutlineViewers(SourceUtils.obtainActiveEditor());

			for (int i = 0; i < viewers.size(); i++) {
				System.out.println(viewers.get(i).getSelection().toString());
			}

			// Creating the costabs tmp directory
			File f = new File(CostabsConstants.TMP_DIR);
			f.mkdirs();

			// JCF// if (CostabsLink.ENTRIES_STRINGS.size() <= 0) {
			// JCF// DialogPrinter.printError(shellEclipse,
			// JCF// new Exception ("At least one function or method must be selected in the
			// outline view"),
			// JCF// "EasyInterface cannot analyze");
			// JCF// return null;
			// JCF// }

			if (Activator.getDefault().getPreferenceStore().getString("urlAddress").equals("")) {
				DialogPrinter.printMessage(shellEclipse,
						"Cannot load the server. Add the server in Window -> Preferences");
				return null;
			}

			OptionsDialog mDialog = new OptionsDialog(shellEclipse);
			mDialog.open();

			if (mDialog.getReturnCode() == OptionsDialog.CANCEL) {
				return null;
			}

			if (mDialog.getAnalysisSelected() == null) {
				DialogPrinter.printError(shellEclipse,
						new Exception("At least one analysis must be selected in the dialog"),
						"EasyInterface cannot analyze");
				return null;
			}

			// Global boolean to use a given output.xml instead of executing a command
			if (!CostabsConstants.DEBUG_OUTPUT) {
				CostabsXMLFrontend.cleanPrevResults();
				// callPrologBackend(absFile);

				// JCF// for(int i=0; i<CostabsLink.ENTRIES_STRINGS.size();i++){
				// JCF// System.out.println(CostabsLink.ENTRIES_STRINGS.get(i));
				// JCF// }
				// JCF// res = shell.analyze(absFile, CostabsLink.ENTRIES_STRINGS,
				// mDialog.getAnalysisSelected());
				// JCF-INI
				ArrayList<String> entrada = new ArrayList<String>();
				entrada.add("nada"); // OJO: esto era antes el metodo seleccionado.
				// outputZipProjectFile -> ficheroZip
				// absFile -> ficheroJava
				// String idApp = mDialog.getAnalysisSelected();
				res = shell.analyze(entrada, mDialog.getAnalysisSelected(), shellEclipse);
				// JCF-FIN
				System.out.println("res = " + res);

			}
			if (res != null)
				processOutput(res);

			editor.setFocus();

			OutputManager.getInstance().updateView(SourceUtils.getActiveFile());

		} catch (CostabsException e) {
			e.printStackTrace();
			DialogPrinter.printError(shellEclipse, e, "An error has ocurred during the analysis");

		} catch (Exception e) {
			e.printStackTrace();
			DialogPrinter.printError(shellEclipse, new Exception("Unkwon error: " + e.getMessage()),
					"An unkown error has ocurred during the analysis");
		}

		return null;
	}

	// JCF// private void callPrologBackend(String filename) throws Exception {
	// JCF// //System.out.println("filename = "+filename);
	// JCF// if (!filterUnreachable){
	// JCF// int numArgs = 3;
	// JCF// String[] args = new String[numArgs];
	// JCF// int i = 0;
	// JCF// args[i++] = "-d";
	// JCF// args[i++] = CostabsConstants.TMP_DIR;
	// JCF// args[i++] = filename;
	// JCF// PrologBackend.runFromShell(args);
	// JCF// } else {
	// JCF// Model model = CostabsLink.ABS_NATURE.getCompleteModel();
	// JCF//
	// PrologBackend.runFromPlugin(model,CostabsConstants.TMP_DIR,CostabsConstants.ABS_PL_FILE,CostabsLink.ENTRIES_NODES);
	// JCF// }
	// JCF// }

	private void processOutput(String res) throws Exception {

		IFile file = SourceUtils.getActiveFile();
		System.out.println("CONTENIDO DE ACTIVE FILE: " + file.toString());

		try {
			OutputTracker tracker = OutputManager.getInstance().getOutputTracker(file);

			if (tracker != null) {
				tracker.cleanAllInfo();
			}

			OutputManager.getInstance().loadResults(file, res);

			OutputTracker tracker2 = OutputManager.getInstance().getOutputTracker(file);

			tracker2.trackResults();
		} catch (Exception e) {
			throw e;
		}

	}

}
