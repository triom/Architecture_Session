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
				this.requestUpdate(String.format("INSERT INTO Session (ID_classe, ID_UE, ID_creneau) VALUES('%s','%s','%s')", session.getClasse().getClasseid(),
						session.getUe().getId(), creneau.getIdCreneau()));
				this.selectAllSessions();
			}
			this.disconnect();
	}
	
	public void delete(int id_session) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM Session WHERE ID_session='%s'", id_session));
		this.selectAllSessions();
		this.disconnect();
	}
	
	public Session getSessionById(int id_session) {
		this.connect();
		ResultSet set = this.requestSelect(String.format("SELECT * FROM Session WHERE ID_session='%s'", id_session));
		
		try {
			UE ue = this.ue_sql.getUEById(set.getInt("ID_UE"));
			Classe classe = this.cl_sql.getById(set.getInt("ID_classe"));
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
	
	public void update(int id_session, int id_creneau) {
		this.connect();
		this.requestUpdate(String.format("UPDATE Session SET ID_creneau='%s' WHERE ID_session='%s'", id_creneau, id_session));
		this.selectAllSessions();
		this.disconnect();
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
				sessions.add(new Session(set.getInt("ID_session"), classe,ue,creneau));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.selectAllSessions();
		this.disconnect();
		return sessions;
	}
}