package com.cm.manage.model.system;



public class SyroleSymenu implements java.io.Serializable {

	private static final long serialVersionUID = -4808053475433753077L;
	private String id;
	private Syrole syrole;
	private Symenu symenu;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Syrole getSyrole() {
		return syrole;
	}
	public void setSyrole(Syrole syrole) {
		this.syrole = syrole;
	}
	public Symenu getSymenu() {
		return symenu;
	}
	public void setSymenu(Symenu symenu) {
		this.symenu = symenu;
	}
}