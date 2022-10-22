package com.schoolManagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionImplementation implements SessionInterface{

	public void initDatabase() throws ClassNotFoundException {
		Connection conn=null;
		String url = "jdbc:sqlite:data.db";
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		String sql = "CREATE TABLE IF NOT EXISTS UniteEnseignement(ID INTEGER,code TEXT,intitule TEXT)";
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
	public void createUE(int id,String code, String intitule) throws SQLException {
		UE ue = new UE(id, code, intitule);	
		ue.save();
		System.out.println("Ue created.");
	}

	@Override
	public void deleteUE(int id) {
			UE.getById(id).delete();
			System.out.println("Ue deleted.");
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

	@Override
	public String createSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setSession() {
		// TODO Auto-generated method stub
		return null;
	}

}
