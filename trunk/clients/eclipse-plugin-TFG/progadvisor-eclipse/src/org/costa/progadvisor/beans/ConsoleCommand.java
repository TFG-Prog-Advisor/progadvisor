package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "printonconsole")
public class ConsoleCommand {
	
	@XmlAttribute(name = "consoleid")
	private String consoleid;
	
	@XmlAttribute(name = "consoletitle")
	private String consoletitle;
	
	@XmlAttribute(name = "outclass")
	private String outclass;
	
	@XmlElement(name = "content",  type = Content.class)
	private Content content;
	
	@XmlElement(name = "level")
	private String level;
	
	private String type;
	
	private String file;

	public String getConsoleid() {
		return consoleid;
	}

	public void setConsoleid(String consoleid) {
		this.consoleid = consoleid;	}

	

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

	public void setLines(Lines lss) {
		// TODO Auto-generated method stub
		
	}

	public String getConsoletitle() {
		return consoletitle;
	}

	public void setConsoletitle(String consoletitle) {
		this.consoletitle = consoletitle;
	}

	public String getOutclass() {
		return outclass;
	}

	public void setOutclass(String outclass) {
		this.outclass = outclass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	
	
	
	
	
}
