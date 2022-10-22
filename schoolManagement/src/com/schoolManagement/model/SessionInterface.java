package com.schoolManagement.model;

import java.sql.SQLException;

public interface SessionInterface {
	
	// UE
	public void createUE(int id, String code, String intitule) throws SQLException;
	public void deleteUE(int id);
	public String getUE();
	public String listEU();
	
	// Session
	void createSession(int id_ue, int id_classe, int id_creneau) throws SQLException;
	public void deleteSession(int id);
	public Session getSession();
	public String listSession();
	public String setSession();
}
