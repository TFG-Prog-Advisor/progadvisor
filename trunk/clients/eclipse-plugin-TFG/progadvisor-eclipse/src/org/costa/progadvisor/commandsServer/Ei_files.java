package org.costa.progadvisor.commandsServer;
import java.util.List;


public class Ei_files {
	private String id;
	private String name;
	private String type;
	private List<String> _ei_files;
	private String content;
	
	public Ei_files(String iden, String nam, String typ,List<String> files,  String con){
		id = iden;
		name = nam;
		type = typ;
		_ei_files = files;	
		content = con;
	}
	
	

}
