package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "oncodelineclick")
public class CodelineAction {
	
	@XmlAttribute(name = "destination")
	private String destination;
	
	@XmlAttribute(name = "autoclean")
	private String autoclean;
	
	@XmlElement(name = "lines", type = Lines.class)
	private Lines lines;
	
	@XmlElement(name = "eicommands", type = Commands.class)
	private Commands commands;
	
	@XmlElement(name = "level")
	private String level;

	private Nodes nodes;

	private String type = "source";	
	
	

	public Lines getLines() {
		return lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAutoclean() {
		return autoclean;
	}

	public void setAutoclean(String autoclean) {
		this.autoclean = autoclean;
	}

	public Commands getCommands() {
		return commands;
	}

	public void setCommands(Commands commands) {
		this.commands = commands;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Nodes getNodes() {		
		// TODO Auto-generated method stub
		return nodes;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	public void setType(String type) {
		this.type = type;
	}		
	

}
