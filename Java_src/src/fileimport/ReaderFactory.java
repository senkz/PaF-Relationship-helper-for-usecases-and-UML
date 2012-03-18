package fileimport;
import model.ModelDiagram;


public class ReaderFactory {
	public ReaderFactory() {
		
	}
	
	public ModelDiagram read(String fn) {
		XMLReader reader = new XMLReader();
		return new ModelDiagram(reader.read(fn));
	}
}
