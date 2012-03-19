package fileexport;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.DiagramObject;
import model.ModelDiagram;
import model.UMLCRUD;
import model.UMLClass;
import model.UMLUsecase;

public class CRUDReport implements ReportInterface{
	private ArrayList<UMLClass> umlclist = new ArrayList<UMLClass>();
	private ArrayList<UMLUsecase> umlulist = new ArrayList<UMLUsecase>();
	private ArrayList<UMLCRUD> umlcrudlist = new ArrayList<UMLCRUD>();
	
	public JPanel createReport(ModelDiagram md) {
		umlclist.clear(); umlulist.clear(); umlcrudlist.clear();
		
		JPanel jp = new JPanel(new GridLayout(0, 2));
		ArrayList<DiagramObject> dol = md.getDiagramObjects();
			
		for(DiagramObject dio : dol) {
			if(dio.getClass() == UMLClass.class) {
				umlclist.add((UMLClass)dio);
			}
			if(dio.getClass() == UMLUsecase.class) {
				umlulist.add((UMLUsecase)dio);
				
				ArrayList<DiagramObject> rol = dio.getRelatedObjects();
				for(DiagramObject ro : rol) {
					if(ro.getClass() == UMLCRUD.class){
						umlcrudlist.add((UMLCRUD)ro);
					}
				}
			}
		}
		
		JLabel jl1 = new JLabel("Total Usecases");
		JLabel jl2 = new JLabel("" + umlulist.size());
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Total Classes");
		jl2 = new JLabel("" + umlclist.size());
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Total CRUD Connections");
		jl2 = new JLabel("" + umlcrudlist.size());
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Usecase with most connections");
		jl2 = new JLabel("" + mostConnUsecase());
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Class with most Connections");
		jl2 = new JLabel("" + mostConnClass());
		jp.add(jl1); jp.add(jl2);
		
		int creates = 0, reads = 0, updates = 0, deletes = 0;
		for(UMLCRUD umlcrud : umlcrudlist) {
			if(umlcrud.getCreate())
				creates++;
			if(umlcrud.getRead())
				reads++;
			if(umlcrud.getUpdate())
				updates++;
			if(umlcrud.getDelete())
				deletes++;
		}
		
		jl1 = new JLabel("Total Creates");
		jl2 = new JLabel("" + creates);
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Total Reads");
		jl2 = new JLabel("" + reads);
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Total Updates");
		jl2 = new JLabel("" + updates);
		jp.add(jl1); jp.add(jl2);
		
		jl1 = new JLabel("Total Deletes");
		jl2 = new JLabel("" + deletes);
		jp.add(jl1); jp.add(jl2);
		
		
		return jp;
	}
	
	private String mostConnUsecase() {
		String s = "";
		int high = 0;
		
		for(UMLUsecase umlu : umlulist) {
			ArrayList<DiagramObject> rol = umlu.getRelatedObjects();
			if(rol.size() >= high) {
				int newHigh = 0;
				for(DiagramObject ro : rol) {
					if(ro.getClass() == UMLCRUD.class){
						newHigh++;
					}
				}
				if(newHigh >= high) {
					if(newHigh > high) {
						high = newHigh;
						s = "" + umlu.getNaam();
					} else {
						s = s + ", " + umlu.getNaam();	
					}
				}
			}
		}
		s = s + " (" + high + ")";
		return s;
	}
	
	private String mostConnClass() {
		String s = "";
		int high = 0;

		Map<String, Integer> crudClassMap = new HashMap<String, Integer>();

		for(UMLCRUD umlcrud : umlcrudlist) {
			String sCrud = umlcrud.getClassName();
			int connections = 1;
			if(crudClassMap.containsKey(sCrud)) {
				connections = crudClassMap.get(sCrud);
				connections++;
			}
			crudClassMap.put(sCrud, connections);
		}

		if(crudClassMap != null) {
			for (Map.Entry<String, Integer> entry : crudClassMap.entrySet()) {
				String key = entry.getKey();
				Integer newHigh = entry.getValue();
				if(newHigh >= high) {
					if(newHigh > high) {
						high = newHigh;
						s = "" + key;
					} else {
						s = s + ", " + key;	
					}
				}
			}
		}

		s = s + " (" + high + ")";
		return s;
	}
	
	public static void main(String [ ] args) {
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
		
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(400,480);
		frame.setVisible(true);
		
		CRUDReport cr = new CRUDReport();
		
		JPanel buttonPannel = cr.createReport(md);
		
		frame.add(buttonPannel);
	}
}
