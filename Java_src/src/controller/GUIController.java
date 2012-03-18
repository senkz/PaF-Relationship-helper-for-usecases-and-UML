package controller;
import fileexport.OutputFormat;
import fileexport.OutputWriter;
import fileexport.ReportGenerator;
import fileimport.ReaderFactory;
import fileimport.XMLReader;
import model.ModelDiagram;


public class GUIController {
	DataController dc;
	ReportGenerator rg;
	OutputWriter ow;
	ReaderFactory rf = new ReaderFactory();
	
	public GUIController() {
		
	}
	
	public void generateReport() {
		
	}
	
	public ModelDiagram read(String fn) {
		return rf.read(fn);
	}
	
	public void write(String fp, OutputFormat of) {
		
	}
}
