package com.schoolManagement.model;

public class Classe {

	private int classeid;
	private String section;
	private int promotion;

	public Classe(int classeid,String section, int promotion) {
		this.classeid = classeid;
		this.section=section;
		this.promotion = promotion;
	}
	
	public int getClasseid() {
		return classeid;
	}

	public void setClasseid(int classeid) {
		this.classeid = classeid;
	}
	
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
	
}
