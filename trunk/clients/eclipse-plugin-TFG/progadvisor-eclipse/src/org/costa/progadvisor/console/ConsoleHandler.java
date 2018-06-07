package org.costa.progadvisor.console;

import java.io.OutputStream;

import org.costa.progadvisor.beans.Content;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


public class ConsoleHandler {

	public static MessageConsole defaultConsole = null;

	public static final String ID_CONSOLE_VIEW = "EasyInterface Console";

	public static MessageConsole findCostabsConsole() {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (ID_CONSOLE_VIEW.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		//no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(ID_CONSOLE_VIEW, null);
		conMan.addConsoles(new IConsole[]{myConsole});
		return myConsole;
	}
	
	 public static MessageConsole findConsole( String name )  {
	        ConsolePlugin plugin = ConsolePlugin.getDefault();
	        IConsoleManager conMan = plugin.getConsoleManager();
	        IConsole[] existing = conMan.getConsoles();
	        for ( int i = 0; i < existing.length; i++ )
	            if ( name.equals( existing[i].getName() ) ) return ( MessageConsole ) existing[i];
	        //no console found, so create a new one
	        MessageConsole myConsole = new MessageConsole( name, null );
	        conMan.addConsoles( new IConsole[]{myConsole} );
	        return myConsole;
	    }

	/**
	 * Returns the Costabs Console Output Stream to write to the console.
	 * @return Costabs Console Output Stream to write to the console.
	 */
	public static OutputStream getCostaConsoleStream () {
		MessageConsole myConsole = findCostabsConsole();
		MessageConsoleStream out = myConsole.newMessageStream();
		out.setActivateOnWrite(true);
		return out;
	}
	
	public static MessageConsoleStream getMessageConsoleStream( String name ) {
        MessageConsole myConsole = findConsole( name );
        MessageConsoleStream out = myConsole.newMessageStream();
        return out;
    }

	public static void write(String text) {
		if (defaultConsole != null) {
			MessageConsoleStream out = defaultConsole.newMessageStream();
			out.setActivateOnWrite(true);
			out.println(text);
		}
	}

	public static void clearConsole() {

		if (defaultConsole != null) 
			defaultConsole.clearConsole();
	}


	/**
	 * Gives the default console with an empty title
	 * @return Default Message Console
	 */
	public static MessageConsole getDefault() {
		if (defaultConsole == null){
			defaultConsole = new MessageConsole("Default",null);
			addConsole(defaultConsole);
		}
		return defaultConsole;
	}

	/**
	 * Adds consoles to the console View
	 * @param console
	 */
	public static void addConsole(IConsole console){
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {console});
	}

	public static final String WARNING = "warning";
	public static final String ERROR = "error";

	private static Color getColor (String level) {
		if (WARNING.equals(level)) {
			return new Color(null, 30,12,186);
		}
		else if (ERROR.equals(level)) {
			return new Color(null, 255,0,0);
		}
		else {
			return new Color(null, 0,0,0);
		}
	}

	public static void write(String level, String text) {
		if (defaultConsole != null) {
			MessageConsoleStream out = defaultConsole.newMessageStream();
			out.setActivateOnWrite(false);

			out.println(text);
			out.setColor(getColor(level));
		}
	}
	
	public static void write2(String level, Content content) {
		if (defaultConsole != null) {
			MessageConsoleStream out = defaultConsole.newMessageStream();
			out.setActivateOnWrite(false);
			if(content.getTextFormat().equals("text")){
				out.println(content.getText());
				out.setColor(getColor(level));
			}
			
			if(content.getTextFormat().equals("html")){
				out.println(content.getText());
				out.setColor(getColor(level));
			}
			
			if(content.getTextFormat().equals("svg")){
				out.println(content.getText());
				out.setColor(getColor(level));
			}
			
		}
	}
	
	public static void write2(String level, Content content, String consoleId) {
		if(consoleId != null){
			 MessageConsole myConsole = findConsole( consoleId );
		     MessageConsoleStream out = myConsole.newMessageStream();
		     
		     out.setActivateOnWrite(false);
				if(content.getTextFormat().equals("text")){
					out.println(content.getText());
					out.setColor(getColor(level));
				}

				if(content.getTextFormat().equals("html")){
					out.println(content.getText());
					out.setColor(getColor(level));
				}

				if(content.getTextFormat().equals("svg")){
					out.println(content.getText());
					out.setColor(getColor(level));
				}
			
		}
		else{
			if (defaultConsole != null) {
				MessageConsoleStream out = defaultConsole.newMessageStream();
				out.setActivateOnWrite(false);
				if(content.getTextFormat().equals("text")){
					out.println(content.getText());
					out.setColor(getColor(level));
				}

				if(content.getTextFormat().equals("html")){
					out.println(content.getText());
					out.setColor(getColor(level));
				}

				if(content.getTextFormat().equals("svg")){
					out.println(content.getText());
					out.setColor(getColor(level));
				}

			}
		}
	}
	
	public static void setActive () {
		defaultConsole.activate();
	}

}
