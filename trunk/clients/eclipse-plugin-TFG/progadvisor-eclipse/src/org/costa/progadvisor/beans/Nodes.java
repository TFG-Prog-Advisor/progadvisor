package org.costa.progadvisor.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.costa.progadvisor.structures.CostabsXMLFrontend;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "node")
public class Nodes {
	
	@XmlElement(name = "node", type = Node.class)
	private List<Node> nodes;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setLines(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return CostabsXMLFrontend.toString(this);
	}

	public boolean contains(Node n) {
		// TODO Auto-generated method stub
		return false;
	}
}
