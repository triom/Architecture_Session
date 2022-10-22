package com.schoolManagement.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionInterface {
	
	// UE
	public void createUE(int id, String code, String intitule) throws SQLException;
	public void deleteUE(int id);
	
	public void createClasse(int classeid, String section, int promotion) throws SQLException;
	public void deleteClasse(int classeid);
	public Classe getClasse(int classeid);
	
	public String getUE();
	public String listEU();
	
	// Session
	void createSession(int id_ue, int id_classe, ArrayList<Integer> ids_creneaux) throws SQLException;
	public void deleteSession(int id);
	public Session getSession();
	public String listSession();
	public String setSession();
}
