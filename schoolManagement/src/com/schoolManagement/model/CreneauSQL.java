package com.schoolManagement.model;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreneauSQL extends SqlUtils {
	
	public void save(Creneau creneau) throws SQLException {
		this.connect(); 
		this.requestUpdate(String.format("INSERT INTO Creneau VALUES('%s','%s','%s','%s')", creneau.getIdCreneau(),
				creneau.getDebut(), creneau.getFin(), creneau.getJour()));
		this.selectAllCreneaux();
		this.disconnect();
	}
	
	public void delete(Creneau creneau) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM Creneau WHERE idCreneau='%s'", creneau.getIdCreneau()));
		this.selectAllCreneaux();
		this.disconnect();
	}
	
	

	public Creneau getById(int findId) {
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM Creneau WHERE idCreneau='%s'", findId));
		
		try {
		Creneau creneau = new Creneau(set.getInt("idCreneau"),set.getString("debut"),set.getString("fin"),set.getString("jour"));
			sql.disconnect();
			return creneau;
		} catch (SQLException e) {
		e.printStackTrace();
			sql.disconnect();
			return null;
		}
		
		
	}
