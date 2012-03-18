package model;

import java.util.ArrayList;

import controller.DataController;

public class ModelDiagram {
	int versie;
	private ArrayList<DiagramObject> dol = new ArrayList<DiagramObject>();
	
	
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
