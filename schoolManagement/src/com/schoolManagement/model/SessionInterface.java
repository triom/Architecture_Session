package com.schoolManagement.model;

public interface SessionInterface {
	public void createUE(String id, String code, String intitule);
	public void deleteUE(String id);
	public String getUE();
	public String listEU();
}
