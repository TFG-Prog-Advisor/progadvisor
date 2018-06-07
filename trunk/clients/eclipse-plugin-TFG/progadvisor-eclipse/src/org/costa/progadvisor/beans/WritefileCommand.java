package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@XmlRootElement(name = "writefile")
@XmlAccessorType(XmlAccessType.FIELD)
public class WritefileCommand {
	
	@XmlAttribute(name = "path")
	private String path;
	
	@XmlAttribute(name = "filename")
	private String filename;

	@XmlAttribute(name = "overwrite")
	private Boolean overwrite;
	
	/*@XmlElement(name = "content",  type = Content.class)
	private Content content;*/
	
	@XmlValue
	String texto;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/*public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}*/

	public Boolean getOverwrite() {
		return overwrite;
	}

	public void setOverwrite(Boolean overwrite) {
		this.overwrite = overwrite;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	
	
}
