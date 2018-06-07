package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eiout")
public class Ei_out {
	
	@XmlAttribute(name = "version")
	private String version;
	
	@XmlElement(name = "eicommands")
	private Commands commands;
	
	@XmlElement(name = "eiactions")
	private Interactions interactions;
	
	public Commands getCommands() {
		return commands;
	}

	public void setCommands(Commands commands) {
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
