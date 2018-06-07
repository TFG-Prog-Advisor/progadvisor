package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "elements")
public class Elements {
	
	@XmlElement(name = "selector", type = Selector.class)
	private List<Selector> selector;

	public List<Selector> getSelector() {
		return selector;
	}

	public void setSelector(List<Selector> selector) {
		this.selector = selector;
	}
	
	private List<Node> nodes;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setLines(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public boolean contains(Node n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return CostabsXMLFrontend.toString(this);
	}
}
