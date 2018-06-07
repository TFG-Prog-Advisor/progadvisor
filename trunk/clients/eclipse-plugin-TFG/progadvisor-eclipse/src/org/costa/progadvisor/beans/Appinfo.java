package org.costa.progadvisor.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "appinfo")
public class Appinfo {

	@XmlElement(name = "acronym")
	private String acronym;
	
	@XmlElement(name = "title")
	private String title;
	
	@XmlElement(name = "logo", type = Logo.class)
	private Logo logo;
	
	@XmlElement(name = "desc", type = Desc.class)
	private Desc desc;

	@XmlAttribute(name = "uploadtype")
	private String uploadtype;
	

	public String getAcronym() {
		return acronym; 
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Logo getLogo() {
		return logo;
	}

	public void setLogo(Logo logo) {
		this.logo = logo;
	}

	public Desc getDesc() {
		return desc;
	}

	public void setDesc(Desc desc) {
		this.desc = desc;
	}

	public String getUploadType() {
		return uploadtype;
	}

	public void setUploadType(String uploadtype) {
		this.uploadtype = uploadtype;
	}
	
	
	
}
