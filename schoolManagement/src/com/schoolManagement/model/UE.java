package com.schoolManagement.model;

public class UE {

	private String id;
	private String code;
	private String intitule;

	public UE(String id, String code, String intitule) {
		this.id = id;
		this.code=code;
		this.intitule = intitule;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
