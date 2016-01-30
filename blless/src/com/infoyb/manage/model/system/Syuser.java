package com.cm.manage.model.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class Syuser implements java.io.Serializable {
	private static final long serialVersionUID = -7917893115870069548L;
	private String id;
	private String name;
	private String password;
	private Date createdatetime;
	private Date modifydatetime;
	private Set<SyuserSyrole> syuserSyroles = new HashSet<SyuserSyrole>(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public Date getModifydatetime() {
		return modifydatetime;
	}
	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	public Set<SyuserSyrole> getSyuserSyroles() {
		return syuserSyroles;
	}
	public void setSyuserSyroles(Set<SyuserSyrole> syuserSyroles) {
		this.syuserSyroles = syuserSyroles;
	}
}