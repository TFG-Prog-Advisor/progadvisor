package org.costa.progadvisor.trackers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;

import org.costa.progadvisor.beans.AlertmsgCommand;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.Content;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.GetProjectCommand;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.structures.CostabsConstants;
import org.costa.progadvisor.utils.Unzip;
import org.costa.progadvisor.utils.ZipUtils;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;



public class GetProjectTracker extends CommandTracker{

	private String path;
	private String zipfile; 
	
	public GetProjectTracker(GetProjectCommand command) {
		super(command);
		path = command.getPath();
		zipfile = command.getTexto();
	}
	

	@Override
	public void track() {	
       
		try {
		    
			byte [] res = Base64.getMimeDecoder().decode(this.zipfile.getBytes());
			
			String rutaZipTemporal = this.path+System.getProperty("file.separator")+"temp.zip";
			
			FileOutputStream f2 = new FileOutputStream(rutaZipTemporal);
			f2.write(res);
			f2.close();

			Unzip unzip = new Unzip(rutaZipTemporal, this.path);
			
			//BORRAR EL ZIP
			File f = new File(rutaZipTemporal);		
			f.delete();
			
			File file = new File(this.path);
			String[] directories = file.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
			
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject[] proyectosCreados = root.getProjects();
			ArrayList<String> listaProyectos = new ArrayList<String>();
			
			for (int i = 0; i < proyectosCreados.length; i++) {
				listaProyectos.add(proyectosCreados[i].getName());
			}
			
			
			for(int i = 0; i < directories.length; i++) {
				String nombreProyAux = directories[i];
				int j = 2;
				
				while(listaProyectos.contains(nombreProyAux)) {
					nombreProyAux = directories[i]+"_"+j;
					j++;
				}
				
				IProject project = root.getProject(nombreProyAux);
				IWorkspace w = ResourcesPlugin.getWorkspace();
					
				IProjectDescription desc=w.newProjectDescription(project.getName()); 
				IPath path1=new Path(this.path+System.getProperty("file.separator")+directories[i]);
			    desc.setLocation(path1);
			    project.create(desc, progressMonitor); 
			    project.open(progressMonitor);
			}
			
		}
		catch (Exception e) {
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
