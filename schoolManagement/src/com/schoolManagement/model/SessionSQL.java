package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionSQL extends SqlUtils {
	private UESQL ue_sql = new UESQL();
	
	public void save(Session session) throws SQLException {
			this.connect(); 
			for (Creneau creneau : session.getCreneaux()) {
				this.requestUpdate(String.format("INSERT INTO Session VALUES('%s','%s','%s')", session.getClasse().getClasseid(),
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
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM Session WHERE id='%s'", id));
		
		try {
			UE ue = this.ue_sql.getUEById(set.getInt("ID_UE"));
			Classe classe = null;
			Creneau creneau = null;
			Session session = new Session(classe,ue,creneau);
			sql.disconnect();
			return session;
		} catch (SQLException e) {
			e.printStackTrace();
			sql.disconnect();
			return null;
		}
	}
}
