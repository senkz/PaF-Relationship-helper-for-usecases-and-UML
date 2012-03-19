package model;

import java.util.ArrayList;
import java.util.Map;


public class UMLClass implements DiagramObject{
	private String naam;
	
	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	public Map<String, String> getAdditionalInfo() {
		return null;
	}

	public ArrayList<DiagramObject> getRelatedObjects() {
		return null;
	}
	
	public void draw() {
		// TODO 
	}
	
	public void setNaam(String nm) {
		this.naam = nm;
	}
	
	public String toString() {
		return naam;
	}
}