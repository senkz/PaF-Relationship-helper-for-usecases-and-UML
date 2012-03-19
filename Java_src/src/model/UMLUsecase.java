package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;


public class UMLUsecase implements DiagramObject{
	private String naam;
	private ArrayList<DiagramObject> relatedobjectlist = new ArrayList<DiagramObject>();
	
	public void addCRUD(UMLCRUD crud) {
		for (DiagramObject dol : relatedobjectlist) {
			if (dol instanceof UMLCRUD) {
				if(crud.getClassName().equals(((UMLCRUD) dol).getClassName())) {
					relatedobjectlist.remove(dol);
					relatedobjectlist.add(crud);
					return;
				}
			}
		}
		relatedobjectlist.add(crud);
	}
	
	public UMLCRUD getCrud(UMLClass uclass) {
		for (DiagramObject dol : relatedobjectlist) {
			if (dol instanceof UMLCRUD) {
				if(uclass.getNaam().equals(((UMLCRUD) dol).getClassName())) {
					return (UMLCRUD) dol;
				}
			}
		}
		return null;
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

	public void draw(Graphics g, int w, int h, int x, int y) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, w, h);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, w, h);
		g.drawString(getNaam(), x+15, y+(h/2));
	}	
	
	public void setNaam(String nm) {
		this.naam = nm;
	}
	
	public String toString() {
		return naam;
	}
}