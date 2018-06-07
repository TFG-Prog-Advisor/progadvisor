package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eiactions")
public class Interactions {
	
	public static final String NO_CLEAN_INTERACTION_VALUE = "no";
	
	@XmlAttribute(name = "destination")
	private String destination;
	
	@XmlAttribute(name = "autoclean")
	private Boolean autoclean;

	
	
	@XmlAttribute		
	private String type; 

	
	@XmlElement(name = "oncodelineclick", type = CodelineAction.class)
	private List<CodelineAction> codeline;

	
	@XmlElement(name = "onclick", type = OnclickAction.class)
	private List<OnclickAction> onclick;

	
	

	public Boolean getAutoclean() {
		return autoclean;
	}

	public void setAutoclean(Boolean autoclean) {
		this.autoclean = autoclean;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<CodelineAction> getCodeline() {
		return codeline;
	}

	public void setCodeline(List<CodelineAction> codeline) {
		this.codeline = codeline;
	}

	public List<OnclickAction> getOnclick() {
		return onclick;
	}

	public void setOnclick(List<OnclickAction> onclick) {
		this.onclick = onclick;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
}
