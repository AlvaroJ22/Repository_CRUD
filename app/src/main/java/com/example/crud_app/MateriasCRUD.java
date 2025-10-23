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
    public long insertSubject(Materia materia) {
        SQLiteDatabase baseDeDatos = DBConex.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("materia", materia.getMateria());
        valores.put("creditos", materia.getCreditos());

        long resultado = baseDeDatos.insert(NOMBRE_TABLA, null, valores);
        baseDeDatos.close();
        return resultado;
    }

    //Method for obtain subjects
    public ArrayList<Materia> obtainSubjects() {
        ArrayList<Materia> materias = new ArrayList<>();
        SQLiteDatabase baseDeDatos = DBConex.getReadableDatabase();
        String[] columnas = {"materia", "creditos", "id"};

        Cursor cursor = baseDeDatos.query(NOMBRE_TABLA, columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String nombreMateria = cursor.getString(0);
                int creditos = cursor.getInt(1);
                long id = cursor.getLong(2);
                materias.add(new Materia(nombreMateria, creditos, id));
            } while (cursor.moveToNext());
        }

        cursor.close();
        baseDeDatos.close();
        return materias;
    }

    //Method for update subjects
    public int updateSubject(Materia materiaEditada) {
        SQLiteDatabase baseDeDatos = DBConex.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("materia", materiaEditada.getMateria());
        valores.put("creditos", materiaEditada.getCreditos());

        String where = "id = ?";
        String[] args = {String.valueOf(materiaEditada.getId())};

        int filas = baseDeDatos.update(NOMBRE_TABLA, valores, where, args);
        baseDeDatos.close();
        return filas;
    }

    //Method for eliminate subjects
    public int deleteSubject(Materia materia) {
        SQLiteDatabase baseDeDatos = DBConex.getWritableDatabase();
        String[] args = {String.valueOf(materia.getId())};

        int filas = baseDeDatos.delete(NOMBRE_TABLA, "id = ?", args);
        baseDeDatos.close();
        return filas;
    }
}
