package com.cm.manage.model.system;


public class SyroleSyresources implements java.io.Serializable {

	private static final long serialVersionUID = 1160479801923125773L;
	private String id;
	private Syrole syrole;
	private Syresources syresources;
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
	public Syresources getSyresources() {
		return syresources;
	}
	public void setSyresources(Syresources syresources) {
		this.syresources = syresources;
	}
}