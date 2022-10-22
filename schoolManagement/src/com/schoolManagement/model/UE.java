package com.schoolManagement.model;

public class UE {

	private int id;
	private String code;
	private String intitule;

	public UE(int id,String code, String intitule) {
		this.id = id;
		this.code=code;
		this.intitule = intitule;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
