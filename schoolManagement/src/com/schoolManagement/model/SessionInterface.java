package com.schoolManagement.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionInterface {
	
	// UE
	public void createUE(int id, String code, String intitule) throws SQLException;
	public void deleteUE(int id);
	public String getUE();
	public String listEU();
	
	// Classe
	public void createClasse(int classeid, String section, int promotion) throws SQLException;
	public void deleteClasse(int classeid);
	public Classe getClasse(int classeid);
	
	// Session
	void createSession(int id_ue, int id_classe, ArrayList<String> ids_creneaux) throws SQLException;
	public void deleteSession(int id);
	public ArrayList<Session> listSession();
	public void setSession();
}
