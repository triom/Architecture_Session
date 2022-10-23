package com.schoolManagement.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseSQL extends SqlUtils {
	
	public void save(Classe cl) throws SQLException {
		this.connect(); 
		this.requestUpdate(String.format("INSERT INTO Classe VALUES('%s','%s','%s')", cl.getClasseid(),
				cl.getSection(), cl.getPromotion()));
		this.selectAllClasses();
		this.disconnect();
	}
	
	public void delete(Classe cl) {
		this.connect();
		this.requestUpdate(String.format("DELETE FROM Classe WHERE ClasseId='%s'", cl.getClasseid()));
		this.selectAllClasses();
		this.disconnect();
	}
	
	public Classe getById(int findId) {
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
	
		
	public Classe[] getAllClasses(){
        SqlUtils sql = new SqlUtils();
		sql.connect();
		ResultSet set = sql.requestSelect(String.format("SELECT ClasseId, section, promotion FROM Classe"));
		Classe[] tabCl=new Classe[255];
		int i=0;
		
        try
        {
        	System.out.println("Classe:");
            while (set.next()) 
            {
            	tabCl[i]= new Classe(set.getInt("ClasseId"),set.getString("section"),set.getInt("promotion"));
            	
                System.out.println(set.getInt("ClasseId") +  "\t" + set.getString("section") + "\t" + set.getInt("promotion"));
                
                i++;
            }
            return tabCl;

        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return tabCl;

	}


}
