package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UE extends SqlUtils {

	private String id;
	private String code;
	private String intitule;

	public UE(String id, String code, String intitule) {
		this.id = id;
		this.code=code;
		this.intitule = intitule;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public void save() {
		this.connect(); 
		this.requestUpdate(String.format("INSERT INTO UniteEnseignement VALUES('%s','%s','%s')", this.id,
				this.code, this.intitule));
		this.selectAll();
		this.disconnect();
	}
	
	public void delete() {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM UniteEnseignement WHERE id='%s'", this.id));
		this.selectAll();
		this.disconnect();

	}
	
	public static UE getById(String id) {
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM UniteEnseignement WHERE id='%s'", id));
		
		try {
			UE ue = new UE(set.getString("id"), set.getString("code"),
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
