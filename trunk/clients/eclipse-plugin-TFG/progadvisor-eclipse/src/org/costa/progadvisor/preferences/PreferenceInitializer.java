package org.costa.progadvisor.preferences;

import org.costa.progadvisor.Activator;
import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.beans.Selectmany;
import org.costa.progadvisor.beans.Selectone;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.structures.CostabsConstants;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.StringFieldEditor;







/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();		

		Apps apps;
		try {
			
			store.setDefault("urlAddress", CostabsConstants.ADDRESS_EISERVER);
			
			//CUIDADO; CAMBIAR
			
			apps = PreferencesManager.getInstance().getApps();
			
			if(apps == null){
				return;
			}

			System.out.println("Inicializando 2...");
			for(int i = 0; i < apps.getApps().size();  i ++) {
				App app = PreferencesManager.getInstance().getApp(apps.getApps().get(i).getAppId());

				//System.out.println("paso por..");
				
				if( app.getParameters().getSelectMany()!=null){

					for (int j = 0; j < app.getParameters().getSelectMany().size(); j++) {
						Selectmany option = app.getParameters().getSelectMany().get(j);
						//String optid = PreferencesManager2.getInstance().getOptionId(app.getAppinfo().getAcronym(),option.getName());
						String optid = PreferencesManager.getInstance().getOptionId(app.getAppId(),option.getName());
						//					String optid = analysis.getAnalysisId() + "_" + option.getOptname();
						if (PreferencesManager.getInstance().isBooleanOption(optid)) {
							if (CostabsConstants.BOOLEAN_TRUE.equals(option.getDefaultvalue())) {
								store.setDefault(optid, true);
							}
							else {
								store.setDefault(optid, false);
							}
						}
						else {
							store.setDefault(optid, option.getDefaultvalue().getValue());
						}
					}
				}
				//System.out.println("paso por 2..");
				if( app.getParameters().getSelectone()!=null){

					for (int j = 0; j < app.getParameters().getSelectone().size(); j++) {
						Selectone option = app.getParameters().getSelectone().get(j);
						//String optid = PreferencesManager2.getInstance().getOptionId(app.getAppinfo().getAcronym(),option.getName());
						String optid = PreferencesManager.getInstance().getOptionId(app.getAppId(),option.getName());
						//					String optid = analysis.getAnalysisId() + "_" + option.getOptname();
						if (PreferencesManager.getInstance().isBooleanOption(optid)) {
							if (CostabsConstants.BOOLEAN_TRUE.equals(option.getDefaultvalue())) {
								store.setDefault(optid, true);
							}
							else {
								store.setDefault(optid, false);
							}
						}
						else {
							store.setDefault(optid, option.getDefaultvalue().getDefaultvalue());
						}
					}
				}
				//System.out.println("paso por 3..");

			}

		} catch (CostabsException e) {
			DialogPrinter.logError(new Exception ("Preferences cannot be initialized. Error: " + e.getMessage(), e));
		}

	}

}
