package com.example.crud_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "materias.db";
    private static final int VERSION_BASE_DATOS = 1;

    // Sentencia SQL para crear la tabla "materias"
    private static final String TABLA_MATERIAS =
            "CREATE TABLE materias (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "materia TEXT NOT NULL, " +
                    "creditos INTEGER NOT NULL)";

    public DBHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_MATERIAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS materias");
        onCreate(db);
    }
}
