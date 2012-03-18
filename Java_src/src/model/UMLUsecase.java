package model;

import java.util.ArrayList;
import java.util.Map;


public class UMLUsecase implements DiagramObject{
	private String naam;
	private ArrayList<DiagramObject> relatedobjectlist = new ArrayList<DiagramObject>();
	
	public void addCRUD(UMLCRUD crud) {
		relatedobjectlist.add(crud);
	}
	
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
		return relatedobjectlist;
	}

	public void draw() {
		// TODO 
	}	
	
	public void setNaam(String nm) {
		this.naam = nm;
	}
}