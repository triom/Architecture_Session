package com.schoolManagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionImplementation implements SessionInterface{

	public void initDatabase() throws ClassNotFoundException {
		Connection conn = null;
		String url = "jdbc:sqlite:data.db";
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		String sql = "CREATE TABLE IF NOT EXISTS UniteEnseignement(ID TEXT PRIMARY KEY,code TEXT,intitule TEXT)";
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("Ue table created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void createUE(String id, String code, String intitule) {
		UE ue = new UE(id, code, intitule);	
		ue.save();
		System.out.println("Ue created.");
	}

	@Override
	public String deleteEU() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listEU() {
		// TODO Auto-generated method stub
		return null;
	}

}
