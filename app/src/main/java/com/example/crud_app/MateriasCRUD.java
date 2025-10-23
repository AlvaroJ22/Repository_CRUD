package com.example.crud_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MateriasCRUD {

    private DBHelper DBConex;
    private static final String NOMBRE_TABLA = "materias";

    public MateriasCRUD(Context context) {
        DBConex = new DBHelper(context);
    }

    //Method for insert subjects
    public long insertSubjects(Materia materia) {
        SQLiteDatabase baseDeDatos = DBConex.getWritableDatabase();

        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("materia", materia.getMateria());
        valoresParaInsertar.put("creditos", materia.getCreditos());

        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }
    //Method for obtain subjects
    public ArrayList<Materia> obtainSubjects() {
        ArrayList<Materia> materias = new ArrayList<>();

        SQLiteDatabase baseDeDatos = DBConex.getReadableDatabase();

        String[] columnasAConsultar = {"materia", "creditos", "id"};

        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,      // nombre de la tabla
                columnasAConsultar,
                null, null, null, null, null
        );

        if (cursor == null) {
            return materias;
        }

        if (!cursor.moveToFirst()) {
            cursor.close();
            return materias;
        }

        do {
            String nombreMateria = cursor.getString(0);
            int creditos = cursor.getInt(1);
            long id = cursor.getLong(2);

            Materia materiaObtenida = new Materia(nombreMateria, creditos, id);
            materias.add(materiaObtenida);
        } while (cursor.moveToNext());

        cursor.close();
        return materias;
    }

    //Method for eliminate subjects
    public int eliminateSubjects(Materia materia) {
        SQLiteDatabase baseDeDatos = DBConex.getWritableDatabase();

        String[] argumentos = {String.valueOf(materia.getId())};

        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}