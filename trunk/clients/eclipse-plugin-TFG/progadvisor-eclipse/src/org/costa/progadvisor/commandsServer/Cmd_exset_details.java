package org.costa.progadvisor.commandsServer;
import java.util.List;


public class Cmd_exset_details {
	
	private String command = "exset_details";
	private Object exset_id;
	
	public Cmd_exset_details(String id){
		exset_id = id;
	}
	
	public Cmd_exset_details(List<String> ids){
		exset_id = ids;		
	}
	
	public Cmd_exset_details(){
		exset_id = "_ei_all";
	}

}
