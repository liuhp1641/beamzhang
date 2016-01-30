package com.cm.manage.model.system;


public class SyuserSyrole implements java.io.Serializable {

	private static final long serialVersionUID = 9076709967748685172L;
	private String id;
	private Syrole syrole;
	private Syuser syuser;
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
	public Syuser getSyuser() {
		return syuser;
	}
	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
	}
}