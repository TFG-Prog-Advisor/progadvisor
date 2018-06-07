package org.costa.progadvisor.structures;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.beans.CostabsOutput;
import org.costa.progadvisor.beans.CostabsOutput2_new;
import org.costa.progadvisor.beans.Ei_out;
import org.costa.progadvisor.beans.Ei_response;
import org.costa.progadvisor.beans.Parameters;
import org.costa.progadvisor.exceptions.CostabsException;
import org.w3c.dom.Document;


public class CostabsXMLFrontend {
	

	public static String toString (Object o) {
		JAXBContext context;
		
		
		try {				        
	   
			context = JAXBContext.newInstance(o.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter stream = new StringWriter();
			m.marshal(o, stream);
			stream.close();
			return stream.toString();
			
			
		} catch (JAXBException e) {
			return "XML not generated: " + o.toString();
		} catch (IOException e) {
			return "XML not generated: " + o.toString(); 
		}
	}
	
	

	// Export
	public static void marshal(Object o, OutputStream writer)
			throws IOException, JAXBException {
		JAXBContext context;
		context = JAXBContext.newInstance(o.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(o, writer);
	}

	
	
	
	
	public static Apps readApps(InputStream importFile) throws CostabsException {
		Apps apps;

		try {
			JAXBContext context = JAXBContext.newInstance(Apps.class);
			Unmarshaller um = context.createUnmarshaller();
			apps = (Apps) um.unmarshal(importFile);	
			
			return apps;
		} catch (JAXBException e) {
			throw new CostabsException("Error " + e.getMessage() + " has ocurred while parsing the apps file");
		}		
	}
	
	public static App readApp(InputStream importFile) throws CostabsException {
		App app;

		try {
			JAXBContext context = JAXBContext.newInstance(App.class);
			Unmarshaller um = context.createUnmarshaller();
			app = (App) um.unmarshal(importFile);
			
			Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        //marshaller.marshal(app, System.out);
	        
	        
			return app;
		} catch (JAXBException e) {
			throw new CostabsException("Error " + e.getMessage() + " has ocurred while parsing the app file");
		}
	}
	
	public static Ei_response readApp_output(InputStream importFile) throws CostabsException {
		Ei_response ei_response;

		try {
			JAXBContext context = JAXBContext.newInstance(Ei_response.class);
			Unmarshaller um = context.createUnmarshaller();
			ei_response = (Ei_response) um.unmarshal(importFile);
			
			Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(ei_response, System.out);
	        
	        System.out.println("marshall "+ei_response.toString());	               
	       
	        
			return ei_response;
		} catch (JAXBException e) {
			throw new CostabsException("Error " + e.getMessage() + " has ocurred while parsing the ei_response file");
		}
	}

	public static String execOptionsCommand (String command) {
		try {
			String [] commands = command.split(";");
			StringBuffer output = new StringBuffer();
			for (String c: commands) {
				Process p = Runtime.getRuntime().exec(c);
				p.waitFor();
				BufferedReader buf = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String line = "";
				while ((line = buf.readLine()) != null) {
					output.append(line + "\n");
				}
			}
			return output.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static Parameters readParameters(String content, String command) throws CostabsException {
		Parameters parameters;
		try {
			JAXBContext context = JAXBContext.newInstance(Parameters.class);
			Unmarshaller um = context.createUnmarshaller();
			StringReader reader = new StringReader (content);
			parameters = (Parameters) um.unmarshal(reader);
			return parameters;
		} catch (JAXBException e) {
			throw new CostabsException("Error " + e.getErrorCode() + " has ocurred while parsing the parameters printed by command: '" + command + "'");
		}
	}

	public static Ei_response readOutput(InputStream importFile) throws CostabsException {
	
		try {			
			Ei_response output;			
			

			JAXBContext context = JAXBContext.newInstance(Ei_response.class);
			Unmarshaller um = context.createUnmarshaller();
			output = (Ei_response) um.unmarshal(importFile);
			
			//System.out.println("output = "+output);
			
			return output;
		} catch (JAXBException e) {
			throw new CostabsException("Error '" + e.getMessage() + "' has ocurred while parsing the output file (" + CostabsConstants.OUTPUT_XML + ")",e);
		}

	}
	
	public static void cleanPrevResults() throws CostabsException {
		File f = new File (CostabsConstants.OUTPUT_XML);
		if (!f.exists()) {
			return;
		}
		//QUITAR el COMENTARIO!!!!
		f.delete();
	}
	
	

}

