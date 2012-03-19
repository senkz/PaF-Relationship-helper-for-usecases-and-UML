package model;

import java.util.ArrayList;

import controller.DataController;
import fileimport.XMLReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		XMLReader reader = new XMLReader();
	    DataController dc = DataController.getInstance();
	    dc.addModel(new ModelDiagram(reader.read("../xml/project.xml")));
	    ModelDiagram md = dc.getModel();
	    ArrayList<DiagramObject> dol = md.getDiagramObjects();
	    for(DiagramObject dio : dol) {
	    	System.out.println("[" + dio.getType() + " " + dio.getNaam() + "]");
	    }
	}
}
