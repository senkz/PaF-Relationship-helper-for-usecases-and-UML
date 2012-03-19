package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.DataController;

public class ModelDiagram {
	int versie;
	private ArrayList<DiagramObject> dol = new ArrayList<DiagramObject>();
	
	public ModelDiagram(Map<String, ArrayList<String>> input) {
		DataController dc = DataController.getInstance();
		this.setVersie(dc.getVersie());

	    Iterator it = input.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        
	        if(pairs.getKey().equals("usecases")) {
	        	ArrayList<String> str = (ArrayList<String>)pairs.getValue();
	        	for(String s : str) {
		        	DiagramObject dio = new UMLUsecase();
		        	dio.setNaam(s);
		        	this.addDiagramObject(dio);
	        	}
	        }
	        
	        if(pairs.getKey().equals("classes")) {
	        	ArrayList<String> str = (ArrayList<String>)pairs.getValue();
	        	for(String s : str) {
		        	DiagramObject dio = new UMLClass();
		        	dio.setNaam(s);
		        	this.addDiagramObject(dio);
	        	}
	        }

	        it.remove();
	    }
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
	
	public void draw(JPanel jp) {
		Graphics g = jp.getGraphics();
		
		ArrayList<String> objectTypes = new ArrayList<String>();
		
		for(DiagramObject dio : dol) {
			boolean matched = false;
			for(String s : objectTypes) {
				if(dio.getType().equals(s)) {
					matched = true;
					break;
				}
			}
			if(!matched)
				objectTypes.add(dio.getType());
		}
		
		int sizeWidth = (400/objectTypes.size()) > 150 ? 150 : (400/objectTypes.size());
		int sizeHeight = 80;	    
	    
	    ArrayList<DiagramObject> lUc = new ArrayList<DiagramObject>();
	    ArrayList<DiagramObject> lCl = new ArrayList<DiagramObject>();
	    for(DiagramObject dio : dol) {
	    	if(dio.getType().equals("UMLClass"))
	    		lCl.add(dio);
	    	if(dio.getType().equals("UMLUsecase"))
	    		lUc.add(dio);
	    }
	    

		Iterator<String> it = objectTypes.iterator();
		int num = 0;
		int numCl = 0;
		int numUc = 0;
	    while (it.hasNext()) {
	        String s = it.next();
	        g.drawString(s, (num*sizeWidth)+15, (sizeHeight/2));
	        
	        if(s.equals("UMLClass")) {
	        	for(DiagramObject dio : lCl) {
	        		dio.draw(g, (sizeWidth-15), (sizeHeight-5), (num*(sizeWidth+20)), (++numCl*(sizeHeight)));
	        	}
	        }
	        
	        if(s.equals("UMLUsecase")) {
	        	for(DiagramObject dio : lUc) {
	        		dio.draw(g, (sizeWidth-15), (sizeHeight-5), (num*(sizeWidth+20)), (++numUc*(sizeHeight)));
	        	}
	        }
	        
	        num++;
	        it.remove();
	    }
	    
	}
}