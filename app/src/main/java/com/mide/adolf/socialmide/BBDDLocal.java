package com.mide.adolf.socialmide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BBDDLocal extends SQLiteOpenHelper {

        String createMidesSentence = "CREATE TABLE `mides` (" +
                "  `id` int(11) NOT NULL," +
                "  `nombre` varchar(256) NOT NULL," +
                "  `ano` varchar(20) NOT NULL," +
                "  `ruta` varchar(100) NOT NULL" +
                ")";

    String createOptionsSentence = "CREATE TABLE `options` (" +
            "  `opcion` varchar(1000) NOT NULL" +
            ")" ;

        public BBDDLocal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(createMidesSentence);
                sqLiteDatabase.execSQL(createOptionsSentence);

            }catch(Exception e){
                Log.e("BDLocal", "No se han podido crear las bases de datos"+e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mides");
            sqLiteDatabase.execSQL(createMidesSentence);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS options");
            sqLiteDatabase.execSQL(createOptionsSentence);

        }
}

