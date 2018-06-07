package org.costa.progadvisor.beans;



public class Command {	
	
		
	//private String text;
	private String level;
	private String color;	
	private Content content;
	private Properties properties;
	private Elements elements;	
	private Nodes nodes;
	private Lines lines;
	private String type;
	private String consoleid;
	
	

	
	public Lines getLines() {
		return lines;
	}
	public void setLines(Lines lines) {
		this.lines = lines;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}	
	public Nodes getNodes() {
		return nodes;
	}
	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}
	
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Elements getElements() {
		return elements;
	}
	public void setElements(Elements elements) {
		this.elements = elements;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConsoleid() {
		return consoleid;
	}
	public void setConsoleid(String consoleid) {
		this.consoleid = consoleid;
	}
	
	
}
