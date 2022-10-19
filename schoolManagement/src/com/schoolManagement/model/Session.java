package com.schoolManagement.model;

import java.util.ArrayList;

public class Session {
	private Classe classe;
	private UE ue;
	private ArrayList<Creneau> creneaux;

	public Session(Classe classe, UE ue, Creneau creneau) {
		this.classe = classe;
		this.ue = ue;
		this.creneaux.add(creneau);
	}

	public Session(Classe classe, UE ue, ArrayList<Creneau> creneaux) {
		this.classe = classe;
		this.ue = ue;
		this.creneau = creneaux;
	}

	public void setCreneau(Creneau creneau, int indexCreneau) {
		this.creneaux.set(indexCreneau, creneau);	
	}

	public void addCreneau(Creneau creneau) {
		this.creneaux.add(creneau)
	}

	public void removeCreneau(int indexCreneau) {
		this.creneaux.remove(indexCreneau)
	}
}