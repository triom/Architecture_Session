package com.schoolManagement.model;

public class Creneau {
   private  String idCreneau;
   private  String debut;
   private  String fin;
   private  String jour;

    public Creneau(String idCreneau, String debut, String fin, String jour) {
        this.idCreneau = idCreneau;
        this.debut = debut;
        this.fin = fin;
        this.jour = jour;
    }

    public String getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(String idCreneau) {
        this.idCreneau = idCreneau;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }
   
   
    
    
}
