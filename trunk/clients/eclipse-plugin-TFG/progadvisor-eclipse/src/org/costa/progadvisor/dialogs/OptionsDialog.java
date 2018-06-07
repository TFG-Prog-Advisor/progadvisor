package org.costa.progadvisor.dialogs;


import java.io.File;
import java.util.ArrayList;

import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.messages.Messages;
import org.costa.progadvisor.preferences.CostabsPreferenceDialog;
import org.costa.progadvisor.preferences.CostabsPreferences2;
import org.costa.progadvisor.preferences.PreferencesManager;
import org.costa.progadvisor.rulers.CostabsLineNumberColumn;
import org.costa.progadvisor.structures.CostabsConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;




public class OptionsDialog extends Dialog {

	
	public OptionsDialog(Shell parentShell) {
		super(parentShell);
	}

	ArrayList<Button> analysisButtons;

	String selected;

	private boolean editPreferences;

	@Override
	protected Control createDialogArea(Composite parent) {
		try {
			Composite composite = new Composite(parent, SWT.NONE);

			analysisButtons = new ArrayList<Button>();

			Apps apps = PreferencesManager.getInstance().getApps();	
			
			
			if(apps == null){
				return null;
			}

			RowLayout verticalLayout = new RowLayout();
			verticalLayout.type = SWT.VERTICAL;
			
			composite.setLayout(verticalLayout);

			Label label = new Label (composite, SWT.NULL);
			label.setText(Messages.OptionsDialog2_available);

			Composite optionsPanel = new Composite(composite,SWT.NONE);
			optionsPanel.setLayout(new RowLayout(SWT.VERTICAL));

			boolean firstTime = true;
			for(App app: apps.getApps()) {
				
			//	if(app.getAppVisible()){
					Button option = new Button(optionsPanel, SWT.RADIO);				
					option.setText(app.getAppinfo().getAcronym());
					option.setToolTipText(app.getAppinfo().getDesc().getDescLong());
					option.setData(app.getAppId());
					if (firstTime) {
						option.setSelection(true);
						firstTime = false;
					}
					analysisButtons.add(option);
			//	}
					
				
			}

			return composite;
		}
		catch (CostabsException e) {
			DialogPrinter.printError(getParentShell(), e, "Cannot generate the options dialog");
			return null;
		}
		
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.OptionsDialog2_2);
	}

	@Override
	protected void cancelPressed() {
		this.close();
		setReturnCode(CANCEL);
	}

	@Override
	protected void okPressed() {
		saveSelected();		
		
		String appIdSelected = null;

		if (this.editPreferences) {
			IPreferencePage page = new CostabsPreferences2();
			PreferenceManager mgr = new PreferenceManager();
			IPreferenceNode node = new PreferenceNode("1", page);
			mgr.addToRoot(node);

			Apps apps;
			
			try {
				apps = PreferencesManager.getInstance().getApps();

				for(App app: apps.getApps()) {					
					App app1 = PreferencesManager.getInstance().getApp(app.getAppId());

				//	if(app1.getAppVisible()){
						PreferenceNode snode = new PreferenceNode(app.getAppId(), 
								new CostabsPreferences2(app, app1.getAppinfo().getAcronym()));
						
						mgr.addTo(node.getId(), snode);	

						if(app.getAppId().equals(selected))
							appIdSelected = snode.getId();
					}
				//}

				CostabsPreferenceDialog dialog = new CostabsPreferenceDialog(getShell(), mgr);			
				
				
				if(selected != null){
					dialog.setSelectedNode(appIdSelected);
				}
				
				
				dialog.create();
				
				dialog.setMessage(Messages.OptionsDialog2_4);
				dialog.open();				

				if (dialog.getReturnCode() == PreferenceDialog.CANCEL) {
					this.editPreferences = false;
					return;
				}				

			} catch (CostabsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.close();
		setReturnCode(OK);
	}

	protected void createButtonsForButtonBar(Composite parent) {
		GridLayout layout = new GridLayout (1,true);
		parent.setLayout(layout);
		parent.pack();

		Button ok = new Button(parent, SWT.PUSH);
		ok.setText(Messages.OptionsDialog2_5);
		GridData data = new GridData(GridData.CENTER);
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				editPreferences = true;
				okPressed();
			}
		});
		super.createButtonsForButtonBar(parent);
		this.getButton(IDialogConstants.OK_ID).setText(Messages.OptionsDialog2_6);

	}


	private void saveSelected () {
		for(Button b: analysisButtons) {
			if (b.getSelection()) {
				selected = (String)b.getData(); 
			}
		}
	}

	public String getAnalysisSelected () {
		return selected;
	}

}
