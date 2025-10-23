package com.example.crud_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MateriasCRUD {

    private DBHelper DBConex;
    private static final String NOMBRE_TABLA = "materias";

    public MateriasCRUD(Context context) {
        DBConex = new DBHelper(context);
    }
    public long nuevaMateria(Materia materia) {
        SQLiteDatabase baseDeDatos = DBConex.getWritableDatabase();

        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("materia", materia.getMateria());
        valoresParaInsertar.put("creditos", materia.getCreditos());

        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }
}