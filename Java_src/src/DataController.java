
public class DataController {
	private static DataController instance = null;
	ModelDiagram md;	// mogelijk meerdere diagrammen?
	
	private DataController() {
	}
	
	public DataController getInstance() {
		if(instance == null)
			instance = new DataController();
		return instance;
	}
	
	public ModelDiagram getModel() {
		return null;
	}
}
