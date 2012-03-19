package controller;
import javax.swing.JPanel;

import model.ModelDiagram;
import model.UMLCRUD;
import model.UMLClass;
import model.UMLUsecase;
import fileexport.OutputWriter;
import fileexport.ReportGenerator;
import fileimport.ReaderFactory;


public class GUIController {
	DataController dc;
	ReportGenerator rg = new ReportGenerator();
	OutputWriter ow = new OutputWriter();
	ReaderFactory rf = new ReaderFactory();
	
	public GUIController() {
		dc =  DataController.getInstance();
	}
	
	public JPanel generateReport(String reportType) {
		return rg.generateReport(reportType);
	}
	
	public void read(String fp) {
		dc.addModel(rf.read(fp));
	}
	
	public void write(String fp, String form) {
		ow.write(fp, form);
	}
	
	public ModelDiagram getModel() {
		return dc.getModel();
	}
	
	public void addCrud (UMLUsecase uc, UMLCRUD crud) {
		dc.addCrud(uc, crud);
	}
	
	public UMLCRUD getCrud(UMLClass uclass, UMLUsecase usecase) {
		return dc.getCrud(uclass, usecase);
	}
	
}
