package com.example.crud_app;

public class Materia {

    private String materia;
    private int creditos;

    private long id; // El ID de la BD

    public Materia(String materia, int creditos) {
        this.materia = materia;
        this.creditos = creditos;
    }

    // Constructor para cuando instanciamos desde la BD
    public Materia(String materia, int creditos, long id) {
        this.materia = materia;
        this.creditos = creditos;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "Materia='" + materia + '\'' +
                ", Creditos=" + creditos +
                '}';
    }
}

