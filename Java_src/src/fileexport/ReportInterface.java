package fileexport;

import javax.swing.JPanel;

import model.ModelDiagram;

public interface ReportInterface {
	JPanel createReport(ModelDiagram md);
}
