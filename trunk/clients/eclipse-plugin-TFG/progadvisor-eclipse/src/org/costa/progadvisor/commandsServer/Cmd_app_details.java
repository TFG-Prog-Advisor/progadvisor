package org.costa.progadvisor.commandsServer;
import java.util.List;


public class Cmd_app_details {
	
	private String command = "app_details";
	private Object app_id;
	
	public Cmd_app_details(String id){
		app_id = id;
	}
	
	public Cmd_app_details(List<String> ids){
		app_id = ids;		
	}
	
	public Cmd_app_details(){
		app_id = "_ei_all";
	}

}
