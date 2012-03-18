package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UMLCRUD implements DiagramObject{
	private boolean create;
	private boolean delete;
	private boolean read;
	private boolean update;
	private UMLClass umlclass;
	
	public UMLCRUD(UMLClass cl) {
		this.umlclass = cl;
	}
	
	public String getType() {
		return this.getClass().getSimpleName();
	}

	public String getNaam() {
		return null;
	}

	public Map<String, String> getAdditionalInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("class", getClassName());
		map.put("create", "" + getCreate());
		map.put("read", "" + getRead());
		map.put("update", "" + getUpdate());
		map.put("delete", "" + getDelete());
		return map;
	}

	public ArrayList<DiagramObject> getRelatedObjects() {
		return null;
	}
	
	public void draw() {
		// TODO
	}
	
	public String getClassName() {
		return umlclass.getNaam();
	}

	public boolean getCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public boolean getDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean getRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean getUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
}
