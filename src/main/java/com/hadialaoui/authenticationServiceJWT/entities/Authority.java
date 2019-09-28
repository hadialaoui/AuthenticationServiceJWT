package com.hadialaoui.authenticationServiceJWT.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authorities")
public class Authority {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String authority;
	
	
	public Authority() {
	}
	public Authority(String authority) {
		super();
		this.authority = authority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
