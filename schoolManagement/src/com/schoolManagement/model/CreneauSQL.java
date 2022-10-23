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
	
	public Creneau getCreneauById(int findId) {
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM Creneau WHERE idCreneau='%s'", findId));
		
		try {
		Creneau creneau = new Creneau(set.getInt("idCreneau"),set.getString("debut"),set.getString("fin"),set.getString("jour"));
			sql.disconnect();
			return creneau;
		} catch (SQLException e) {
			sql.disconnect();
			return null;
		}
	}

	public Creneau getCreneau(String debut, String fin, String jour) {
		SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT * FROM Creneau WHERE debut='%s' and fin='%s' and jour='%s'", debut, fin, jour));
		
		try {
		Creneau creneau = new Creneau(set.getInt("idCreneau"),set.getString("debut"),set.getString("fin"),set.getString("jour"));
			sql.disconnect();
			return creneau;
		} catch (SQLException e) {
			sql.disconnect();
			return null;
		}
	}
}
