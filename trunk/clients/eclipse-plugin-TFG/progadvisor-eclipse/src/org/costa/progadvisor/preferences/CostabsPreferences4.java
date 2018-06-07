package org.costa.progadvisor.preferences;

import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.messages.Messages;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CostabsPreferences4 extends PreferencePage implements
     IWorkbenchPreferencePage {
	String appIdSelected = null;
	String selected;
	
	public CostabsPreferences4() {
	
	IPreferencePage page = new CostabsPreferences2();
	PreferenceManager mgr = new PreferenceManager();
	//mgr.remove("1");
	IPreferenceNode node = new PreferenceNode("1", page);
	mgr.addToRoot(node);

	Apps apps;
	
	try {
		apps = PreferencesManager.getInstance().getApps();

		for(App app: apps.getApps()) {		
			System.out.println("CostabsPreferences4 app.getAppId()="+app.getAppId());
			App app1 = PreferencesManager.getInstance().getApp(app.getAppId());
			
			PreferenceNode snode = new PreferenceNode(app.getAppId(), 
					new CostabsPreferences2(app, app1.getAppinfo().getDesc().getDescShort()));
			
			System.out.println("node.getId()="+node.getId());
			mgr.addTo(node.getId(), snode);	
			
			
		}

		
		
		PreferenceDialog dialog = new PreferenceDialog(null, mgr);			
		
		
		
		
//JCF//		dialog.setPreferenceStore(Activator.getDefault().getPreferenceStore());
		dialog.create();
		
		dialog.setMessage(Messages.OptionsDialog2_4);
		dialog.open();				

		if (dialog.getReturnCode() == PreferenceDialog.CANCEL) {
			
			return;
		}			

	} catch (CostabsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}
}
