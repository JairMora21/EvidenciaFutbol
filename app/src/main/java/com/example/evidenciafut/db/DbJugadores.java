package com.example.evidenciafut.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbJugadores extends DbHelper {

    Context context;

    public DbJugadores(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long agregarJugador(String nombre, String apellido, int numero){

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("numero", numero);

             id = db.insert(TABLE_JUGADORES, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
