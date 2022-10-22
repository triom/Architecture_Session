package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionSQL extends SqlUtils {
	private UESQL ue_sql = new UESQL();
	private ClasseSQL cl_sql = new ClasseSQL();
	private CreneauSQL creneau_sql = new CreneauSQL();
	
	public void save(Session session) throws SQLException {
			this.connect(); 
			for (Creneau creneau : session.getCreneaux()) {
				this.requestUpdate(String.format("INSERT INTO Session VALUES('%s','%s','%s')", 26,
						session.getUe().getId(), creneau.getIdCreneau()));
				this.selectAll();
			}
			this.disconnect();
	}
	
	public void delete(Session session) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM Session WHERE id='%s'", session.getId()));
		this.selectAll();
		this.disconnect();
	}
	
	public Session getSessionById(int id) {
		this.connect();
		ResultSet set = this.requestSelect(String.format("SELECT * FROM Session WHERE id='%s'", id));
		
		try {
			UE ue = this.ue_sql.getUEById(set.getInt("ID_UE"));
			Classe classe = null;
			Creneau creneau = null;
			Session session = new Session(classe,ue,creneau);
			this.disconnect();
			return session;
		} catch (SQLException e) {
			e.printStackTrace();
			this.disconnect();
			return null;
		}
	}
	
	public ArrayList<Session> getAllSessions() {
		ArrayList<Session> sessions = new ArrayList<Session>();
		this.connect();
		ResultSet set = this.requestSelect(String.format("SELECT * FROM Session"));
		try {
			while (set.next()) {
				Classe classe = this.cl_sql.getById(set.getInt("ID_classe"));
				UE ue = this.ue_sql.getUEById(set.getInt("ID_UE"));
				Creneau creneau = this.creneau_sql.getCreneauById(set.getInt("ID_creneau"));
				System.out.println("okkk="+set.getInt("ID_UE"));
				sessions.add(new Session(classe,ue,creneau));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.selectAll();
		this.disconnect();
		return sessions;
	}
}
