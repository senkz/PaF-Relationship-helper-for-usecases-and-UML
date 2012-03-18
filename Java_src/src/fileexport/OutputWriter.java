package fileexport;

import model.ModelDiagram;
import controller.DataController;

public class OutputWriter {
	DataController dc;
	
	public OutputWriter() {
		dc = DataController.getInstance();
	}
	
	public void write(String fn, String form) {
		ModelDiagram md = dc.getModel();
		if(form.equalsIgnoreCase("xmlmd")) {
			XMLMDWriter writer = new XMLMDWriter();
			writer.write(fn, md);
		}
	}
}
