package org.costa.progadvisor.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;



public class PruebaXML {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Exception {
		//JAXBContext jc = JAXBContext.newInstance(CostabsOutput2_new.class);
		//JAXBContext jc = JAXBContext.newInstance(Apps.class);
		JAXBContext jc = JAXBContext.newInstance(Ei_response.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("config"+ File.separator + "outputnew.cfg");
       // File xml = new File("config/svgresult.xml");
     
       
        //File xml = new File("config/eiserver.cfg");
        //File xml = new File("config/apps2.cfg");
        //File xml = new File("config/cost.cfg");
        //File xml = new File("config/mhp.cfg");
        //File xml = new File("config/bla.cfg");
        //File xml = new File("config/deadlock.cfg");
        //File xml = new File("config/terminrg.cfg");
        //File xml = new File("config/output2.xml");
        //File xml = new File("config/cost.sh.output");
        //CostabsOutput2_new tests = (CostabsOutput2_new) unmarshaller.unmarshal(xml);
        
    
        
        Ei_response tests = (Ei_response) unmarshaller.unmarshal(xml);
        
        /*System.out.println("tests.getCommands().getConsoleCommand().size()"+tests.getCommands().getConsoleCommand().size());

        for(int i=0;i<tests.getCommands().getConsoleCommand().size();i++){
        	if(tests.getCommands().getConsoleCommand().get(i).getConsoleid()!=null && tests.getCommands().getConsoleCommand().get(i).getConsoleid().equals("Graph"))
        		System.out.println("content= "+tests.getCommands().getConsoleCommand().get(i).getContent().getText());
        }*/
              		
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);        

        
        /*marshaller.setProperty("com.sun.xml.internal.bind.characterEscapeHandler",
        		new MyHandler());*/
        
       
             
      DocumentBuilderFactory docBuilderFactory =
        		DocumentBuilderFactory.newInstance();
        Document document =
        		docBuilderFactory.newDocumentBuilder().newDocument();
        		
       
        marshaller.marshal(tests, document);
        
        TransformerFactory transformerFactory =
        		TransformerFactory.newInstance();
        Transformer nullTransformer = transformerFactory.newTransformer();
        nullTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        nullTransformer.setOutputProperty(
        		OutputKeys.CDATA_SECTION_ELEMENTS,
        		"content");
        nullTransformer.transform(new DOMSource(document),
        		new StreamResult(System.out));


        /*
        marshaller.setProperty("com.sun.xml.internal.bind.characterEscapeHandler",
                new CharacterEscapeHandler() {
                    @Override
                    public void escape(char[] ch, int start, int length,
                            boolean isAttVal, Writer writer)
                            throws IOException {
                        writer.write(ch, start, length);
                    }
                });
                */
        
      
        
        /*StringWriter sw = new StringWriter();
        marshaller.marshal(tests, sw);
        sw.toString();
        
        System.out.println(sw.toString());*/
        
        marshaller.marshal(tests, System.out);
        
       


	}

}
