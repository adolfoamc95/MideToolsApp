package com.mide.adolf.socialmide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BBDDLocal extends SQLiteOpenHelper {

        String createMidesSentence = "CREATE TABLE `mides` (" +
                "  `id` int(11) NOT NULL," +
                "  `nombre` varchar(256) NOT NULL," +
                "  `epoca` varchar(20) NOT NULL," +
                "  `horario` varchar(20) NOT NULL," +
                "  `ano` varchar(20) NOT NULL," +
                "  `distancia` varchar(20) NOT NULL," +
                "  `dispos` varchar(20) NOT NULL," +
                "  `ruta` varchar(100) NOT NULL," +
                "  `disneg` varchar(20) NOT NULL," +
                "  `notaSev` varchar(20) NOT NULL," +
                "  `notaOr` varchar(20) NOT NULL," +
                "  `notaDif` varchar(20) NOT NULL," +
                "  `notaEsf` varchar(20) NOT NULL," +
                "  `nPasos` varchar(20) NOT NULL," +
                "  `mRapel` varchar(20) NOT NULL," +
                "  `nievePend` varchar(20) NOT NULL" +
                ")" ;

        public BBDDLocal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(createMidesSentence);

            }catch(Exception e){
                Log.e("BDLocal", "No se han podido crear las bases de datos"+e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mides");
            sqLiteDatabase.execSQL(createMidesSentence);

        }
}

