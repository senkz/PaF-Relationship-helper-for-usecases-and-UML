package fileexport;

import model.ModelDiagram;

public interface OutputInterface {
	void write(String fn, ModelDiagram md);
}
