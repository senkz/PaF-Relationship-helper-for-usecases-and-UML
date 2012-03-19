package model;

import java.util.ArrayList;
import java.util.Map;

public interface DiagramObject {
	String getType();
	String getNaam();
	void setNaam(String nm);
	Map<String, String> getAdditionalInfo();
	ArrayList<DiagramObject> getRelatedObjects();
	void draw(int w, int h, int x, int y);
}
