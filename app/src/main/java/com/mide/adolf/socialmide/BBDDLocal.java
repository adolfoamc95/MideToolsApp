package com.mide.adolf.socialmide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BBDDLocal extends SQLiteOpenHelper {

    // Sentencia de creación de la tabla "mides"
    String createMidesSentence = "CREATE TABLE `mides` (" +
            "  `id` int(11) NOT NULL," +
            "  `nombre` varchar(256) NOT NULL," +
            "  `ano` varchar(20) NOT NULL," +
            "  `ruta` varchar(100) NOT NULL" +
            ")";

    // Sentencia de creación de la tabla "edtiMide"
    String createEditableMideSentence = "CREATE TABLE `editMide` (" +
            "  `id` int(11) NOT NULL," +
            "  `nombre` varchar(256) NOT NULL," +
            "  `epoca` int(2) NOT NULL," +
            "  `ano` int(2) NOT NULL," +
            "  `itOp` int(2) NOT NULL," +
            "  `desOp` int(2) NOT NULL," +
            "  `tipoOp` int(2) NOT NULL," +
            "  `horas` varchar(10) NOT NULL," +
            "  `distancia` int(10) NOT NULL," +
            "  `despos` int(10) NOT NULL," +
            "  `desneg` int(10) NOT NULL," +
            "  `pasosOp` int(2) NOT NULL," +
            "  `rapelOp` int(2) NOT NULL," +
            "  `nieveOp` int(2) NOT NULL," +
            "  `npendOp` int(2) NOT NULL," +
            "  `ruta` varchar(100) NOT NULL" +
            ")";

    // Sentencia de creación de la tabla "options"
    String createOptionsSentence = "CREATE TABLE `options` (" +
            "  `opcion` varchar(1000) NOT NULL" +
            ")" ;

        // Constructor de la clase
        public BBDDLocal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Metodo que crea las tablas al instalar la aplicación
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(createEditableMideSentence);
                sqLiteDatabase.execSQL(createMidesSentence);
                sqLiteDatabase.execSQL(createOptionsSentence);

            }catch(Exception e){
                Log.e("BDLocal", "No se han podido crear las bases de datos"+e.getMessage());
            }
        }

        // Metodo de actualizacíon de las tablas que las borra y las crea de nuevo
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mides");
            sqLiteDatabase.execSQL(createMidesSentence);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS options");
            sqLiteDatabase.execSQL(createOptionsSentence);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS editMide");
            sqLiteDatabase.execSQL(createEditableMideSentence);

        }
}

