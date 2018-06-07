package org.costa.progadvisor.trackers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.costa.progadvisor.beans.AddmarkerCommand;
import org.costa.progadvisor.beans.AlertmsgCommand;
import org.costa.progadvisor.beans.ChangecssCommand;
import org.costa.progadvisor.beans.Command;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.Ei_response;
import org.costa.progadvisor.beans.GetProjectCommand;
import org.costa.progadvisor.beans.HighlightCommand;
import org.costa.progadvisor.beans.LineWidgetCommand;
import org.costa.progadvisor.beans.Lines;
import org.costa.progadvisor.beans.WritefileCommand;
import org.costa.progadvisor.exceptions.CostabsException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;


public abstract class CommandTracker extends Command{
	
	private int [] linesInt; 
	
	private IFile iFile;
	
	private String path;
	
	private String filename;
	
	private Ei_response output;

	private static String text;
	
	public CommandTracker(ConsoleCommand command) {		
		//this.setFile(command.getFile());		
		this.setLevel(command.getLevel());
		this.setContent(command.getContent());		
		this.setType(command.getType());
		this.setConsoleid(command.getConsoleid());		
	}
	
	

	public CommandTracker(HighlightCommand command) {		
		this.setColor(command.getColor());
		//this.setFile(command.getFile());
		this.setLevel(command.getOutclass());
		this.setContent(command.getContent());
		this.setLines(command.getLines());	
		this.setType(command.getType());
		
		fillLines((Lines) command.getLines());	
	}
	
	public CommandTracker(AlertmsgCommand command) {		
		//this.setFile(command.getFile());	
		this.setLevel(command.getLevel());
		this.setContent(command.getContent());		
	}
	
	public CommandTracker(WritefileCommand command) {		
		//this.setFile(command.getFile());	
		this.setFilename(command.getFilename());
		//this.setContent(command.getContent());	
		this.setPath(command.getPath());
		this.text = command.getTexto();
		
	}
	
	public CommandTracker(ChangecssCommand command) {		
		this.setProperties(command.getCssproperty());
		this.setElements(command.getElements());	
	}
	
	public CommandTracker(AddmarkerCommand command) {	
		//this.setFile(command.getFile());		
		this.setContent(command.getContent());
		this.setLevel(command.getOutclass());
		this.setLines(command.getLines());
			
		fillLines(command.getLines());	
	}
	
	public CommandTracker(LineWidgetCommand command) {		
		//this.setFile(command.getFile());		
		this.setContent(command.getContent());
		this.setLevel(command.getLevel());
		this.setLines(command.getLines());
		
		fillLines(command.getLines());	
	}
	
	public CommandTracker(DialogboxCommand command) {		
		//this.setFile(command.getFile());		
		this.setContent(command.getContent());
		this.setLevel(command.getOutclass());
		
	}
	
	public CommandTracker(Command command) {
		// TODO Auto-generated constructor stub
	}

	public CommandTracker(GetProjectCommand command) {
		this.setPath(command.getPath());
		this.setText(command.getTexto());
	}



	public int[] getLinesInt() {
		return linesInt;
	}

	public IFile getiFile() {
		return iFile;
	}

	public void setiFile(IFile iFile) {
		this.iFile = iFile;
	}
	
	public void setText(String texto) {
		this.text = texto;
	}
	
	public Ei_response getOutput() {
		return output;
	}

	public void setOutput(Ei_response output) {
		this.output = output;
	}

	private void fillLines (Lines lines) {
		if (lines == null) {
			return;
		} 
		
		linesInt = new int [lines.getLines().size()];
		for(int i = 0; i < lines.getLines().size(); i++) {
			if(lines.getLines().get(i).getTo() != null){
				int linesTotal = Integer.parseInt(lines.getLines().get(i).getTo()) - Integer.parseInt(lines.getLines().get(i).getFrom()) + 1;
			
				//System.out.println("linesTotal = " + linesTotal);
				linesInt = new int [linesTotal];
				for(int j = 0; j < linesTotal; j++) {
				
					linesInt[j] = Integer.parseInt(lines.getLines().get(i).getFrom()) + j;
				
					//System.out.println("linesInt[j]= " + linesInt[j]);
				}
			}
			else{	
				//linesInt = new int [lines.getLines().size()];
				linesInt[i] = Integer.parseInt(lines.getLines().get(i).getFrom());
				
				//System.out.println("linesInt[i]= " + linesInt[i]);
			}
		}
		
	}

	public abstract void track () throws CostabsException ;

	public abstract void clean ();

	public String getText() {
		return text;
	}


	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}


	public static IFile createFile(String fullPath) throws CoreException {
		String fullContents = text;
	  
	    byte[] bytes = fullContents.getBytes();
        InputStream source = new ByteArrayInputStream(bytes);
	    
	    IWorkspace workspace = ResourcesPlugin.getWorkspace();
	    IWorkspaceRoot root = workspace.getRoot();
	    IProject project  = root.getProject("MyProject");
	    IFolder folder = project.getFolder("Folder1");
	    IFile file = folder.getFile(fullPath);
	    //at this point, no resources have been created
	    if (!project.exists()) project.create(null);
	    if (!project.isOpen()) project.open(null);
	    if (!folder.exists()) 
	        folder.create(IResource.NONE, true, null);
	    if (!file.exists()) {	       
	        file.create(source, IResource.NONE, null);
	    }
	    else{
	    	file.setContents(source, IResource.FORCE, null);
	    }
	    
	  
	    return file;
	}	

	
}
