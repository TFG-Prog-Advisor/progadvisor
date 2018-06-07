package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "alertmsg")
public class AlertmsgCommand {
	
	@XmlAttribute(name = "outclass")
	private String outclass;
	
	@XmlAttribute(name = "boxtitle")
	private String boxtitle;
	
	@XmlAttribute(name = "boxwidth")
	private String boxwidth;
	
	@XmlAttribute(name = "boxheight")
	private String boxheight;

	@XmlElement(name = "content",  type = Content.class)
	private Content content;
	
	@XmlElement(name = "level")
	private String level;

	

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOutclass() {
		return outclass;
	}

	public void setOutclass(String outclass) {
		this.outclass = outclass;
	}

	public String getBoxtitle() {
		return boxtitle;
	}

	public void setBoxtitle(String boxtitle) {
		this.boxtitle = boxtitle;
	}

	public String getBoxwidth() {
		return boxwidth;
	}

	public void setBoxwidth(String boxwidth) {
		this.boxwidth = boxwidth;
	}

	public String getBoxheight() {
		return boxheight;
	}

	public void setBoxheight(String boxheight) {
		this.boxheight = boxheight;
	}
	
	
}
