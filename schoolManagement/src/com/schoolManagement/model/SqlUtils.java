package com.schoolManagement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
	private Connection conn = null;
	private String url = "jdbc:sqlite:data.db";

	protected void connect() {
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected ResultSet requestSelect(String sql) {
		ResultSet rs = null;
		try {
			// conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	protected int requestUpdate(String sql) {
		int rs = 0;
		try {
			// conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	protected void selectAll(){
	        String sql = "SELECT ID, code, intitule FROM UniteEnseignement";
	        
	        try (Statement stmt  = conn.createStatement(); ResultSet rs    = stmt.executeQuery(sql))
	        {
	            while (rs.next()) 
	            {
	                System.out.println(rs.getInt("ID") +  "\t" + rs.getString("code") + "\t" + rs.getString("intitule"));
	            }
	        } 
	        catch (SQLException e) 
	        {
	            System.out.println(e.getMessage());
	        }
	}
	
	protected void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
