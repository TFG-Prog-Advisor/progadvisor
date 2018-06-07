package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "changecss")
public class ChangecssCommand {
	
	@XmlElement(name = "cssproperty", type = Properties.class)
	private Properties cssproperty;
	
	@XmlElement(name = "elements", type = Elements.class)
	private Elements elements;

	
	

	public Properties getCssproperty() {
		return cssproperty;
	}

	public void setCsspropertiy(Properties cssproperty) {
		this.cssproperty = cssproperty;
	}

	public Elements getElements() {
		return elements;
	}

	public void setElements(Elements elements) {
		this.elements = elements;
	}

	
	
	


}
