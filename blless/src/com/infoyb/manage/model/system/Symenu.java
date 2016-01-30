package com.cm.manage.model.system;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;



public class Symenu implements java.io.Serializable {
	private static final long serialVersionUID = -3701588674131891005L;
	private String id;
	private Symenu symenu;
	private String text;
	private String iconcls;
	private String src;
	private BigDecimal seq;
	private Set<SyroleSymenu> syroleSymenu = new HashSet<SyroleSymenu>(0);
	private Set<Symenu> symenus = new HashSet<Symenu>(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Symenu getSymenu() {
		return symenu;
	}
	public void setSymenu(Symenu symenu) {
		this.symenu = symenu;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public BigDecimal getSeq() {
		return seq;
	}
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}
	public Set<SyroleSymenu> getSyroleSymenu() {
		return syroleSymenu;
	}
	public void setSyroleSymenu(Set<SyroleSymenu> syroleSymenu) {
		this.syroleSymenu = syroleSymenu;
	}
	public Set<Symenu> getSymenus() {
		return symenus;
	}
	public void setSymenus(Set<Symenu> symenus) {
		this.symenus = symenus;
	}
}