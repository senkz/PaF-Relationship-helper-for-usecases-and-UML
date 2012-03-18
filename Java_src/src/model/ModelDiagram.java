package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.DataController;

public class ModelDiagram {
	int versie;
	private ArrayList<DiagramObject> dol = new ArrayList<DiagramObject>();
	private Map<String, ArrayList<String>> inputtedModels = new HashMap<String, ArrayList<String>>();
	
	public ModelDiagram(Map<String, ArrayList<String>> input) {
		this.inputtedModels = input;
	}

	public ArrayList<DiagramObject> getDiagramObjects() {
		return dol;
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
