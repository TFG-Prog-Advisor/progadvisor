package org.costa.progadvisor.commandsServer;
import java.util.List;
import java.util.Map;


public class Cmd_execute {
	
	private String command = "execute";
	
	private String app_id;
	
	private Map<String, ?> parameters;	
	
	private List<String> _ei_outline;
	
	private String _ei_clientid;
	
	public Cmd_execute(String id, Map<String, ?> param ){
		app_id = id;
		parameters = param;		
	
	}

}
