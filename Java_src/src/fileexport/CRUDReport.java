package fileexport;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.DiagramObject;
import model.ModelDiagram;
import model.UMLCRUD;
import model.UMLClass;
import model.UMLUsecase;

public class CRUDReport implements ReportInterface{
	public JPanel createReport(ModelDiagram md) {
		JPanel jp = new JPanel(new GridLayout(10, 2));
		ArrayList<DiagramObject> dol = md.getDiagramObjects();
		
		ArrayList<UMLClass> umlclist = new ArrayList<UMLClass>();
		ArrayList<UMLUsecase> umlulist = new ArrayList<UMLUsecase>();
		ArrayList<UMLCRUD> umlcrudlist = new ArrayList<UMLCRUD>();
		
		for(DiagramObject dio : dol) {
			if(dio.getClass() == UMLClass.class) {
				umlclist.add((UMLClass)dio);
			}
			if(dio.getClass() == UMLUsecase.class) {
				umlulist.add((UMLUsecase)dio);
				
				ArrayList<DiagramObject> rol = dio.getRelatedObjects();
				for(DiagramObject ro : rol) {
					if(dio.getClass() == UMLCRUD.class){
						umlcrudlist.add((UMLCRUD)ro);
					}
				}
			}
		}
		
		for(int i = 0; i < 20; i++) {
			JLabel jl = new JLabel("" + i);
			System.out.println(jl.getText());
			jp.add(jl);
		}
		
		return jp;
	}
	
	public static void main(String [ ] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(400,480);
		frame.setVisible(true);
		
		CRUDReport cr = new CRUDReport();
		ModelDiagram md = new ModelDiagram();
		
		JPanel buttonPannel = cr.createReport(md);
		
		frame.add(buttonPannel);
	}
}
