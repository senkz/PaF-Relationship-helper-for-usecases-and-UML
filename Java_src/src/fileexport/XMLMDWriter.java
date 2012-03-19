package fileexport;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

import model.DiagramObject;
import model.ModelDiagram;
import model.UMLCRUD;
import model.UMLClass;
import model.UMLUsecase;

public class XMLMDWriter implements OutputInterface{

	public void write(String fn, ModelDiagram md) {
		ArrayList<DiagramObject> dol = md.getDiagramObjects();
		
		try {
			FileWriter fwriter = new FileWriter(fn + ".xmlmd");
			BufferedWriter out = new BufferedWriter(fwriter);
			
			out.write("<XMLMD>" + System.getProperty( "line.separator" ));
			
			for(DiagramObject dio : dol) {
				ArrayList<String> doTransformedlist = transformDiagramObjectToArray(dio, true);
				for(String s : doTransformedlist) {
					out.write(s + System.getProperty( "line.separator" ));
				}
			}
			
			out.write("</XMLMD>");
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private ArrayList<String> transformDiagramObjectToArray(DiagramObject dio, boolean includeRelatedObjects) {
		ArrayList<String> list = new ArrayList<String>();
		
		String type = dio.getType();
		String name = dio.getNaam();
		Map<String, String> infomap = dio.getAdditionalInfo();
		ArrayList<DiagramObject> rolist = dio.getRelatedObjects();
		
		list.add("<" + type + ">");
		if(!name.isEmpty()) {
			list.add("  <name>" + name + "</name>");
		}
		if(infomap != null) {
			for (Map.Entry<String, String> entry : infomap.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    list.add("  <" + key + ">" + value + "</" + key + ">");
			}
		}
		if(rolist != null) {
			for(DiagramObject ro : rolist) {
				ArrayList<String> roTransformedlist = transformDiagramObjectToArray(ro, false);
				for(String s : roTransformedlist) {
					
					list.add("  " + s);
				}
			}
		}
		list.add("</" + type + ">");
		
		return list;
	}
	
	//test met modeldiagram ingevuld om te kijken of de writer werkt
	public static void main(String [ ] args)
	{
	      ModelDiagram md = new ModelDiagram();
	      UMLClass umlc = new UMLClass();
	      umlc.setNaam("Class 1");
	      md.addDiagramObject(umlc);
	      umlc = new UMLClass();
	      umlc.setNaam("Class 2");
	      md.addDiagramObject(umlc);
	      umlc = new UMLClass();
	      umlc.setNaam("Class 3");
	      md.addDiagramObject(umlc);
	      
	      UMLUsecase umluc = new UMLUsecase();
	      umluc.setNaam("Usecase 1");
	      UMLCRUD umlcrud = new UMLCRUD(umlc);
	      umlcrud.setCreate(true);
	      umlcrud.setRead(false);
	      umlcrud.setUpdate(true);
	      umlcrud.setDelete(true);
	      umluc.addCRUD(umlcrud);
	      md.addDiagramObject(umluc);
	      
	      umluc = new UMLUsecase();
	      umluc.setNaam("Usecase 2");
	      umlcrud = new UMLCRUD(umlc);
	      umlcrud.setCreate(true);
	      umlcrud.setRead(false);
	      umlcrud.setUpdate(false);
	      umlcrud.setDelete(false);
	      umluc.addCRUD(umlcrud);
	      md.addDiagramObject(umluc);
	      
	      umluc = new UMLUsecase();
	      umluc.setNaam("Usecase 3");
	      md.addDiagramObject(umluc);
	      
	      umluc = new UMLUsecase();
	      umluc.setNaam("Usecase 4");
	      md.addDiagramObject(umluc);
	      
	      XMLMDWriter writer = new XMLMDWriter();
	      writer.write("test", md);
	}
}
