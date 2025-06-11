package org.example.apiculture1.models;

public class Ruche {
    private int id;
    private String code;
    private int fermeId; // Relation 1-N avec une Ferme

    // Constructeurs
    public Ruche() {
    }

    public Ruche(int id, String code, int fermeId) {
        this.id = id;
        this.code = code;
        this.fermeId = fermeId;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFermeId() {
        return fermeId;
    }

    public void setFermeId(int fermeId) {
        this.fermeId = fermeId;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Ruche{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", fermeId=" + fermeId +
                '}';
    }
}