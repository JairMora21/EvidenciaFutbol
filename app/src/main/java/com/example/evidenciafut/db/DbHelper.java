package com.example.evidenciafut.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "futbol.db" ;
    public static final String TABLE_JUGADORES = "t_jugadores";
    public static final String TABLE_PARTIDOS = "t_partidos" ;



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_JUGADORES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "numero INT NOT NULL," +
                "goles INT," +
                "participaciones)" );

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PARTIDOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "rival TEXT NOT NULL," +
                "golesFavor INT NOT NULL," +
                "golesContra INT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_JUGADORES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PARTIDOS);

        onCreate(sqLiteDatabase);

    }
}
