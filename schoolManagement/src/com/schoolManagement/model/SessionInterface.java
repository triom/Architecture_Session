package com.schoolManagement.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionInterface {
	
	// UE
	public void createUE(int id, String code, String intitule) throws SQLException;
	public void deleteUE(int id);
	public String getUE();
	public ArrayList<UE> listUEs();
	
	// Classe
	public void createClasse(int classeid, String section, int promotion) throws SQLException;
	public void deleteClasse(int classeid);
	public Classe getClasse(int classeid);
	public Classe[] getAllClasses();

	// Session
	void createSession(int id_ue, int id_classe, ArrayList<Integer> ids_creneaux) throws SQLException;
	public void deleteSession(int id);
	public ArrayList<Session> listSession();
	public void setSession(String debut, String fin, String jour, int id_session);
	
	//creneau
	public void createCreneau(int idCreneau, String debut, String fin, String jour) throws SQLException;
	public void deleteCreneau(int idCreneau);
	public String getCreneau();
	public ArrayList<Creneau>  listCreneau();
