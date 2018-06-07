package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "line")
public class Line {

	@XmlAttribute
	private String from;
	
	@XmlAttribute
	private String to;
	
	@XmlAttribute
	private String fromch;
	
	@XmlAttribute
	private String toch;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFromch() {
		return fromch;
	}

	public void setFromch(String fromch) {
		this.fromch = fromch;
	}

	public String getToch() {
		return toch;
	}

	public void setToch(String toch) {
		this.toch = toch;
	}

	
	
}
