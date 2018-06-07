package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "content")
public class Content {	
	
	
	@XmlValue
	private String text;	
	
	

	@XmlAttribute(name = "format")
	private String textFormat;	
	

	public String getTextFormat() {
		
					return textFormat;
		
		
	}

	public void setTextFormat(String textFormat) {
		this.textFormat = textFormat;
	}

	public String getText() {
		return text;
	}

	
	public void setText(String text) {
		this.text = text;
	}

	
	

	

}
