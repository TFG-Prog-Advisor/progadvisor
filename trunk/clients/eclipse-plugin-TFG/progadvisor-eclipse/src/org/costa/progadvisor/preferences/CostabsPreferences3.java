package org.costa.progadvisor.preferences;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.costa.progadvisor.Activator;
import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.beans.Flag;
import org.costa.progadvisor.beans.Hidden;
import org.costa.progadvisor.beans.Option;
import org.costa.progadvisor.beans.Selectmany;
import org.costa.progadvisor.beans.Selectone;
import org.costa.progadvisor.beans.Textfield;
import org.costa.progadvisor.console.ConsoleHandler;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.messages.Messages;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.ScaleFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;


/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class CostabsPreferences3 extends PreferencePage implements IWorkbenchPreferencePage {
	
	public static final String OPT_BOOLEAN = "boolean";
	public static final String OPT_COMBO = "combo";
	public static final String OPT_INTEGER = "integer";
	public static final String OPT_RADIO = "radio";
	public static final String OPT_STRING = "string";
	public static final String OPT_SCALE = "scale";
	public static final String OPT_CHECKBOX = "checkbox";
	public static final String OPT_TEXTFIELD = "textfield";

	private HashMap<String,ArrayList<FieldEditor>> fields;

	private App anSelected;
	
	private void init () {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		System.out.println("init()");
		try {
			PreferencesManager.getInstance(); // Reading and initializing the options file 
		} catch (CostabsException e) {
			DialogPrinter.logError(new Exception ("Error while loading the Costabs plugin, an error has ocurred while reading the options: " + e.getMessage()));
		} 
	
		fields = new HashMap<String, ArrayList<FieldEditor>>();
	
		
	}
	
	
	public CostabsPreferences3() {		
		super("EasyInterface xx");
		
		System.out.println("init() CostabsPreferences3()");
		init();
	
	
	}
	
	/**
	 * Creates the preferences window showing the tab related to idAnalysis
	 * @param idAnalysis The analysis options to be shown
	 * @throws Exception 
	 */
	public CostabsPreferences3(App anSelected, String titulo) {
		super(titulo);		
		
		System.out.println("init() CostabsPreferences3(String titulo) "+titulo);
		init();
		this.anSelected = anSelected;
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected Control createContents(Composite parent) {
		Apps apps;

		
		try {
			apps = PreferencesManager.getInstance().getApps();
			
			TabFolder tabs = new TabFolder(parent, 1 | SWT.V_SCROLL | SWT.H_SCROLL);

			int selected = 0; 
			int i =0;
			for(App app: apps.getApps()) {	
				App app1 = PreferencesManager.getInstance().getApp(app.getAppId());
				
				TabItem tabItem = new TabItem(tabs, SWT.NONE);
				tabItem.setText(app1.getAppinfo().getDesc().getDescShort());
				System.out.println("app1.getAppId() "+ app1.getAppId());
				Composite form = new Composite(tabs, SWT.NONE);
				tabItem.setControl(form);	
				ArrayList<FieldEditor> lfields = createFields(app1,form);
				System.out.println("lfields size = "+lfields.size());
				this.fields.put(app1.getAppId(), lfields);
				if (app1.getAppId().equals(anSelected)) {
					selected = i;
				}
				i++;
			}
			tabs.setSelection(selected);
			
			return new Composite(parent, SWT.NULL);
		
			

		} catch (CostabsException e) {
			DialogPrinter.logError(new Exception ("Error while loading the Costabs plugin, an error has ocurred while reading the options: " + e.getMessage()));
			return null;
		}
	}


	
	
	
	private FieldEditor createFieldEditor (String app, Selectone opt, Composite parent) throws CostabsException{
		FieldEditor fe = null;

		String optid;
		try {
			optid = PreferencesManager.getInstance().getOptionId(app, opt.getName());


			String widget = opt.getWidget();

			if (OPT_BOOLEAN.equals(widget)) {
				fe = new BooleanFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_INTEGER.equals(widget)) {
				fe = new IntegerFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_RADIO.equals(widget)) {
				String [][] options = createValues (opt);
				fe = new RadioGroupFieldEditor(optid, opt.getDesc().getDescShort(),1, options, parent);
			}
			else if (OPT_STRING.equals(widget)) {
				fe = new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_SCALE.equals(widget)) {
				fe = new ScaleFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_COMBO.equals(widget)) {
				String [][] options = createValues (opt);
				fe = new ComboFieldEditor(optid, opt.getDesc().getDescShort(), options, parent);			
			}	
			else if (OPT_CHECKBOX.equals(widget)) {
				fe = new BooleanFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else {
				ConsoleHandler.write("Preference option type " + widget + " not found for app " + app);
				new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
		} catch (CostabsException e) {			
			e.printStackTrace();
		}
		return fe;
	}
	
	private FieldEditor createFieldEditor (String app, Selectmany opt, Composite parent) throws CostabsException{
		FieldEditor fe = null;

		String optid;
		try {
			optid = PreferencesManager.getInstance().getOptionId(app, opt.getName());


			String widget = opt.getWidget();

			if (OPT_BOOLEAN.equals(widget)) {
				fe = new BooleanFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_INTEGER.equals(widget)) {
				fe = new IntegerFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_RADIO.equals(widget)) {
				String [][] options = createValues (opt);
				fe = new RadioGroupFieldEditor(optid, opt.getDesc().getDescShort(),1, options, parent);
			}
			else if (OPT_STRING.equals(widget)) {
				fe = new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_SCALE.equals(widget)) {
				fe = new ScaleFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
			else if (OPT_COMBO.equals(widget)) {
				String [][] options = createValues (opt);
				fe = new ComboFieldEditor(optid, opt.getDesc().getDescShort(), options, parent);
			}
			else {
				ConsoleHandler.write("Preference option type " + widget + " not found for app " + app);
				new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
		} catch (CostabsException e) {			
			e.printStackTrace();
		}
		return fe;
	}

	private FieldEditor createFieldEditor (String app, Textfield opt, Composite parent) throws CostabsException{
		FieldEditor fe = null;

		String optid;
		try {
			optid = PreferencesManager.getInstance().getOptionId(app, opt.getName());


			String widget = opt.getWidget();

			if(widget != null){

				if (OPT_BOOLEAN.equals(widget)) {
					fe = new BooleanFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}
				else if (OPT_INTEGER.equals(widget)) {
					fe = new IntegerFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}
				else if (OPT_RADIO.equals(widget)) {
					String [][] options = createValues (opt);
					fe = new RadioGroupFieldEditor(optid, opt.getDesc().getDescShort(),1, options, parent);
				}
				else if (OPT_STRING.equals(widget)) {
					fe = new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}
				else if (OPT_SCALE.equals(widget)) {
					fe = new ScaleFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}
				else if (OPT_COMBO.equals(widget)) {
					String [][] options = createValues (opt);
					fe = new ComboFieldEditor(optid, opt.getDesc().getDescShort(), options, parent);			
				}	
				else if (OPT_CHECKBOX.equals(widget)) {
					fe = new BooleanFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}
				else if (OPT_TEXTFIELD.equals(widget)) {
					fe = new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}			
				else {
					ConsoleHandler.write("Preference option type " + widget + " not found for app " + app);
					new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
				}
			}
			else{
				fe = new StringFieldEditor(optid, opt.getDesc().getDescShort(), parent);
			}
		} catch (CostabsException e) {			
			e.printStackTrace();
		}
		return fe;
	}


	private ArrayList<FieldEditor> createFields (App app, Composite form) throws CostabsException {
		ArrayList<FieldEditor> fields = new ArrayList<FieldEditor>();
		List<Selectone> selectone = app.getParameters().getSelectone();
		List<Selectmany> selecmany = app.getParameters().getSelectMany();
		List<Flag> flag = app.getParameters().getFlag();
		List<Textfield> textfield = app.getParameters().getTextfield();
		List<Hidden> hidden = app.getParameters().getHidden();
		
		if(textfield != null){
			System.out.println("textfield");
			for(int i = 0; i < textfield.size(); i ++) {
				FieldEditor fe;		
				fe = createFieldEditor(app.getAppId(),textfield.get(i), form);		
				if (fe.getNumberOfControls() == 1) {
					fe.fillIntoGrid(form, 1);
				}
				fe.setLabelText(textfield.get(i).getName());
				
				fe.setPage(this);
				fe.setPreferenceStore(getPreferenceStore());
				fe.load();
				fields.add(fe);
			
			}
		}

		if(selectone != null){
			System.out.println("selectone");
			for(int i = 0; i < selectone.size(); i ++) {
				FieldEditor fe;		

				fe = createFieldEditor(app.getAppId(),selectone.get(i), form);		
				if (fe.getNumberOfControls() == 1) {
					fe.fillIntoGrid(form, 1);
				}
				fe.setPage(this);
				fe.setPreferenceStore(getPreferenceStore());
				fe.load();
				fields.add(fe);
			}
		}
		if(selecmany != null){
			System.out.println("selecmany");
			for(int i = 0; i < selecmany.size(); i ++) {
				FieldEditor fe;		

				fe = createFieldEditor(app.getAppId(),selecmany.get(i), form);		
				if (fe.getNumberOfControls() == 1) {
					fe.fillIntoGrid(form, 1);
				}
				fe.setPage(this);
				fe.setPreferenceStore(getPreferenceStore());
				fe.load();
				fields.add(fe);
			}
		}
		
		

		return fields;
	}
	
	private String[][] createValues(Textfield opt) {
		
		
		return null;
	}

	private String [][] createValues (Selectone selectone) {
		List<Option> values = selectone.getOption();
		String [][] strs = new String[values.size()][2];
		for (int i = 0; i < selectone.getOption().size(); i++) {
			String [] st = new String[2];
			st[0] = values.get(i).getDesc().getDescShort(); 
			st[1] = values.get(i).getValue();
			strs[i] = st;
		}
		return strs;
	}
	
	private String [][] createValues (Selectmany selectmany) {
		List<Option> values = selectmany.getOption();
		String [][] strs = new String[values.size()][2];
		for (int i = 0; i < selectmany.getOption().size(); i++) {
			String [] st = new String[2];
			st[0] = values.get(i).getDesc().getDescShort(); 
			st[1] = values.get(i).getValue();
			strs[i] = st;
		}
		return strs;
	}

	
	
	protected void performDefaults() {
		System.out.println("PAso por aqui");
		for(String key: fields.keySet()) {
			ArrayList<FieldEditor> fes = fields.get(key);
			for(FieldEditor fe: fes) {
				fe.loadDefault();
			}
		}
		super.performDefaults();
	}

	public boolean performOk() {
		System.out.println("PAso por aqui ok size = "+ fields.size());
		for(String key: fields.keySet()) {
			ArrayList<FieldEditor> fes = fields.get(key);
			for(FieldEditor fe: fes) {
				fe.store();
			}
		}
		return true;
	}

	public void performApply () {
		for(String key: fields.keySet()) {
			ArrayList<FieldEditor> fes = fields.get(key);
			for(FieldEditor fe: fes) {
				fe.store();
			}
		}
	}

}

