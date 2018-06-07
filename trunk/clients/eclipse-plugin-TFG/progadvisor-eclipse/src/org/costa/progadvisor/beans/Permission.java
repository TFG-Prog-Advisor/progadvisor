package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "permission")
public class Permission {
	
	@XmlAttribute(name = "default")
	private Boolean permissionDefault;
	
	@XmlElement(name = "allowed", type = Allowed.class)
	private List<Allowed> allowed;
	
	@XmlElement(name = "excluded", type = Excluded.class)
	private List<Excluded> excluded;

	public Boolean getPermissionDefault() {
		return permissionDefault;
	}

	public void setPermissionDefault(Boolean permissionDefault) {
		this.permissionDefault = permissionDefault;
	}

	public List<Allowed> getAllowed() {
		return allowed;
	}

	public void setAllowed(List<Allowed> allowed) {
		this.allowed = allowed;
	}

	public List<Excluded> getExcluded() {
		return excluded;
	}

	public void setExcluded(List<Excluded> excluded) {
		this.excluded = excluded;
	}

	


}
