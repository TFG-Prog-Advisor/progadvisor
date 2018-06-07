package org.costa.progadvisor.structures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.swing.JPanel;

import org.costa.progadvisor.beans.CodelineAction;
import org.costa.progadvisor.beans.Command;
import org.costa.progadvisor.beans.Command_old;
import org.costa.progadvisor.beans.ConsoleCommand;
import org.costa.progadvisor.beans.CostabsOutput;
import org.costa.progadvisor.beans.DialogboxCommand;
import org.costa.progadvisor.beans.Ei_out;
import org.costa.progadvisor.beans.Ei_response;
import org.costa.progadvisor.beans.HighlightCommand;
import org.costa.progadvisor.beans.Interaction;
import org.costa.progadvisor.beans.Node;
import org.costa.progadvisor.beans.OnclickAction;
import org.costa.progadvisor.beans.Selector;
import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.trackers.CommandFactory;
import org.costa.progadvisor.trackers.CommandTracker;
import org.costa.progadvisor.trackers.OutputManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.Title;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.app.beans.SVGIcon;



public class CostabsHTMLGraph  {

	

	private ConsoleCommand command;
	private DialogboxCommand command2;

	
	private IFile file = null;

	public void loadHTML (IFile file, ConsoleCommand command) throws CostabsException {
		this.command = command;
		this.file = file;
	
		try {
			// Arghh!! It is needed to create a temporary file to to read all graphs from different files. 
			// Work-around to fix the problem of SVGSalamander while loading multiple graphs
			// using the same file name.
			
			//System.out.println("loadGraph html" + this.command.getContent().getText());
			
			
			
			/*File tmpfile = copy(this.command.getContent().getText());			
			System.out.println("file://" + tmpfile.getAbsolutePath());*/
			
						
			OutputManager.getInstance().setHtmlxml(this.command.getContent().getText());
			
			return;
			
		} catch (Exception  e) {
			throw new CostabsException("Graph file cannot be found " + command.getFile());
		}
	}
	

	

	/**
	 * Creates a temporary file with the content of the file <code>fo</code> 
	 * @param fo The original file name
	 * @return returns a temporary file with the content of <code>fo</code>
	 * @throws CostabsException If something goes wrong
	 */
	public static File copy (String fo) throws CostabsException {

		File f1 = new File(fo);
		File f2;
		try {
			f2 = File.createTempFile("pt_graph_fo_html", null, new File(CostabsConstants.TMP_DIR_SACO));
			InputStream in = new FileInputStream(f1);
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0){
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			return f2;
		} catch (IOException e) {
			throw new CostabsException("Cannot copy the file " + fo + " to a temporary directory");
		}
	}
	
	@Override
	public String toString() {
		return "SVG Graph: " + file.getFullPath().toOSString();
	}




	public void loadHTML(IFile file2, DialogboxCommand command2) throws CostabsException {
		this.command2 = command2;
		this.file = file;
	
		try {
			// Arghh!! It is needed to create a temporary file to to read all graphs from different files. 
			// Work-around to fix the problem of SVGSalamander while loading multiple graphs
			// using the same file name.
			
			//System.out.println("loadGraph html" + this.command2.getContent().getText());		
			
			
			/*File tmpfile = copy(this.command.getContent().getText());
			
			System.out.println("file://" + tmpfile.getAbsolutePath());*/
			
						
			OutputManager.getInstance().setHtmlxml(this.command2.getContent().getText());
			
			return;
			
		} catch (Exception  e) {
			throw new CostabsException("Graph file cannot be found " + command2.getFile());
		}
		
	}

}
