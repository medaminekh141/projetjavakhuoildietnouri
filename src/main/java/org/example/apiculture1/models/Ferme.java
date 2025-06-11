package org.example.apiculture1.models;

public class Ferme {
    // Attributs
    private int id;
    private String localisation;
    private int fermierId; // Clé étrangère vers Fermier

    // Constructeurs
    public Ferme() {} // Constructeur par défaut nécessaire pour JSP/JDBC

    public Ferme(int id, String localisation, int fermierId) {
        this.id = id;
        this.localisation = localisation;
        this.fermierId = fermierId;
    }


    // Getters & Setters (obligatoires pour JSP)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getFermierId() {
        return fermierId;
    }

    public void setFermierId(int fermierId) {
        this.fermierId = fermierId;
    }

    // Méthode toString() pour le débogage
    @Override
    public String toString() {
        return "Ferme{" +
                "id=" + id +
                ", localisation='" + localisation + '\'' +
                ", fermierId=" + fermierId +
                '}';
    }
}