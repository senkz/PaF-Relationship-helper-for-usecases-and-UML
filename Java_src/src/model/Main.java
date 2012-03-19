package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.DataController;
import fileimport.XMLReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		XMLReader reader = new XMLReader();
	    DataController dc = DataController.getInstance();
	    dc.addModel(new ModelDiagram(reader.read("../xml/project.xml")));
	    ModelDiagram md = dc.getModel();
	    ArrayList<DiagramObject> dol = md.getDiagramObjects();
	    for(DiagramObject dio : dol) {
	    	System.out.println("[" + dio.getType() + " " + dio.getNaam() + "]");
	    }
    	System.out.println("-----------------------------------------------");
	    
	    JFrame jf = new JFrame();
	    jf.setLayout(new BorderLayout());
	    jf.getContentPane().setBackground(Color.WHITE);
		jf.setSize(800,800);
		jf.setPreferredSize(new Dimension(400,400));
		jf.setVisible(true);
		
	    JPanel jp = new JPanel();
	    jp.setLayout(new BorderLayout());
		jp.setPreferredSize(new Dimension(400,400));
		jp.setBackground(Color.WHITE);
		jp.setForeground(Color.BLACK);
		
	    jf.add(jp, BorderLayout.CENTER);
	    md.draw(jp);
	}
}
