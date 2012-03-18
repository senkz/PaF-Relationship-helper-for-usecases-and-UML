package model;

public class UMLCRUD {
	private boolean create;
	private boolean delete;
	private boolean read;
	private boolean update;
	private UMLClass umlclass;
	
	public UMLCRUD(UMLClass cl) {
		this.umlclass = cl;
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
