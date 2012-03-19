package fileexport;

import javax.swing.JPanel;

import model.ModelDiagram;

import controller.DataController;

public class ReportGenerator {
	DataController dc;
	
	public ReportGenerator() {
		dc = DataController.getInstance();
	}
	
	public JPanel generateReport(String type) {
		ModelDiagram md = dc.getModel();
		JPanel jp = null;
		
		if(type.equalsIgnoreCase("crud")) {
			CRUDReport cr = new CRUDReport();
			jp = cr.createReport(md);
		}
		
		return jp;
	}
}
