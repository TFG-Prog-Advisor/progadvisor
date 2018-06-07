package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "highlightlines")
public class HighlightCommand {
	
	@XmlAttribute(name = "destination")
	private String destination;
	
	@XmlAttribute(name = "outclass")
	private String outclass;
	
	
	@XmlElement(name = "lines", type = Lines.class)
	private Lines lines;
	
	@XmlElement(name = "content",  type = Content.class)
	private Content content;
	
	@XmlElement(name = "level")
	private String level;
	
	@XmlElement(name = "color")
	private String color;
	
	private String type = "highlight";

	public Lines getLines() {
		return lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

	public String getOutclass() {
		return outclass;
	}

	public void setOutclass(String outclass) {
		this.outclass = outclass;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	

}
