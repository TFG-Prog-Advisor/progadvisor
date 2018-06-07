package org.costa.progadvisor.commandsServer;
import java.util.List;


public class Cmd_app_info {
	
	private String command = "app_info";
	private Object app_id;
	
	public Cmd_app_info(String id){
		app_id = id;
	}
	
	public Cmd_app_info(List<String> ids){
		app_id = ids;		
	}
	
	public Cmd_app_info(){
		app_id = "_ei_all";
	}

	

}
