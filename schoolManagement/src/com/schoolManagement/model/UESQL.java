package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UESQL extends SqlUtils {
	public void save(UE ue) throws SQLException {
		this.connect(); 
		this.requestUpdate(String.format("INSERT INTO UniteEnseignement VALUES('%s','%s','%s')", ue.getId(),
				ue.getCode(), ue.getIntitule()));
		this.selectAll();
		this.disconnect();
	}
	
	public void delete(UE ue) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM UniteEnseignement WHERE id='%s'", ue.getId()));
		this.selectAll();
		this.disconnect();
	}
	
	public UE getUEById(int id) {
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM UniteEnseignement WHERE ID='%s'", id));
		
		try {
			UE ue = new UE(set.getInt("ID"),set.getString("code"),
					set.getString("intitule"));
			sql.disconnect();
			return ue;
		} catch (SQLException e) {
			e.printStackTrace();
			sql.disconnect();
			return null;
		}
	}
}
