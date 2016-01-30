package com.cm.manage.model.system;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;



public class Syrole implements java.io.Serializable {

	private static final long serialVersionUID = -5700154286564244488L;

	private String id;
	private Syrole syrole;
	private String text;
	private BigDecimal seq;
	private String descript;
	private Set<SyuserSyrole> syuserSyroles = new HashSet<SyuserSyrole>(0);
	private Set<Syrole> syroles = new HashSet<Syrole>(0);
	private Set<SyroleSyresources> syroleSyresourceses = new HashSet<SyroleSyresources>(0);
	private Set<SyroleSymenu> syroleSymenu= new HashSet<SyroleSymenu>(0);
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public BigDecimal getSeq() {
		return seq;
	}
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Set<SyuserSyrole> getSyuserSyroles() {
		return syuserSyroles;
	}
	public void setSyuserSyroles(Set<SyuserSyrole> syuserSyroles) {
		this.syuserSyroles = syuserSyroles;
	}
	public Set<Syrole> getSyroles() {
		return syroles;
	}
	public void setSyroles(Set<Syrole> syroles) {
		this.syroles = syroles;
	}
	public Set<SyroleSyresources> getSyroleSyresourceses() {
		return syroleSyresourceses;
	}
	public void setSyroleSyresourceses(Set<SyroleSyresources> syroleSyresourceses) {
		this.syroleSyresourceses = syroleSyresourceses;
	}
	public Set<SyroleSymenu> getSyroleSymenu() {
		return syroleSymenu;
	}
	public void setSyroleSymenu(Set<SyroleSymenu> syroleSymenu) {
		this.syroleSymenu = syroleSymenu;
	}
}