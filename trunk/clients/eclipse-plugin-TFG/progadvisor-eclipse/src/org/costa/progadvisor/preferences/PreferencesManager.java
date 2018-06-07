package org.costa.progadvisor.preferences;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.costa.progadvisor.Activator;
import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.beans.Ei_output;
import org.costa.progadvisor.beans.Ei_response;
import org.costa.progadvisor.beans.Selectmany;
import org.costa.progadvisor.beans.Selectone;
import org.costa.progadvisor.commandsServer.Cmd_app_details;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.structures.CostabsConstants;
import org.costa.progadvisor.structures.CostabsXMLFrontend;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import org.osgi.framework.Bundle;

import com.google.gson.Gson;


public class PreferencesManager {

	private Apps apps;	

	private static PreferencesManager manager;
	
	protected static String address;	

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		PreferencesManager.address = address;
	}

	private HashMap<String, String> optionsTypes;
	private Throwable e;
	
	private PreferencesManager() throws CostabsException {
		
		//System.out.println("PASO POR AQUI Preferences2");
		//Bundle bundle = Platform.getBundle(CostabsConstants2.PLUGIN_ID);
		Path path = new Path(CostabsConstants.OPTIONS_FILE_2);
		System.out.println("path.toString() = " + path.toString());
		
		//URL fileURL = FileLocator.find(bundle, path, null);
		
		
		try {			
					
					//address = CostabsConstants2.ADDRESS_EISERVER;
					address = Activator.getDefault().getPreferenceStore().getString("urlAddress");						
					
					Gson gson = new Gson();						
					
					System.out.println("address="+address);
					
					if (address.equals("")){
						//Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						//DialogPrinter2.printMessage(activeShell, "Cannot load the server. Add the server in Window -> Preferences");
						return;						
					}
					
					Cmd_app_details obj4 = new Cmd_app_details();
					String json = gson.toJson(obj4);
					//System.out.println(json);
					//System.out.println();
					String outputString = excutePost( address, json );						
					
					//System.out.println("outputString = "+outputString);
					
					InputStream is = new ByteArrayInputStream(outputString.getBytes());
					
					Ei_response ei_response = CostabsXMLFrontend.readApp_output(is);
					
					Ei_output ei_app_output = ei_response.getEi_app_output();
					
					apps = ei_app_output.getApps();
					
					for(App app: apps.getApps()){		
					
						saveOptionTypes(app);
						
					
					}	
				
			
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new CostabsException("Cannot read the apps file '" + CostabsConstants.OPTIONS_FILE_2+ "'. The plugin will not work properly", e);
		}
	}

	private void saveOptionTypes (App app) {
		optionsTypes = new HashMap<String, String>();
		
		
			if (app.getParameters() == null) {
				return;
			}
			
			if(app.getParameters().getSelectMany()!=null){
				for(Selectmany option: app.getParameters().getSelectMany()){
					//String optid = getOptionId(app.getAppinfo().getAcronym(),option.getName());
					String optid = getOptionId(app.getAppId(),option.getName());					
					optionsTypes.put(optid, option.getWidget());
				}
			}			
			if(app.getParameters().getSelectone()!=null){
				for(Selectone option: app.getParameters().getSelectone()){
					String optid = getOptionId(app.getAppinfo().getAcronym(),option.getName());					
					optionsTypes.put(optid, option.getWidget());
				}
			}
			
			
		
			return;
	}
	
	public static PreferencesManager getInstance () throws CostabsException {
		if (PreferencesManager.manager == null) {
			PreferencesManager.manager = new PreferencesManager ();
		}
		return PreferencesManager.manager;
	}
	
	public static PreferencesManager getInstance (String newAddress) throws CostabsException {
		PreferencesManager.address = newAddress;		
		PreferencesManager.manager = new PreferencesManager();
		
		
		return PreferencesManager.manager;
	}

	public Apps getApps() {
		return apps;
	}

	public App getApp(String idApp) throws CostabsException {
		for(App app: apps.getApps()){
			
			//System.out.println("idApp="+idApp);
			//System.out.println("app.getAppId()="+app.getAppId());
			
			if (idApp!=null && idApp.equals(app.getAppId())) {				
				try {					
					saveOptionTypes(app);
					return app;
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}
		throw new CostabsException("Cannot read the apps file '" +  "'. The plugin will not work properly", e);
		}
			

	
	public String getOptionId (String app, String option) {
		return app + "_" + option;
	}
	
	public String getOptionType (String optId) {
		return optionsTypes.get(optId);
	}
	
	public boolean isBooleanOption (String optString) {
		if ("boolean".equals(optionsTypes.get(optString))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	 public static String excutePost(String targetURL, String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	   
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  	      
	      connection.setRequestProperty("eirequest", urlParameters);				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);
		   
	      
	      String param = "eirequest=" + URLEncoder.encode(urlParameters, "UTF-8");
	      
	      OutputStream os = connection.getOutputStream();
	        os.write(param.getBytes());
	        os.flush();
	        os.close ();		   

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      String ls = System.getProperty("line.separator");
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append(ls);
	      }
	      rd.close();      
	     
		  ///Esto es para que pueda hacer el unmarshalling sin perder los caracteres 
		    String result = response.toString();
		      System.out.println("result.indexOf('CDATA') =  "+result.indexOf("CDATA"));
		      
		      if(result.indexOf("CDATA") == -1){
		    	  String textoReplace = "";
		    	  result = result.replaceAll("<content format=\"html\">", "<content format=\"html\"><![CDATA[");
		    	  //result = result.replaceAll("<content format=\"text\">", "<content format=\"text\"><![CDATA[");
		    	  result = result.replaceAll("<content format='html'>", "<content format='html'><![CDATA[");
		    	  //result = result.replaceAll("<content format='text'>", "<content format='text'><![CDATA[");
		    	  
		    	  String[] sub = result.split("</content>");
		    	  if(sub.length>0){
		    		  for( int i=0; i<sub.length; i++){
		    			  if(i < sub.length-1){
		    				  if(sub[i].indexOf("<![CDATA[") != -1){
		    					  textoReplace += sub[i] + "]]></content>";
		    				  }
		    				  else{
		    					  textoReplace += sub[i] + "</content>";
		    				  }
		    			  }
		    			  else{
		    				  textoReplace += sub[i];
		    			  }		    		 
		    		  }
		    	  }
		    	  //System.out.println("textoReplace = "+textoReplace);
		    	  
		    	  if(!textoReplace.equals(""))
		    		  result = textoReplace;
		    	  
		    	  //result = result.replaceAll("</content>", "]]></content>");
		    	  
		    	 
		    	  System.out.println("**************");
		      }
		      
		      //System.out.println("result =  "+result);
		      
		      return result;
       

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
	
}
