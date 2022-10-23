package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UESQL extends SqlUtils {
	public void save(UE ue) throws SQLException {
		this.connect(); 
		this.requestUpdate(String.format("INSERT INTO UniteEnseignement VALUES('%s','%s','%s')", ue.getId(),
				ue.getCode(), ue.getIntitule()));
		this.selectAllUes();
		this.disconnect();
	}
	
	public void delete(UE ue) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM UniteEnseignement WHERE id='%s'", ue.getId()));
		this.selectAllUes();
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
	
	public ArrayList<UE> getAllUEs() {
		ArrayList<UE> ues = new ArrayList<UE>();
		this.connect();
		ResultSet set = this.requestSelect(String.format("SELECT * FROM UniteEnseignement"));
		try {
			while (set.next()) {
				ues.add(new UE(set.getInt("ID"),set.getString("code"),set.getString("intitule")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.disconnect();
		return ues;
	}
}
