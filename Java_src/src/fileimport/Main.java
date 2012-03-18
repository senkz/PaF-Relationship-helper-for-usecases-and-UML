package fileimport;

import java.util.Iterator;
import java.util.Map;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLReader reader = new XMLReader();
		Map mp = reader.read("../xml/project.xml");
		Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove();
	    }		
	}

}
