package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;

public interface DiagramObject {
	String getType();
	String getNaam();
	void setNaam(String nm);
	Map<String, String> getAdditionalInfo();
	ArrayList<DiagramObject> getRelatedObjects();
	void draw(Graphics g, int w, int h, int x, int y);
}
