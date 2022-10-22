package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionSQL extends SqlUtils {
	public void save(Session session) throws SQLException {
			this.connect(); 
			this.requestUpdate(String.format("INSERT INTO Session VALUES('%s','%s','%s')", session.getClasse(),
					session.getUe(), session.getCreneaux()));
			this.selectAll();
			this.disconnect();
	}
	
	public void delete(Session session) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM Session WHERE id='%s'", session.getId()));
		this.selectAll();
		this.disconnect();
	}
	
	public Session getSessionById(int id) {
//		SqlUtils sql = new SqlUtils();
//		sql.connect();
//		ResultSet set = sql.requestSelect(String.format("SELECT * FROM Session WHERE id='%s'", id));
//		
//		try {
//			Session session = new Session(set.getInt("id"),set.getString("code"),
//					set.getString("intitule"));
//			sql.disconnect();
//			return session;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			sql.disconnect();
//			return null;
//		}
		return null;
	}
}
