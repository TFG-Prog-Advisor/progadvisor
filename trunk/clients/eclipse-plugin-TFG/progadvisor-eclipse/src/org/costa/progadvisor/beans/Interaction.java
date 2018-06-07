package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "action")
public class Interaction {
	
	public static final String NO_CLEAN_INTERACTION_VALUE = "no";
	
	@XmlAttribute(name = "autoclean")
	private Boolean autoclean = true;
	
	@XmlAttribute		
	private String type; 
	

	
	@XmlElement(name = "oncodelineclick", type = CodelineAction.class)
	private CodelineAction codeline;

	
	@XmlElement(name = "onclickaction", type = OnclickAction.class)
	private OnclickAction onclick;


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


	public CodelineAction getCodeline() {
		return codeline;
	}


	public void setCodeline(CodelineAction codeline) {
		this.codeline = codeline;
	}


	public OnclickAction getOnclick() {
		return onclick;
	}


	public void setOnclick(OnclickAction onclick) {
		this.onclick = onclick;
	}


	public Object getNodes() {
		// TODO Auto-generated method stub
		return null;
	}


	public CostabsOutput getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
