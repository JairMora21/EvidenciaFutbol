package com.example.evidenciafut.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.evidenciafut.entidades.Jugadores;
import com.example.evidenciafut.entidades.Partidos;

import java.util.ArrayList;

public class DbPartidos extends DbHelper {

    Context context;

    public DbPartidos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long agregarPartido(String rival, int golesFavor, int golesContra){

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("rival", rival);
            values.put("golesFavor", golesFavor);
            values.put("golesContra", golesContra);

            id = db.insert(TABLE_PARTIDOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
    public void eliminarPartido(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int cantidad = db.delete(TABLE_PARTIDOS,"id=" + id,null);
        db.close();

        if (cantidad >= 1){
            Log.i("Mensaje","Se elimino con exito");
        } else{
            Log.i("Mensaje","No se elimino");
        }

    }

    public ArrayList<Partidos> mostrarPartidos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Partidos> listaPartidos = new ArrayList<>();
        Partidos partidos = null;
        Cursor cursorPartidos = null;

        cursorPartidos = db.rawQuery("SELECT * FROM " + TABLE_PARTIDOS, null);

        if (cursorPartidos.moveToFirst()){
            do{
                partidos = new Partidos();
                partidos.setId(cursorPartidos.getInt(0));
                partidos.setRival(cursorPartidos.getString(1));
                partidos.setGolesFavor(cursorPartidos.getString(2));
                partidos.setGolesContra(cursorPartidos.getString(3));
                listaPartidos.add(partidos);
            } while (cursorPartidos.moveToNext());
            cursorPartidos.close();
        }
        return listaPartidos;
    }


/*
    public Jugadores verJugador(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Jugadores jugadores = null;
        Cursor cursorJugadores;

        cursorJugadores = db.rawQuery("SELECT * FROM " + TABLE_JUGADORES + " WHERE id = " + id + " LIMIT 1", null);



        if (cursorJugadores.moveToFirst()){

            jugadores = new Jugadores();
            jugadores.setId(cursorJugadores.getInt(0));
            jugadores.setNombre(cursorJugadores.getString(1));
            jugadores.setApellido(cursorJugadores.getString(2));
            jugadores.setNumero(cursorJugadores.getString(3));
        }

        cursorJugadores.close();

        return jugadores;
    }
    */

}
