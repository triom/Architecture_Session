package com.schoolManagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionImplementation implements SessionInterface {

	private UESQL ue_sql;
	private SessionSQL session_sql;

	public void initDatabase() throws ClassNotFoundException {
		this.ue_sql = new UESQL();
		this.session_sql = new SessionSQL();
		
		Connection conn=null;
		String url = "jdbc:sqlite:data.db";
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		String sql = "CREATE TABLE IF NOT EXISTS UniteEnseignement(ID INTEGER,code TEXT,intitule TEXT)";
		String sqlSession = "CREATE TABLE IF NOT EXISTS Session(ID_UE INTEGER,ID_classe INTEGER,ID_creneau INTEGER)";
		
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("Ue table created");
			stmt.execute(sqlSession);
			System.out.println("Session table created");
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
		this.ue_sql.save(ue);
		System.out.println("Ue created.");
	}

	@Override
	public void deleteUE(int id) {
		UE ue = this.ue_sql.getUEById(id);
		this.ue_sql.delete(ue);
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
	public void createSession(int id_ue, int id_classe, int id_creneau) throws SQLException {
		UE ue = this.ue_sql.getUEById(id_ue);
		Classe classe = null;
		Creneau creneau = null;
		Session session = new Session(classe, ue, creneau);	
		this.session_sql.save(session);
		System.out.println("Session created.");
	}

	@Override
	public void deleteSession(int id) {
		Session session = this.session_sql.getSessionById(id);
		this.session_sql.delete(session);
		System.out.println("Session deleted.");
	}

	@Override
	public Session getSession() {
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
