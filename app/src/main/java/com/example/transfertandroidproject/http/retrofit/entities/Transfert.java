package com.example.transfertandroidproject.http.retrofit.entities;

public class Transfert {
    private int number;
    private String matricule;
    private String type;
    private int montant;
    private String date;
    private User emetteur;
    private User recepteur;

    @Override
    public String toString() {
        return "Transfert{" +
                "number=" + number +
                ", matricule='" + matricule + '\'' +
                ", type='" + type + '\'' +
                ", montant=" + montant +
                ", date='" + date + '\'' +
                ", emetteur=" + emetteur +
                ", recepteur=" + recepteur +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(User emetteur) {
        this.emetteur = emetteur;
    }

    public User getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(User recepteur) {
        this.recepteur = recepteur;
    }
}
