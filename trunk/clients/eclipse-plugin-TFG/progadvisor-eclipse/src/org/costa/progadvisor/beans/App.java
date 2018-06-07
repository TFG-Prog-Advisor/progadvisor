package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "app")
public class App {
	
	@XmlAttribute(name = "id")
	private String appId;
	
	@XmlAttribute(name = "src")
	private String appSrc;
	
	@XmlAttribute(name = "visible")
	private Boolean appVisible;
	
	@XmlElement(name = "appinfo", type = Appinfo.class)
	private Appinfo appinfo;
	
	@XmlElement(name = "apphelp", type = Apphelp.class)
	private Apphelp apphelp;	
	
	@XmlElement(name = "execinfo", type = Execinfo.class)
	private Execinfo execinfo;
	
	@XmlElement(name = "parameters", type = Parameters.class)
	private Parameters parameters;

	@XmlElement(name = "permission", type = Permission.class)
	private Permission permission;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSrc() {
		return appSrc;
	}

	public void setAppSrc(String appSrc) {
		this.appSrc = appSrc;
	}
	
	public boolean isAppVisible() {
		return appVisible;
	}

	public void setAppVisible(boolean appVisible) {
		this.appVisible = appVisible;
	}

	public Appinfo getAppinfo() {
		return appinfo;
	}

	public void setAppinfo(Appinfo appinfo) {
		this.appinfo = appinfo;
	}

	public Apphelp getApphelp() {
		return apphelp;
	}

	public void setApphelp(Apphelp apphelp) {
		this.apphelp = apphelp;
	}

	public Execinfo getExecinfo() {
		return execinfo;
	}

	public void setExecinfo(Execinfo execinfo) {
		this.execinfo = execinfo;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Boolean getAppVisible() {
		return appVisible;
	}

	public void setAppVisible(Boolean appVisible) {
		this.appVisible = appVisible;
	}
	

	@Override
	public String toString() {
		return CostabsXMLFrontend.toString(this);
	}
	
}
