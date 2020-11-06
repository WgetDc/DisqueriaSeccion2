package com.wgetdc.disqueriaseccion2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class gestorDeBaseDeDatos extends SQLiteOpenHelper {

    String tabla_discos = "CREATE TABLE discos(id int primary key, artistas text, album text, fecha text)";
    public gestorDeBaseDeDatos(@Nullable Context context, @Nullable String name,
                               @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_discos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
