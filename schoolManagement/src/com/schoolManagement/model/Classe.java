package com.schoolManagement.model;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Classe extends SqlUtils {

	private int classeid;
	private String section;
	private int promotion;

	public Classe(int classeid,String section, int promotion) {
		this.classeid = classeid;
		this.section=section;
		this.promotion = promotion;
	}
	
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
	
	public void save() throws SQLException {
		this.connect(); 
		this.requestUpdate(String.format("INSERT INTO Classe VALUES('%s','%s','%s')", this.classeid,
				this.section, this.promotion));
		this.selectAll();
		this.disconnect();
	}
	
	public void delete() {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM Classe WHERE ClasseId='%s'", this.classeid));
		this.selectAll();
		this.disconnect();
	}
	
	public static Classe getById(int findId) {
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM Classe WHERE ClasseId='%s'", findId));
		
		try {
			Classe cl = new Classe(set.getInt("ClasseId"),set.getString("section"),
					set.getInt("promotion"));
			sql.disconnect();
			return cl;
		} catch (SQLException e) {
			e.printStackTrace();
			sql.disconnect();
			return null;
		}
		
	}
}
