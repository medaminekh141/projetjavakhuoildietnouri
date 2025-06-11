package org.example.apiculture1.models;

public class Fermier {

    private int id;
    private String nom;

    public Fermier(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
