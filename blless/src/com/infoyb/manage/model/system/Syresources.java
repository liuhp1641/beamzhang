package com.cm.manage.model.system;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;



public class Syresources implements java.io.Serializable {

	private static final long serialVersionUID = 1530696719234718111L;

	private String id;
	private Syresources syresources;
	private String text;
	private BigDecimal seq;
	private String src;
	private String descript;
	private String onoff;
	private Set<SyroleSyresources> syroleSyresourceses = new HashSet<SyroleSyresources>(0);
	private Set<Syresources> syresourceses = new HashSet<Syresources>(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Syresources getSyresources() {
		return syresources;
	}
	public void setSyresources(Syresources syresources) {
		this.syresources = syresources;
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
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getOnoff() {
		return onoff;
	}
	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
	public Set<SyroleSyresources> getSyroleSyresourceses() {
		return syroleSyresourceses;
	}
	public void setSyroleSyresourceses(Set<SyroleSyresources> syroleSyresourceses) {
		this.syroleSyresourceses = syroleSyresourceses;
	}
	public Set<Syresources> getSyresourceses() {
		return syresourceses;
	}
	public void setSyresourceses(Set<Syresources> syresourceses) {
		this.syresourceses = syresourceses;
	}
}