package com.example.crud_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "CRUD",
            NOMBRE_TABLA_MATERIAS = "Tablasss";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public DBHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id int eger primary key autoincrement, materia text, creditos int)", NOMBRE_TABLA_MATERIAS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
