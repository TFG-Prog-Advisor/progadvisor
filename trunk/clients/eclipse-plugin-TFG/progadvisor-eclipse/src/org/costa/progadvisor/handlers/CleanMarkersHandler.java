package org.costa.progadvisor.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;




/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CleanMarkersHandler extends AbstractHandler {

	/**
	 * the command has been executed, so extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		final Shell shellEclipse= HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
		//		try {
		//			
		//			/*
		//
		//			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		//			jfc.setDialogTitle("Choose a directory to save your file: ");
		//			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//
		//			int returnValue = jfc.showSaveDialog(null);
		//			if (returnValue == JFileChooser.APPROVE_OPTION) {
		//				if (jfc.getSelectedFile().isDirectory()) {
		//					System.out.println("You selected the directory: " + jfc.getSelectedFile());
		//				}
		//			}*/
		//			
		//			JFileChooser fileChooser = new JFileChooser();
		//		    fileChooser.showOpenDialog(null);
		//			
		//			String zippedProjectPath = "/Users/aykaz/Desktop/Hola.zip";
		//			String destinyPath = "/Users/aykaz/Desktop";
		//			Unzip unzip = new Unzip(zippedProjectPath, destinyPath);
		//			
		//			OutputManager.getInstance().cleanAll(SourceUtils.getActiveFile());
		//		} catch (CostabsException e) {
		//			DialogPrinter.printError(shellEclipse, e, e.getMessage());
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}

		try {			
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();


			IProject project = root.getProject("Prueba");
			    IWorkspace w = ResourcesPlugin.getWorkspace();
			    IProjectDescription desc=w.newProjectDescription(project.getName()); 
			    //String projectLocation="";
			    IPath path1=new Path("/Users/aykaz/Desktop/MiProyecto");
			    desc.setLocation(path1); 
			    project.create(desc, progressMonitor); 
			    project.open(progressMonitor);
		}
		catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}

}
