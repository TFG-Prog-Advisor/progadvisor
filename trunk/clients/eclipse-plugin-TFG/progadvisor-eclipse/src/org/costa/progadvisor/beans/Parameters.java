package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "parameters")
public class Parameters {
	
	@XmlAttribute(name = "prefix")
	private String prefix;
	
	@XmlAttribute(name = "check")
	private Boolean check;
	
	@XmlElement(name = "selectone", type = Selectone.class)
	private List<Selectone> selectone;
	
	@XmlElement(name = "selectmany", type = Selectmany.class)
	private List<Selectmany> selectMany;
	
	@XmlElement(name = "flag", type = Flag.class)
	private List<Flag> flag;
	
	@XmlElement(name = "passwordfield", type = Passwordfield.class)
	private List<Passwordfield> passwordfield;
	
	@XmlElement(name = "textfield", type = Textfield.class)
	private List<Textfield> textfield;
	
	@XmlElement(name = "hidden", type = Hidden.class)
	private List<Hidden> hidden;


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

	public List<Selectone> getSelectone() {
		return selectone;
	}

	public void setSelectone(List<Selectone> selectone) {
		this.selectone = selectone;
	}

	public List<Selectmany> getSelectMany() {
		return selectMany;
	}

	public void setSelectMany(List<Selectmany> selectMany) {
		this.selectMany = selectMany;
	}

	public List<Flag> getFlag() {
		return flag;
	}

	public void setFlag(List<Flag> flag) {
		this.flag = flag;
	}

	public List<Textfield> getTextfield() {
		return textfield;
	}

	public void setTextfield(List<Textfield> textfield) {
		this.textfield = textfield;
	}

	public List<Hidden> getHidden() {
		return hidden;
	}

	public void setHidden(List<Hidden> hidden) {
		this.hidden = hidden;
	}

	public List<Passwordfield> getPasswordfield() {
		return passwordfield;
	}

	public void setPasswordfield(List<Passwordfield> passwordfield) {
		this.passwordfield = passwordfield;
	}
	

}
