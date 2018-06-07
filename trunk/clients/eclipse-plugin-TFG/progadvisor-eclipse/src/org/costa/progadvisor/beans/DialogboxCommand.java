package org.costa.progadvisor.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dialogbox")
public class DialogboxCommand {
	
	@XmlAttribute(name = "boxwidth")
	private Integer boxwidth;
	
	@XmlAttribute(name = "boxheight")
	private Integer boxheight;
	
	@XmlAttribute(name = "boxtitle")
	private String boxtitle;
	
	@XmlAttribute(name = "outclass")
	private String outclass;
	
	@XmlElement(name = "content",  type = Content.class)
	private Content content;
	
	private String type = "mark_node";
	
	private String file;
	
	private Nodes nodes;

	public Nodes getNodes() {
		return nodes;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	public Integer getBoxwidth() {
		return boxwidth;
	}

	public void setBoxwidth(Integer boxwidth) {
		this.boxwidth = boxwidth;
	}

	public Integer getBoxheight() {
		return boxheight;
	}

	public void setBoxheight(Integer boxheight) {
		this.boxheight = boxheight;
	}

	public String getBoxtitle() {
		return boxtitle;
	}

	public void setBoxtitle(String boxtitle) {
		this.boxtitle = boxtitle;
	}

	public String getOutclass() {
		return outclass;
	}

	public void setOutclass(String outclass) {
		this.outclass = outclass;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
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
