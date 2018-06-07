package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ei_response")
public class Ei_response {@XmlElement(name = "ei_output", type = Ei_output.class)
private Ei_output ei_app_output;



public Ei_output getEi_app_output() {
	return ei_app_output;
}



public void setEi_app_output(Ei_output ei_app_output) {
	this.ei_app_output = ei_app_output;
}



@Override
public String toString() {
	return CostabsXMLFrontend.toString(this);
}

}
