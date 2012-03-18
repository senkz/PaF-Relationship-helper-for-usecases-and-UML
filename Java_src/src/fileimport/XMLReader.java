package fileimport;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader implements ReaderInterface {

	private ArrayList<String> usecases = new ArrayList<String>();
	private ArrayList<String> classes = new ArrayList<String>();
	private Map<String, ArrayList<String>> xmlinfo = new HashMap<String, ArrayList<String>>();

	public XMLReader() {
	}

	@Override
	public Map<String, ArrayList<String>> read(String fn) {
		parseXmlFile(fn);
		xmlinfo.put("usecases", usecases);
		xmlinfo.put("classes", classes);
		return xmlinfo;
	}

	private void parseXmlFile(String fn) {
		try {
			File file = new File(fn);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			NodeList usecases = doc.getElementsByTagName("usecase");

			for (int i = 0; i < usecases.getLength(); i++) {
				this.usecases.add(usecases.item(i).getChildNodes().item(0)
						.getNodeValue());
			}
			
			NodeList classes = doc.getElementsByTagName("class");

			for (int i = 0; i < classes.getLength(); i++) {
				this.classes.add(classes.item(i).getChildNodes().item(0)
						.getNodeValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
