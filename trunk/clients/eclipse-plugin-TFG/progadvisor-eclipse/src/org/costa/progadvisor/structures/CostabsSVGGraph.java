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



public class CostabsSVGGraph extends CostabsGraph {

	private SVGIcon icon;

	private SVGDiagram diagram;

	private HashMap<String,String> idsTable;

	private ConsoleCommand command;

	private TreeSet<String> lastClicked = new TreeSet<String>();

	private IFile file = null;

	public void loadGraph (IFile file, ConsoleCommand command) throws CostabsException {
		this.command = command;
		this.file = file;
		//		FileReader reader;
		icon = new SVGIcon();
		icon.setScaleToFit(false);
		try {
			// Arghh!! It is needed to create a temporary file to to read all graphs from different files. 
			// Work-around to fix the problem of SVGSalamander while loading multiple graphs
			// using the same file name.
			
			//System.out.println("loadGraph " + this.command.getContent().getText());
			
			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(this.command.getContent().getText().getBytes());
			
			//File tmpfile = copy(this.command.getContent().getText());
			
			//System.out.println("file://" + tmpfile.getAbsolutePath());
			
			SVGUniverse universe = new SVGUniverse();
			//URI uri = universe.loadSVG(new URL("file://" + tmpfile.getAbsolutePath()));
			
			URI uri = universe.loadSVG(is, "file://" +CostabsConstants.TMP_DIR_SACO+"tmpGraph");
			
			//System.out.println("uri" + uri.toString());			
			
			
			diagram = universe.getDiagram(uri);			
			
			
			fixDiagramSize();			
			
			
			icon.setSvgURI(uri);			
			
			
			idsTable = new HashMap<String, String> ();
			
			
			fillIdsTable(diagram.getRoot());
		} catch (Exception  e) {
			throw new CostabsException("Graph file cannot be found " + command.getFile());
		}
	}
	
	private void fixDiagramSize () {
		if (diagram.getRoot().getPresAbsolute("width") != null) {
			String width = diagram.getRoot().getPresAbsolute("width").getStringValue();
			if (width != null && width.contains("pt")) {
				width = width.replace("pt", "");
			}
			diagram.getRoot().getPresAbsolute("width").setStringValue(width);
		}
		
		if (diagram.getRoot().getPresAbsolute("height") != null) {
			String height = diagram.getRoot().getPresAbsolute("height").getStringValue();
			if (height != null && height.contains("pt")) {
				height = height.replace("pt", "");
			}
			diagram.getRoot().getPresAbsolute("height").setStringValue(height);
		}
		try {
			diagram.getRoot().build();
		} catch (SVGException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getWidth () {
		if (diagram != null) {
			return (int)diagram.getWidth();
		}
		return 0;
	}

	public int getHeight () {
		if (diagram != null) {
			return (int)diagram.getHeight();
		}
		return 0;
	}

//	public void paint(Graphics g, JPanel panel) {
//		if (icon != null) {
////			g.setColor(panel.getBackground());
////			g.fillRect(0, 0, getWidth(), getHeight());
//			icon.paintIcon(panel, g, 0, 0);
//		}
//		//panel.setPreferredSize(new Dimension(getWidth(),getHeight()));
//	}
	
	
	public void paint(Graphics gg, JPanel panel) {

		Graphics2D g = (Graphics2D)gg;

		if (panel.getBackground() != null)
		{
			Dimension dim = panel.getSize();
			g.setColor(panel.getBackground());
			g.fillRect(0, 0, dim.width, dim.height);
		}
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		if (diagram != null) 
		{
			try
			{
				diagram.render(g);
			}
			catch (SVGException e)
			{
				e.printStackTrace();
			}
		}	
		panel.setPreferredSize(new Dimension(getWidth(),getHeight()));
	}


	private void fillIdsTable (SVGElement element) {


		for (int i=0; i < element.getNumChildren(); i ++) {
			try {
				if (element.getChild(i) instanceof com.kitfox.svg.Title) {
					if (!element.hasAttribute("class", AnimationElement.AT_XML) || 
							"node".equals(element.getPresAbsolute("class").getStringValue()) ||
							"edge".equals(element.getPresAbsolute("class").getStringValue())){
						Title title = (Title)element.getChild(i);
						idsTable.put(title.getText(),element.getId());						
						
					}
					//ids.add(title.getText());
				}

				SVGElement child = element.getChild(i);
				fillIdsTable(child);
			} catch (SVGElementException e) {
				e.printStackTrace();
			}
		}
	}

	public void cleanGraph () {
		cleanMarked();
		diagram = null;
		icon = null;
	}

	public void handleClick (int x, int y) throws CostabsException {
		List pickedElements;	
		try {
			pickedElements = diagram.pick(new Point(x,y),null);

			TreeSet<String> ids = new TreeSet<String>();

			// Clean markers...
			cleanMarked();
			// InteractionsTracker.cleanInterMarkers(OutputManager.getInstance().getFile());
			OutputManager.getInstance().getOutputTracker(file).cleanInteractions();

			// Getting elements clicked
			for (int i = 0; i < pickedElements.size(); i++) {
				ArrayList<SVGElement> elements = (ArrayList<SVGElement>)pickedElements.get(i);
				for (SVGElement element: elements) {
					if (element.getId() != null) {
						ids.add(element.getId());
						System.out.println("element.getId()= "+element.getId());
					}
				}
			}

			// Mark nodes in the graph
			TreeSet<String> allNodes = new TreeSet<String>();
			for(String id: ids) {
				TreeSet<String> nodes = getNodesToMark (id);
				markNode(id, CostabsGraph.COLOR_SELECTED);
				for (String node: nodes) {
					markNode(node, CostabsGraph.COLOR_MARKED);
				}
				nodes.add(id);
				allNodes.addAll(nodes);

			}
			lastClicked = allNodes;

			// Other commands like highlight's or markers
			
for(String id: ids) {
				
				//System.out.println("id for = " + id);
}

			for(String id: ids) {
				
				//System.out.println("id " + id);
				
				List<Object> commands = getCommandsToApply(id);
				for(Object command: commands) {
					//System.out.println("command.getClass().getName() 1 ="+command.getClass().getName());
					
					if(command.getClass().getName().equals("costabs.beans.DialogboxCommand")){
						//System.out.println("command.getContent() " + ((DialogboxCommand) command).getContent());
					
						if (((DialogboxCommand) command).getOutclass()=="info") {
							continue;
						}
					
					
						CommandTracker tracker = CommandFactory.getInterTracker((DialogboxCommand) command);
						tracker.setiFile(file);
						tracker.track();
					}
					
					if(command.getClass().getName().equals("costabs.beans.HighlightCommand")){						
						//System.out.println("command.getContent() " + ((HighlightCommand) command).getLines().getLines().size());
					
						if (((HighlightCommand) command).getOutclass()=="info") {
							continue;
						}
					
					
						CommandTracker tracker = CommandFactory.getInterTracker((HighlightCommand) command);
						tracker.setiFile(file);
						tracker.track();
					}
				}			
			}
		} catch (SVGException e) {
			throw new CostabsException("SVG file cannot be handled properly: " + e.getMessage(), e);
		}
	}

	public void cleanMarked () {
		for(String node: lastClicked) {
			try {
				markNode(node, CostabsGraph.COLOR_NO_SELECTED);
			} catch (SVGElementException e) {
				ConsoleHandler.write("Marked node " + node + " cannot be cleaned");
			}
			lastClicked = new TreeSet<String>();
		}
	}


	public void interMarkNode (List<Node> nodes, String color) throws CostabsException {

		for(Node n: nodes) {
			lastClicked.add(getSVGId(n.getId()));
			try {
				markNode(getSVGId(n.getId()), color);
			} catch (SVGElementException e) {
				e.printStackTrace();
			}
		}
	}

	private void markNode (String id, String color) throws SVGElementException {
		SVGElement element = diagram.getElement(id);
		if (element == null) {
			return;
		}
		if (!element.hasAttribute("class", AnimationElement.AT_XML) || 
			!"node".equals(element.getPresAbsolute("class").getStringValue())){
			return;
		}
		markNodeAux(element, color);
	}

	private void markNodeAux (SVGElement element, String color) throws SVGElementException {
		for(int i=0; i < element.getNumChildren(); i ++) {
			SVGElement child = element.getChild(i);
			if (child.hasAttribute("fill", AnimationElement.AT_XML)) {
				child.setAttribute("fill", AnimationElement.AT_XML, color);
			}
			markNodeAux(child, color);
		}
	}


	private TreeSet<String> getNodesToMark (String id) throws SVGException {
		TreeSet<String> nodes = new TreeSet<String>();

		Ei_response output = OutputManager.getInstance().getOutputTracker(file).getOutput();

		SVGElement element = diagram.getElement(id);

		if (!element.hasAttribute("class", AnimationElement.AT_XML) || 
			(!"node".equals(element.getPresAbsolute("class").getStringValue()) && 
			 !"edge".equals(element.getPresAbsolute("class").getStringValue()))){
			return nodes;
		}
		
		if(output.getEi_app_output().getEiout()==null){
			return nodes;
		}

		List<OnclickAction> interactions = output.getEi_app_output().getEiout().getInteractions().getOnclick();

		Node n = new Node();
		String idxml = getCostaId(id);
		n.setId(idxml);
		
		
		
		//System.out.println("idxml = "+idxml);

		if (interactions == null || idxml == null) {
			return nodes;
		}
		for (OnclickAction interaction: interactions) {

			/*if (CommandFactory2.TYPE_GRAPH.equals(interaction.getType()) && 
					interaction.getElements() != null && 
					(interaction.getElements().contains(n))) {*/
			if (CommandFactory.TYPE_GRAPH.equals(interaction.getType()) && 
					interaction.getElements() != null && (interaction.getElements().getSelector().get(0).getValue().indexOf(idxml) != -1)){
				//System.out.println("indexOf = "+interaction.getElements().getSelector().get(0).getValue());
				if(interaction.getCommands().getDialogbox()!=null){				
					for(DialogboxCommand command: interaction.getCommands().getDialogbox()) {
						if (CommandFactory.MARK_NODE.equals(command.getType())) {
							
							for(Selector selector: interaction.getElements().getSelector()) {
								//System.out.println("selector.getValue() = "+selector.getValue());
								String nodoSelector = "";
							
								nodoSelector = selector.getValue().split("\"")[1];								

								//System.out.println("node.getId() = "+ nodoSelector);
								//System.out.println("getSVGId(nodoSelector)="+getSVGId(nodoSelector));
								
								nodes.add(getSVGId(nodoSelector));

								//nodes.add(nodoSelector);
							}



						}
					}
				}
				if(interaction.getCommands().getHighlightline()!=null){		
					for(HighlightCommand command: interaction.getCommands().getHighlightline()) {
						if (CommandFactory.HIGHLIGHT.equals(command.getType())) {
							
							for(Selector selector: interaction.getElements().getSelector()) {
								//System.out.println("selector.getValue() = "+selector.getValue());
								String nodoSelector = "";								
								nodoSelector = selector.getValue().split("\"")[1];								

								//System.out.println("node.getId() = "+ nodoSelector);

								nodes.add(getSVGId(nodoSelector));


								//nodes.add(nodoSelector);
							}



						}
					}

				}
				
			}
		}

		return nodes; 

	}

	private ArrayList<Object> getCommandsToApply (String id) {
		
		ArrayList<Object> commands = new ArrayList<Object>();
		

		Ei_response output = OutputManager.getInstance().getOutputTracker(file).getOutput();

		List<OnclickAction> interactions = output.getEi_app_output().getEiout().getInteractions().getOnclick();
		Node n = new Node();
		n.setId(getCostaId(id));		


		if(interactions == null) {
			return commands;
		}
		/*for (Interaction2 interaction: interactions) {
			if (CommandFactory2.TYPE_GRAPH.equals(interaction.getType()) && 
					interaction.getNodes() != null &&
					((Command2) interaction.getNodes()).getNodes().contains(n)) {
				commands.addAll((Collection<? extends Command>) interaction.getCommands().getCommands());
			}	
		}*/
		
		for (OnclickAction interaction: interactions) {
			
			//System.out.println("interaction.getType()  " + interaction.getType());	
			
			//System.out.println("interaction.getElements().getSelector().size() ="+interaction.getElements().getSelector().size());
			//System.out.println("interaction.getElements().getSelector() ="+interaction.getElements().getSelector().get(0).getValue());
			
			/*if (CommandFactory2.TYPE_GRAPH.equals(interaction.getType()) && 
					interaction.getElements() != null &&
					(interaction.getElements()).contains(n)) {
				//commands.addAll((Collection<? extends ConsoleCommand>) interaction.getCommands());
				System.out.println("ok");
				commands.addAll(interaction.getCommands().getDialogbox());
			}	*/
			//System.out.println("getCostaId(id) = "+getCostaId(id));
			
			if (CommandFactory.TYPE_GRAPH.equals(interaction.getType()) && 
					interaction.getElements() != null && getCostaId(id) != null && (interaction.getElements().getSelector().get(0).getValue().indexOf(getCostaId(id)) != -1)){
				//System.out.println("indexOf = "+interaction.getElements().getSelector().get(0).getValue());
				if(interaction.getCommands().getDialogbox()!=null){				
					//System.out.println("ok getDialogbox");
					commands.addAll(interaction.getCommands().getDialogbox());
					}
				
				if(interaction.getCommands().getHighlightline()!=null){		
					//System.out.println("ok getHighlightline");
					commands.addAll(interaction.getCommands().getHighlightline());
					}

				}
				
			
		}

		return commands; 

	}


	public String getSVGId (String title){
		return idsTable.get(title);
	}

	private String getCostaId (String svgid){
		//System.out.println("svgid= "+svgid);
		for(Entry<String, String> entry: idsTable.entrySet()) {
			//System.out.println("entry.getValue()= "+entry.getValue());
			if (svgid.equals(entry.getValue())) {
				//System.out.println("entry.getValue()= "+entry.getValue());
				return entry.getKey();
			}
		}
		return null;
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
			f2 = File.createTempFile("pt_graph_fo", null, new File(CostabsConstants.TMP_DIR_SACO));
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

	

}
