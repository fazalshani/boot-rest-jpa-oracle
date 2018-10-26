package com.mypack.bootrestjpaoracle.model;

import java.util.Date;

public class Users_sample {

	private int id;
	private String name;
	private Date birthdate;
	
	
	public Users_sample(int id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	
}
