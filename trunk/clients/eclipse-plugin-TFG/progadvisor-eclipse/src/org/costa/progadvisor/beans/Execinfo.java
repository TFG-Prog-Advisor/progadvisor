package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "execinfo")
public class Execinfo {
	
		
	@XmlElement(name = "cmdlineapp")
	private String cmdlineapp;
	
	@XmlElement(name = "serverapp", type = Serverapp.class)
	private Serverapp serverapp;

	
	public String getCmdlineapp() {
		return cmdlineapp;
	}

	public void setCmdlineapp(String cmdlineapp) {
		this.cmdlineapp = cmdlineapp;
	}

	public Serverapp getServerapp() {
		return serverapp;
	}

	public void setServerapp(Serverapp serverapp) {
		this.serverapp = serverapp;
	}
	
	

}
