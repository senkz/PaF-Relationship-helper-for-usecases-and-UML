package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import controller.DataController;

public class ModelDiagram {
	int versie;
	private ArrayList<DiagramObject> dol = new ArrayList<DiagramObject>();
	
	public ModelDiagram(Map<String, ArrayList<String>> input) {
		DataController dc = DataController.getInstance();
		this.setVersie(dc.getVersie());

	    System.out.println("\n------------------- Started making ModelDiagram (" + this.versie +  ")");

	    Iterator it = input.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        
	        if(pairs.getKey().equals("usecases")) {
	        	ArrayList<String> str = (ArrayList<String>)pairs.getValue();
	        	for(String s : str) {
		        	DiagramObject dio = new UMLUsecase();
		        	dio.setNaam(s);
		        	this.addDiagramObject(dio);
		    	    System.out.println("added usecase: " + s);
	        	}
	        }
	        
	        if(pairs.getKey().equals("classes")) {
	        	ArrayList<String> str = (ArrayList<String>)pairs.getValue();
	        	for(String s : str) {
		        	DiagramObject dio = new UMLClass();
		        	dio.setNaam(s);
		        	this.addDiagramObject(dio);
		    	    System.out.println("added class: " + s);
	        	}
	        }
	        	
	        it.remove();
	    }
	    System.out.println("------------------- Finished making ModelDiagram (" + this.versie +  ")\n");
	}

	public ArrayList<DiagramObject> getDiagramObjects() {
		return dol;
	}
	
	public void addDiagramObject(DiagramObject dio) {
		dol.add(dio);
	}
	
	private void setVersie(int i) {
		this.versie = i;
	}
	
	public int getVersie() {
		return versie;
	}
	
	public ModelDiagram() {
		DataController dc = DataController.getInstance();
		this.setVersie(dc.getVersie());
	}
	
	public void draw() {
		
	}
}
