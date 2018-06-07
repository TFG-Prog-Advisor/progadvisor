package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "flag")
public class Flag {	

	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "prefix")
	private String prefix;
	
	@XmlAttribute(name = "check")
	private Boolean check;
	
	@XmlAttribute(name = "widget")
	private String widget;
	
	@XmlElement(name = "desc", type = Desc.class)
	private Desc desc;
	
		
	@XmlElement(name = "defaultvalue")
	private Boolean defaultvalue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public String getWidget() {
		return widget;
	}

	public void setWidgetid(String widget) {
		this.widget = widget;
	}

	public Desc getDesc() {
		return desc;
	}

	public void setDesc(Desc desc) {
		this.desc = desc;
	}

	
	public Boolean getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(Boolean defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

}
