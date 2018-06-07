package org.costa.progadvisor.structures;

import java.io.File;

public class CostabsConstants {

	public static final boolean DEBUG_OUTPUT = false;
	
	public static final String TMP_DIR_SACO = File.separator + "tmp" + File.separator + "costabsP" + File.separator;
	public static final String OUTPUT_XML = File.separator + "tmp" + File.separator + "costabsP" + File.separator + "output.xml";
	//public static final String OUTPUT_XML =  "/tmp/costabs/output3.xml";
	public static final String TMP_DIR = File.separator + "tmp" + File.separator + "costabsP" + File.separator + "absPL";
	public static final String ABS_PL_FILE = "abs.pl";
	public static final String EXEC_DIR = "binary" + File.separator;
	public static final String OPTIONS_FILE = "config" + File.separator + "analyses.xml";
	public static final String PLUGIN_ID = "org.costa.ProgAdvisor";

	public static final String LEVEL_INFO = "info";
	public static final String LEVEL_WARN = "warning";
	
	public static final String BOOLEAN_TRUE = "yes";
	public static final String BOOLEAN_FALSE = "no";
	
	//BBC para pruebas
	public static final String TMP_DIR_OUTPUT_HTML = File.separator + "home" + File.separator + "jcorreas" + File.separator + "Systems" + File.separator + "easyinterface" + File.separator + "trunk" + File.separator + "src" + File.separator + "clients" + File.separator + "eclipse-plugin" + File.separator + "saco" + File.separator + "config";
	public static final String OPTIONS_FILE_2 = "config" + File.separator + "apps.cfg";
	public static final String OPTIONS_DIR = "config" + File.separator;
	
	
	public static final String ADDRESS_EISERVER = "http://localhost/server/eiserver.php";
}
