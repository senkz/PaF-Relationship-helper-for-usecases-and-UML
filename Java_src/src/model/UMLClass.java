package model;

import java.awt.Color;
import java.awt.Graphics;
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
	
	public void draw(Graphics g, int w, int h, int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, w, h);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
		g.drawRect(x, y, w, h-15);
		g.drawString(getNaam(), x+15, y+(h/2));
	}
	
	public void setNaam(String nm) {
		this.naam = nm;
	}
	
	public String toString() {
		return naam;
	}
}