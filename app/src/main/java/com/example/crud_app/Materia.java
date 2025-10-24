package com.example.crud_app;

public class Materia {
    private long id;
    private String materia;
    private int creditos;

    public Materia(String materia, int creditos) {
        this.materia = materia;
        this.creditos = creditos;
    }

    public Materia(String materia, int creditos, long id) {
        this.materia = materia;
        this.creditos = creditos;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getMateria() {
        return materia;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
