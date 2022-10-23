package com.schoolManagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionImplementation implements SessionInterface {

	private UESQL ue_sql;
	private SessionSQL session_sql;
	private ClasseSQL cl_sql;
	private CreneauSQL creneau_sql;

	public void initDatabase() throws ClassNotFoundException {
		this.ue_sql = new UESQL();
		this.session_sql = new SessionSQL();
		this.creneau_sql = new CreneauSQL();
		this.cl_sql = new ClasseSQL();
		
		Connection conn=null;
		String url = "jdbc:sqlite:data.db";
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		String sql = "CREATE TABLE IF NOT EXISTS UniteEnseignement(ID INTEGER,code TEXT,intitule TEXT)";
		String sqlSession = "CREATE TABLE IF NOT EXISTS Session(ID_session INTEGER PRIMARY KEY AUTOINCREMENT,ID_classe INTEGER,ID_UE INTEGER,ID_creneau INTEGER)";
		String sqlClasse = "CREATE TABLE IF NOT EXISTS Classe(ClasseId INTEGER,section TEXT,promotion INTEGER)";
		String sqlCreneau = "CREATE TABLE IF NOT EXISTS Creneau(idCreneau INTEGER,debut TEXT,fin TEXT,jour TEXT)";
		
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("Ue table created");
			stmt.execute(sqlSession);
			System.out.println("Session table created");
			stmt.execute(sqlClasse);
			System.out.println("Classe table created");
			stmt.execute(sqlCreneau);
			System.out.println("Creneau table created");
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
	public ArrayList<UE> listUEs() {
		ArrayList<UE> ues = this.ue_sql.getAllUEs();
		return ues;
	}

	@Override
	public void createSession(int id_classe, int id_ue, ArrayList<Integer> ids_creneaux) throws SQLException {
		UE ue = this.ue_sql.getUEById(id_ue);
		System.out.println("verif"+id_classe);
		Classe classe = this.cl_sql.getById(id_classe);
		
		ArrayList<Creneau> creneaux = new ArrayList<Creneau>();
//		for (int id : ids_creneaux) {
//			creneaux.add(this.creneau_sql.getCreneauById(id));
//		}
		creneaux.add(new Creneau(76, "deb", "fin", "j"));
			
		Session session = new Session(classe, ue, creneaux);	
		this.session_sql.save(session);
		System.out.println("Session created.");
	}

	@Override
	public void deleteSession(int id_session) {
		this.session_sql.delete(id_session);
		System.out.println("Session deleted.");
	}

	@Override
	public ArrayList<Session> listSession() {
		ArrayList<Session> sessions = this.session_sql.getAllSessions();
		return sessions;
	}

	@Override
	public void setSession(String debut, String fin, String jour, int id_session) {
		Creneau creneau = this.creneau_sql.getCreneau(debut, fin, jour);
		this.session_sql.update(id_session, creneau.getIdCreneau());
		System.out.println("Session updated.");
	}
	
	@Override
	public void createClasse(int classeid, String section, int promotion) throws SQLException {
		Classe cl = new Classe(classeid, section, promotion);	
		this.cl_sql.save(cl);
		System.out.println("Classe created.");
	}

	@Override
	public void deleteClasse(int classeid) {
		Classe cl= this.cl_sql.getById(classeid);
		this.cl_sql.delete(cl);
		System.out.println("Classe deleted.");
	}
	@Override
	public Classe getClasse(int classeid) {
		// TODO Auto-generated method stub
		return this.cl_sql.getById(classeid);
	}
	
	@Override
	public void createCreneau(int idCreneau,String debut, String fin, String jour) throws SQLException {
		Creneau creneau= new Creneau(idCreneau, debut, fin, jour);	
		this.creneau_sql.save(creneau);
		System.out.println("Creneau created.");
	}

	@Override
	public void deleteCreneau(int idCreneau) {
		Creneau creneau = this.creneau_sql.getCreneauById(idCreneau);
		this.creneau_sql .delete(creneau);
		System.out.println("Creneau deleted.");
	}
	@Override
	public String getCreneau() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String listCreneau() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
