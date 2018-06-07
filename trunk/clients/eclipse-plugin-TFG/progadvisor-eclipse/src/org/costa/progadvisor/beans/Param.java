package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "param")
public class Param {
	@XmlElement(name = "selectone", type = Selectone.class)
	private Selectone selectone;
	
	@XmlElement(name = "selectmany", type = Selectmany.class)
	private Selectmany selectMany;
	
	@XmlElement(name = "flag", type = Flag.class)
	private Flag flag;
	
	@XmlElement(name = "passwordfield", type = Passwordfield.class)
	private Passwordfield passwordfield;

	@XmlElement(name = "textfield", type = Textfield.class)
	private Textfield textfield;
	
	@XmlElement(name = "hidden", type = Hidden.class)
	private Hidden hidden;

	public Selectone getSelectone() {
		return selectone;
	}

	public void setSelectone(Selectone selectone) {
		this.selectone = selectone;
	}

	public Selectmany getSelectMany() {
		return selectMany;
	}

	public void setSelectMany(Selectmany selectMany) {
		this.selectMany = selectMany;
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	public Textfield getTextfield() {
		return textfield;
	}

	public void setTextfield(Textfield textfield) {
		this.textfield = textfield;
	}

	public Hidden getHidden() {
		return hidden;
	}

	public void setHidden(Hidden hidden) {
		this.hidden = hidden;
	}

	public Passwordfield getPasswordfield() {
		return passwordfield;
	}

	public void setPasswordfield(Passwordfield passwordfield) {
		this.passwordfield = passwordfield;
	}

	


}
