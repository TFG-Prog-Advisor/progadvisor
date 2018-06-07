package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eicommands")
public class Commands {
	
	@XmlAttribute(name = "destination")
	private String destination;
	
	@XmlAttribute(name = "outclass")
	private String outclass;
	/*
	@XmlElement(name = "eicommand", type = Command2.class)
	private List<Command2> commands;

	public List<Command2> getCommands() {
		return commands;
	}

	public void setCommands(List<Command2> commands) {
		this.commands = commands;
	}
*/	
	@XmlElement(name="printonconsole", type=ConsoleCommand.class)
	private List<ConsoleCommand> consoleCommand;
	
	@XmlElement(name="highlightlines", type=HighlightCommand.class)
	private List<HighlightCommand> highlightline;
	
	@XmlElement(name="alertmsg", type=AlertmsgCommand.class)
	private List<AlertmsgCommand> alertmsg;

	@XmlElement(name="writefile", type=WritefileCommand.class)
	private List<WritefileCommand> writefile;
	
	@XmlElement(name="changecss", type=ChangecssCommand.class)
	private List<ChangecssCommand> changecss;
	
	@XmlElement(name="addmarker", type=AddmarkerCommand.class)
	private List<AddmarkerCommand> addmarker;
	
	@XmlElement(name="addinlinemarker", type=AddmarkerCommand.class)
	private List<AddmarkerCommand> addnlinemarker;
	
	@XmlElement(name="linewidget", type=LineWidgetCommand.class)
	private List<LineWidgetCommand> linewidget;
	
	@XmlElement(name="dialogbox", type=DialogboxCommand.class)
	private List<DialogboxCommand> dialogbox;
	
	@XmlElement(name="getproject", type=GetProjectCommand.class)
	private List<GetProjectCommand> getproject;

	public List<ConsoleCommand> getConsoleCommand() {
		return consoleCommand;
	}

	public void setConsoleCommand(List<ConsoleCommand> consoleCommand) {
		this.consoleCommand = consoleCommand;
	}

	public List<HighlightCommand> getHighlightline() {
		return highlightline;
	}

	public void setHighlightline(List<HighlightCommand> highlightline) {
		this.highlightline = highlightline;
	}

	public List<AlertmsgCommand> getAlertmsg() {
		return alertmsg;
	}

	public void setAlertmsg(List<AlertmsgCommand> alertmsg) {
		this.alertmsg = alertmsg;
	}

	public List<WritefileCommand> getWritefile() {
		return writefile;
	}
	
	public List<GetProjectCommand> getGetProject() {
		return getproject;
	}

	public void setWritefile(List<WritefileCommand> writefile) {
		this.writefile = writefile;
	}

	public List<ChangecssCommand> getChangecss() {
		return changecss;
	}

	public void setChangecss(List<ChangecssCommand> changecss) {
		this.changecss = changecss;
	}

	public List<AddmarkerCommand> getAddmarker() {
		return addmarker;
	}

	public void setAddmarker(List<AddmarkerCommand> addmarker) {
		this.addmarker = addmarker;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<LineWidgetCommand> getLinewidget() {
		return linewidget;
	}

	public void setLinewidget(List<LineWidgetCommand> linewidget) {
		this.linewidget = linewidget;
	}

	public String getOutclass() {
		return outclass;
	}

	public void setOutclass(String outclass) {
		this.outclass = outclass;
	}

	public List<DialogboxCommand> getDialogbox() {
		return dialogbox;
	}

	public void setDialogbox(List<DialogboxCommand> dialogbox) {
		this.dialogbox = dialogbox;
	}

	public List<AddmarkerCommand> getAddnlinemarker() {
		return addnlinemarker;
	}

	public void setAddnlinemarker(List<AddmarkerCommand> addnlinemarker) {
		this.addnlinemarker = addnlinemarker;
	}

	
	
}
