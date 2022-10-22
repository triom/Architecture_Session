package com.schoolManagement.model;

import java.util.ArrayList;

public class Session extends SqlUtils {
	private int id;
	private Classe classe;
	private UE ue;
	private ArrayList<Creneau> creneaux;

	public Session(Classe classe, UE ue, Creneau creneau) {
		this.setClasse(classe);
		this.setUe(ue);
		this.creneaux.add(creneau);
	}

	public Session(Classe classe, UE ue, ArrayList<Creneau> creneaux) {
		this.setClasse(classe);
		this.setUe(ue);
		this.creneaux = creneaux;
	}
	
	public ArrayList<Creneau> getCreneaux() {
		return this.creneaux;
	}

	public void setCreneau(Creneau creneau, int indexCreneau) {
		this.creneaux.set(indexCreneau, creneau);	
	}

	public void addCreneau(Creneau creneau) {
		this.creneaux.add(creneau);
	}

	public void removeCreneau(int indexCreneau) {
		this.creneaux.remove(indexCreneau);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	} 
}