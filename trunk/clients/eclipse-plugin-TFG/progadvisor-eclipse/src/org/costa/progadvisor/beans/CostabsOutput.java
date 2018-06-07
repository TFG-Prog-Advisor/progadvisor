package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "output")
public class CostabsOutput {
	
	@XmlElement(name = "eicommands")
	private Commands_old commands;
	
	@XmlElement
	private Interactions interactions;
	
	public Commands_old getCommands() {
		return commands;
	}

	public void setCommands(Commands_old commands) {
		this.commands = commands;
	}
	
	public Interactions getInteractions() {
		return interactions;
	}

	public void setInteractions(Interactions interactions) {
		this.interactions = interactions;
	}
	
	@Override
	public String toString() {
		return CostabsXMLFrontend.toString(this);
	}
}
