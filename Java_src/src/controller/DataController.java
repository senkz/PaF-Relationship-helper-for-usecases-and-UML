package controller;
import java.util.ArrayList;

import model.ModelDiagram;


public class DataController {
	private static DataController instance = null;
	private ArrayList<ModelDiagram> mdl = new ArrayList<ModelDiagram>();
	
	private DataController() {}
	
	public static DataController getInstance() {
		if(instance == null)
			instance = new DataController();
		return instance;
	}
	
	public void addModel(ModelDiagram md) {
		mdl.add(md);
	}
	
	public ModelDiagram getModel() {
		return getModel(this.getVersie()-1);
	}
	
	public ModelDiagram getModel(int i) {
		for(ModelDiagram m : mdl)
			if(m.getVersie() == i)
				return m;
		return null;
	}
	
	public int getVersie() {
		if(mdl.size() == 0)
			return 1;
		int i = 0;
		for(ModelDiagram m : mdl)
			if(m.getVersie() > i)
				i = m.getVersie()+1;
		return i;
	}
}
