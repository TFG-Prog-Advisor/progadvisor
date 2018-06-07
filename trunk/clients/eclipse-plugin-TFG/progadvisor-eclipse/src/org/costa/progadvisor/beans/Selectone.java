package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "selectone")
public class Selectone {
	
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
	
	@XmlElement(name = "option", type = Option.class)
	private List<Option> option;
	
	@XmlElement(name = "default", type = Default.class)
	private Default defaultvalue;		

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

	public void setWidget(String widget) {
		this.widget = widget;
	}

	public Desc getDesc() {
		return desc;
	}

	public void setDesc(Desc desc) {
		this.desc = desc;
	}

	public List<Option> getOption() {
		return option;
	}

	public void setOption(List<Option> option) {
		this.option = option;
	}

	public Default getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(Default defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

		
	
	
}
