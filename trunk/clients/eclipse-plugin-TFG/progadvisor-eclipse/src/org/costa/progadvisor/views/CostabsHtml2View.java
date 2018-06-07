package org.costa.progadvisor.views;

import java.io.File;

import org.costa.progadvisor.awt.ScrollableSwingComposite;
import org.costa.progadvisor.panels.CostabsGraphPanel;
import org.costa.progadvisor.trackers.OutputManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class CostabsHtml2View extends ViewPart{
	final String SCRIPT = "function valorCelda(celda,x)  {" +
			" destino = document.getElementById('table');" +
					"     destino.value = celda.innerHTML;" +
					"     alert(destino.value); mouseDownHappened(x);" +
					"}";//document.onmousedown = function(e) {if (!e) {e = window.event;} if (e) {mouseDownHappened(e.clientX, e.clientY);}}";
	//
	Browser browser;
	
	@Override
	public void createPartControl(Composite parent) {
	try {
		/*System.out.println(System.getProperty("java.library.path"));
        System.setProperty("org.eclipse.swt.browser.DefaultType","ie,mozilla");
        System.out.println(System.getProperty("org.eclipse.swt.browser.DefaultType"));
        System.setProperty("org.eclipse.swt.browser.XULRunnerPath",File.separator + "opt"+ File.separator + "xulrunner"+ File.separator);
        System.out.println(System.getProperty("org.eclipse.swt.browser.XULRunnerPath"));*/

		browser = new Browser(parent, SWT.NONE);
	} catch (SWTError e) {
		System.out.println("Could not instantiate Browser: " + e.getMessage());
		
		return;
	}
	final BrowserFunction function = new CustomFunction (browser, "mouseDownHappened");
	final BrowserFunction function2 = new CustomFunction (browser, "pepito");
	browser.addProgressListener (new ProgressAdapter () {
		@Override
		public void completed (ProgressEvent event) {
			browser.execute(SCRIPT);
			browser.addLocationListener (new LocationAdapter () {
				@Override
				public void changed (LocationEvent event) {
					browser.removeLocationListener (this);
					function.dispose ();
					function2.dispose ();
				}
			});
		}
		
	});
	
	if(OutputManager.getInstance().getHtmlxml()!=null){
		System.out.println("outManager2 "+ OutputManager.getInstance().getHtmlxml());		
		browser.setText(OutputManager.getInstance().getHtmlxml());
	}
	//OutputManager2.getInstance().setPbrowser(browser);	

	//browser.setUrl("/home/beatriz/Systems/easyinterface/trunk/src/clients/eclipse-plugin/saco/config/pruebatabla.html");
	//browser.setUrl("/home/beatriz/Systems/easyinterface/trunk/src/clients/eclipse-plugin/saco/config/svgresult.xml");
	
	//browser.setText(OutputManager2.getInstance().getHtmlxml());
	
	
	
}

@Override
public void setFocus() {
	// TODO Auto-generated method stub	
}

static class CustomFunction extends BrowserFunction {
	CustomFunction (Browser browser, String name) {
		super (browser, name);
	}
	@Override
	public Object function (Object[] arguments) {
		if (arguments.length==1) System.out.println("kkk: " + arguments[0]);
		else System.out.println ("mouseDown: " + ((Number)arguments[0]).intValue() + "," + ((Number)arguments[1]).intValue());
		return null;
	}
}
}
