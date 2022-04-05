package com.example.evidenciafut.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.evidenciafut.entidades.Jugadores;

import java.util.ArrayList;

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

    public ArrayList<Jugadores> mostrarJugadores(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Jugadores> listaJugadores = new ArrayList<>();
        Jugadores jugadores = null;
        Cursor cursorJugadores = null;

        cursorJugadores = db.rawQuery("SELECT * FROM " + TABLE_JUGADORES, null);

        if (cursorJugadores.moveToFirst()){
            do{
                jugadores = new Jugadores();
                jugadores.setId(cursorJugadores.getInt(0));
                jugadores.setNombre(cursorJugadores.getString(1));
                jugadores.setApellido(cursorJugadores.getString(2));
                jugadores.setNumero(cursorJugadores.getString(3));
                listaJugadores.add(jugadores);
            } while (cursorJugadores.moveToNext());
            cursorJugadores.close();
        }
        return listaJugadores;
    }
}
