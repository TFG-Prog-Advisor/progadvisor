package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "appexec")
public class Exec {
		
	@XmlElement(name = "result", type = Result.class)
	private Result result;
	
	@XmlElement(name = "cmd", type = Cmd.class)
	private Cmd cmd;
	
	@XmlElement(name = "pprefix", type = Pprefix.class)
	private Pprefix pprefix;
	
	@XmlElement(name = "entries", type = Entries.class)
	private Entries entries;
	
	@XmlElement(name = "files", type = Files.class)
	private Files files;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Cmd getCmd() {
		return cmd;
	}

	public void setCmd(Cmd cmd) {
		this.cmd = cmd;
	}

	public Pprefix getPprefix() {
		return pprefix;
	}

	public void setPprefix(Pprefix pprefix) {
		this.pprefix = pprefix;
	}

	public Entries getEntries() {
		return entries;
	}

	public void setEntries(Entries entries) {
		this.entries = entries;
	}

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}
	
	

}
