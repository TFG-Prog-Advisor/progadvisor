package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "onclick")
public class OnclickAction {
	
	@XmlAttribute(name = "autoclean")
	private String autoclean;
	
	@XmlElement(name = "elements", type = Elements.class)
	private Elements elements;
	
	@XmlElement(name = "eicommands", type = Commands.class)
	private Commands commands;
	
	@XmlElement(name = "level")
	private String level;	
	
	private String type = "graph";
	
	public Elements getElements() {
		return elements;
	}

	public void setElements(Elements elements) {
		this.elements = elements;
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

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}		
	

}
