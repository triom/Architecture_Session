package com.schoolManagement.model;

import java.sql.SQLException;

public interface SessionInterface {
	public void createUE(int id, String code, String intitule) throws SQLException;
	public void deleteUE(int id);
	public String getUE();
	public String listEU();
	public String createSession();
	public String deleteSession();
	public String getSession();
	public String listSession();
	public String setSession();
}
