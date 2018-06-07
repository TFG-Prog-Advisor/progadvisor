package org.costa.progadvisor.commandsServer;
import java.util.List;


public class Cmd_app_parameters {

	private String command = "app_parameters";
	private Object app_id;
	
	public Cmd_app_parameters(String id){
		app_id = id;
	}
	
	public Cmd_app_parameters(List<String> ids){
		app_id = ids;		
	}
	
	public Cmd_app_parameters(){
		app_id = "_ei_all";
	}

}
