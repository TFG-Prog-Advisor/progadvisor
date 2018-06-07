package org.costa.progadvisor.console;

import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.batik.css.engine.Messages;
import org.costa.progadvisor.Activator;
import org.costa.progadvisor.beans.App;
import org.costa.progadvisor.beans.Apps;
import org.costa.progadvisor.beans.Flag;
import org.costa.progadvisor.beans.Hidden;
import org.costa.progadvisor.beans.Parameters;
import org.costa.progadvisor.beans.Selectmany;
import org.costa.progadvisor.beans.Selectone;
import org.costa.progadvisor.beans.Textfield;
import org.costa.progadvisor.beans.Passwordfield;
import org.costa.progadvisor.commandsServer.Cmd_execute;
import org.costa.progadvisor.commandsServer.Ei_files;
import org.costa.progadvisor.dialogs.DialogPrinter;
import org.costa.progadvisor.exceptions.CostabsException;
import org.costa.progadvisor.preferences.PreferencesManager;
import org.costa.progadvisor.structures.CostabsConstants;
import org.costa.progadvisor.utils.SourceUtils;
import org.costa.progadvisor.utils.ZipUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kitfox.svg.Text;

public class CostabsShellCommand {

	public static String COSTABS_EXECUTABLE_PATH = "";

	// protected static String address = CostabsConstants2.ADDRESS_EISERVER;
	protected static String address = ""; // Activator.getDefault().getPreferenceStore().getString("urlAddress");

	/**
	 * Result of the last run.
	 */
	private String result = "";
	private String error = "";

	/**
	 * Create the communicator with costabs.
	 */
	public CostabsShellCommand() {
	}

	/**
	 * Get the error message from the last execution of costabs.
	 * 
	 * @return
	 */
	public String getError() {
		return error;
	}

	/**
	 * Get the result from the last execution of costabs.
	 * 
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Call to costabs to execute with the actual preferences setup.
	 * 
	 * @param file
	 *            ABS to be passed to costabs.
	 * @param entries
	 *            The names of methods / functions to use in costabs.
	 */
	public String analyze(ArrayList<String> entries, String app, Shell shellEclipse) throws CostabsException {

		address = Activator.getDefault().getPreferenceStore().getString("urlAddress");
		System.out.println("address analyze 1= " + address);
		System.out.println("address analyze 2= " + PreferencesManager.getAddress());
		System.out
				.println("address analyze 3 = " + Activator.getDefault().getPreferenceStore().getString("urlAddress"));
		if (address != null) {
			String cmd = buildCommand(entries, app, shellEclipse);
			if (cmd != null) {
				String response = executeCommand(address, cmd);
				result = response;
				System.out.println("Address = " + address);
				// System.out.println("response = " + response);
				System.out.println("result = " + result);
			} else {
				result = null;
			}
		} else {
			Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			DialogPrinter.printMessage(activeShell,
					"1 Cannot load the server. Add the server in Window -> Preferences");
		}
		return result;

	}

	public static final String PARAMS_ID = "_ei_params";
	public static final String FILE_ID = "_ei_files";
	public static final String ENTRIES_ID = "_ei_outline";
	public static final String DIRS_ID = "_ei_dirs";
	public static final String root_ID = "_ei_root";
	public static final String SESSION_ID = "_ei_sessionid";
	public static final String CLIENT_ID = "_ei_clientid";

	;

	/**
	 * 
	 * 
	 * Auxiliar method, just to build the shell command with the entries and
	 * preferences setup.
	 * 
	 * @param entries
	 *            The entries to be used in costabs.
	 * @return The string that has the shell command to use costabs.
	 */
	private String buildCommand(final ArrayList<String> entries, final String idApp, Shell shellEclipse)
			throws CostabsException {

		Gson gson = new Gson();

		String json = "";

		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			{
				App app = PreferencesManager.getInstance().getApp(idApp);

				System.out.println("ID DEL APP: " + app.getAppId());
				String tipo = app.getAppinfo().getUploadType();
				System.out.println("TIPO TIPO TIPO:" + tipo);
				List<String> ei_outline = new ArrayList<String>();
				// Build entries
				StringBuffer entriesBuf = new StringBuffer();
				for (int i = 0; i < entries.size(); i++) {
					entriesBuf.append(entries.get(i));
					ei_outline.add(entries.get(i).toString());
				}

				String contenido = "";
				String absFile = "";
				if (tipo.equals("active")) {

					SourceUtils.obtainCurrProject().getName();
					String currProjPath = SourceUtils.obtainCurrProject().getLocation().makeAbsolute().toString();
					ZipUtils appZip = new ZipUtils(currProjPath);
					appZip.generateFileList(new File(currProjPath));
					absFile = currProjPath + ".txt";
					byte[] zipbytes = appZip.zipIt(null);

					contenido = Base64.getEncoder().encodeToString(zipbytes);
				} else if (tipo.equals("many")) {
					String chosenDirectory = "";
					String currProjPath = SourceUtils.obtainCurrProject().getLocation().makeAbsolute().toString();
					DirectoryDialog directorySelectorDialog = new DirectoryDialog(shellEclipse, SWT.OPEN);
					
					directorySelectorDialog.setText("Selecciona un directorio");
					directorySelectorDialog.setFilterPath(currProjPath);
					chosenDirectory = directorySelectorDialog.open();
					
					if (chosenDirectory == null)
						return null;

					ZipUtils appZip = new ZipUtils(chosenDirectory);
					
					appZip.generateFileList(new File(chosenDirectory));
					absFile = chosenDirectory + ".txt";
					byte[] zipbytes = appZip.zipIt(null);

					contenido = Base64.getEncoder().encodeToString(zipbytes);

				} else if (tipo.equals("file")) {
					absFile = SourceUtils.extractResource(SourceUtils.obtainActiveEditor()).getLocation().toString();
					contenido = new String(Files.readAllBytes(Paths.get(absFile)));

				} else {
					absFile = SourceUtils.extractResource(SourceUtils.obtainActiveEditor()).getLocation().toString();
					contenido = "";

				}

				Ei_files files = new Ei_files("10", Paths.get(absFile).getFileName().toString(), "text", null,
						contenido);

				List<Ei_files> lfiles = new ArrayList<Ei_files>();
				lfiles.add(files);

				IPreferenceStore store = Activator.getDefault().getPreferenceStore();

				if (app.getParameters().getSelectone() != null) {
					for (Selectone op : app.getParameters().getSelectone()) {
						List<String> lSelectone = new ArrayList<String>();
						String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
						lSelectone.add(store.getString(optId));
						parameters.put(op.getName(), lSelectone);
					}
				}

				if (app.getParameters().getSelectMany() != null) {
					for (Selectmany op : app.getParameters().getSelectMany()) {
						List<String> lSelectmany = new ArrayList<String>();
						String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
						lSelectmany.add(store.getString(optId));
						parameters.put(op.getName(), lSelectmany);
					}
				}

				if (app.getParameters().getTextfield() != null) {
					for (Textfield op : app.getParameters().getTextfield()) {
						List<String> lTextField = new ArrayList<String>();
						String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
						lTextField.add(store.getString(optId));
						parameters.put(op.getName(), lTextField);
					}
				}

				if (app.getParameters().getPasswordfield() != null) {
					for (Passwordfield op : app.getParameters().getPasswordfield()) {
						List<String> lPasswordField = new ArrayList<String>();
						String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
						lPasswordField.add(store.getString(optId));
						parameters.put(op.getName(), lPasswordField);
					}
				}

				
				if (app.getParameters().getFlag() != null) {
					for (Flag op : app.getParameters().getFlag()) {
						List<String> lFlag = new ArrayList<String>();
						String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
						lFlag.add(store.getString(optId));
						parameters.put(op.getName(), lFlag);
					}
				}

				parameters.put("_ei_files", lfiles);
				parameters.put("_ei_outline", ei_outline);
				parameters.put("_ei_clientid", "web_client");
			}
			// };

			Cmd_execute obj6 = new Cmd_execute(idApp, parameters);
			json = gson.toJson(obj6);
			System.out.println("json =" + json);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;

	}

	/**
	 * Auxiliar method to add to a string the options checked in preferences.
	 * 
	 * @param command
	 *            The String with the shell command to ABS.
	 */
	private String buildOptions(String idApp) throws CostabsException {
		StringBuffer command = new StringBuffer();

		// command.append("-cost_model " +
		// store.getString(PreferenceConstants.PCOST_MODEL) + " ");
		// command.append("-cost_centers " +
		// store.getString(PreferenceConstants.PCOST_CENTER) + " ");
		// command.append("-norm " + store.getString(PreferenceConstants.PSIZE_ABST) + "
		// ");
		// if (store.getString(PreferenceConstants.PASYMPTOTIC) == "yes")
		// command.append("-a ");
		// if (store.getString(PreferenceConstants.PDEBUG_MODE) == "yes")
		// command.append("-debug ");
		// command.append("-verbosity " +
		// store.getString(PreferenceConstants.PVERBOSITY) + " ");

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		App app = PreferencesManager.getInstance().getApp(idApp);

		if (app.getParameters().getSelectone() != null) {
			for (Selectone op : app.getParameters().getSelectone()) {
				String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
				if (PreferencesManager.getInstance().isBooleanOption(optId)) {
					if (store.getBoolean(optId)) {
						command.append(" " + app.getParameters().getPrefix() + op.getName() + " ");
					}
				} else {
					command.append(" " + app.getParameters().getPrefix() + op.getName() + " " + store.getString(optId));
				}
			}
		}
		if (app.getParameters().getSelectMany() != null) {
			for (Selectmany op : app.getParameters().getSelectMany()) {
				String optId = PreferencesManager.getInstance().getOptionId(app.getAppId(), op.getName());
				if (PreferencesManager.getInstance().isBooleanOption(optId)) {
					if (store.getBoolean(optId)) {
						command.append(" " + app.getParameters().getPrefix() + op.getName() + " ");
					}
				} else {
					command.append(" " + app.getParameters().getPrefix() + op.getName() + " " + store.getString(optId));
				}
			}
		}

		System.out.println("command=" + command.toString());
		return command.toString();
	}

	/**
	 * Create a process to execute the command given by argument in a shell.
	 * 
	 * @param command
	 *            The shell command to be executed.
	 * @return The state of finalization of the process.
	 */
	public String executeCommand(String targetURL, String urlParameters) {
		StreamReaderThread outputThread;
		StreamReaderThread errorThread;

		System.out.println("urlparameters: " + urlParameters);

		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("eirequest", urlParameters);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			String param = "eirequest=" + URLEncoder.encode(urlParameters, "UTF-8");

			System.out.println("param: " + param);

			OutputStream os = connection.getOutputStream();
			os.write(param.getBytes());
			os.flush();
			os.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			String ls = System.getProperty("line.separator");
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append(ls);
			}
			rd.close();
			result = response.toString();

			System.out.println("RESULT: " + result);

			/// Esto es para que pueda hacer el unmarshalling sin perder los caracteres
			// System.out.println("result.indexOf('CDATA') = "+result.indexOf("CDATA"));
			// if(result.indexOf("CDATA") == -1){
			String textoReplace = "";
			if (result.indexOf("<content format=\"html\"><![CDATA[") == -1)
				result = result.replaceAll("<content format=\"html\">", "<content format=\"html\"><![CDATA[");
			// result = result.replaceAll("<content format=\"text\">", "<content
			// format=\"text\"><![CDATA[");
			if (result.indexOf("<content format='html'><![CDATA[") == -1)
				result = result.replaceAll("<content format='html'>", "<content format='html'><![CDATA[");
			// result = result.replaceAll("<content format='text'>", "<content
			// format='text'><![CDATA[");
			if (result.indexOf("<content format=\"svg\"><![CDATA[") == -1)
				result = result.replaceAll("<content format=\"svg\">", "<content format=\"svg\"><![CDATA[");
			if (result.indexOf("<content format='svg'><![CDATA[") == -1)
				result = result.replaceAll("<content format='svg'>", "<content format='svg'><![CDATA[");

			String[] sub = result.split("</content>");
			if (sub.length > 0) {
				for (int i = 0; i < sub.length; i++) {
					if (i < sub.length - 1) {
						if ((sub[i].indexOf("<![CDATA[") != -1)
								&& ((sub[i].indexOf("<content format='text'><![CDATA[") == -1)
										&& (sub[i].indexOf("<content format=\"text\"><![CDATA[") == -1))) {
							textoReplace += sub[i] + "]]></content>";
						} else {
							textoReplace += sub[i] + "</content>";
						}
					} else {
						textoReplace += sub[i];
					}
				}
			}
			// System.out.println("textoReplace = "+textoReplace);

			if (!textoReplace.equals(""))
				result = textoReplace;

			return result;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}

	}

	private String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}

		return stringBuilder.toString();
	}

}
