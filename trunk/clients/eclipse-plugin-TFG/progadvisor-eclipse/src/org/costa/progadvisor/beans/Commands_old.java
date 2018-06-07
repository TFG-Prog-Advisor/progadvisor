package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eicommands")
public class Commands_old {
	
	@XmlElement(name = "eicommand", type = Command_old.class)
	private List<Command_old> commands;

	public List<Command_old> getCommands() {
		return commands;
	}

	public void setCommands(List<Command_old> commands) {
		this.commands = commands;
	}
}
