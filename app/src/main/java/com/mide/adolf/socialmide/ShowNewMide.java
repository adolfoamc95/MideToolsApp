package com.mide.adolf.socialmide;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShowNewMide extends AppCompatActivity {

    BBDDLocal bdhelper;
    SQLiteDatabase db ;
    MideObject mideObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_new_mide);

        mideObject = (MideObject) getIntent().getExtras().getSerializable("mideObject");

        cargarDatos(mideObject);

        bdhelper = new BBDDLocal(this, "mides", null, 1);
        db = bdhelper.getWritableDatabase();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton_save);
        setClickOnSaveFab(fab);
    }


    private String guardarImagen (Context context, String nombre, Bitmap imagen){
        ContextWrapper cw = new ContextWrapper(context);
        File dirImages = cw.getDir("imagenes", Context.MODE_PRIVATE);
        File myPath = new File(dirImages, nombre + ".png");

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            imagen.compress(Bitmap.CompressFormat.PNG, 10, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return myPath.getAbsolutePath();
    }


    private void setClickOnSaveFab (FloatingActionButton b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout view = (LinearLayout) findViewById(R.id.content_table);
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap bm = view.getDrawingCache();
                if(bm != null){
                    Log.d(getClass().getName(), "Bitmap NO NULO");
                    String nombreImagen = mideObject.getRuta();
                    String ruta = guardarImagen(getApplicationContext(), nombreImagen, bm);
                    Log.d(getClass().getName(), ruta);

                    if (db != null) {
                        MideObject mide1 = mideObject;

                        ContentValues nuevoRegistroA = new ContentValues();

                        nuevoRegistroA.put("id", mide1.getMideId());
                        nuevoRegistroA.put("nombre", mide1.getNombre());
                        nuevoRegistroA.put("epoca", mide1.getEpoca());
                        nuevoRegistroA.put("horario", mide1.getHorario());
                        nuevoRegistroA.put("ano", mide1.getAño());
                        nuevoRegistroA.put("distancia", String.valueOf(mide1.getDistancia()));
                        nuevoRegistroA.put("dispos", String.valueOf(mide1.getDesSubida()));
                        nuevoRegistroA.put("disneg", String.valueOf(mide1.getDesBajada()));
                        nuevoRegistroA.put("notaSev", String.valueOf(mide1.getNotaSev()));
                        nuevoRegistroA.put("notaOr", String.valueOf(mide1.getNotaOr()));
                        nuevoRegistroA.put("notaDif", String.valueOf(mide1.getNotaDiff()));
                        nuevoRegistroA.put("notaEsf", String.valueOf(mide1.getNotaEsf()));
                        nuevoRegistroA.put("nPasos", mide1.getnPasos());
                        nuevoRegistroA.put("mRapel", String.valueOf(mide1.getMetrosRapel()));
                        nuevoRegistroA.put("nievePend", String.valueOf(mide1.getAngPend()));
                        nuevoRegistroA.put("ruta", mide1.getRuta());

                        db.insert("mides", null, nuevoRegistroA);
                        Log.d("BBDD", "mideobject inserted");
                        db.close();

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Su MIDE se ha guardado en su dispostivo", Toast.LENGTH_SHORT);


                        toast1.show();

                        Intent restartMain = new Intent(getApplicationContext(), MisMidesActivity.class);
                        startActivity(restartMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                        finish();
                    }
                }else {
                    Log.d(getClass().getName(), "Bitmap NULL");
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Ha habido un problema con el generado de su MIDE", Toast.LENGTH_SHORT);

                    toast1.show();
                }
            }
        });
    }

    private void setClickOnShare(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void cargarDatos(MideObject object) {

        LinearLayout tableContent = (LinearLayout) findViewById(R.id.content_table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableLayout table = (TableLayout) inflater.inflate(R.layout.image_mide_linear, null, false);

        TextView txtHorario = table.findViewById(R.id.txt_horario);
        TextView txtNombre = table.findViewById(R.id.txt_nombre);
        TextView txtdesSub = table.findViewById(R.id.txt_des_sub);
        TextView txtdesBaj = table.findViewById(R.id.txt_des_baj);
        TextView txtDist = table.findViewById(R.id.txt_dist);
        TextView txtSev = table.findViewById(R.id.txt_nota_sev);
        TextView txtOr = table.findViewById(R.id.txt_nota_or);
        TextView txtDif = table.findViewById(R.id.txt_nota_despl);
        TextView txtCant = table.findViewById(R.id.txt_nota_esf);
        TextView txtDatos = table.findViewById(R.id.txt_datos2);
        TextView txtPasos = table.findViewById(R.id.txt_pasos);
        TextView txtRapel = table.findViewById(R.id.txt_rapel);
        TextView txtNieve = table.findViewById(R.id.txt_nieve);
        TextView txtTipo = table.findViewById(R.id.txt_tipo);

        txtHorario.setText(object.getHorario()+" '");
        txtNombre.setText(String.valueOf(object.getNombre()));
        txtdesSub.setText(String.valueOf(object.getDesSubida())+" m");
        txtdesBaj.setText(String.valueOf(object.getDesBajada())+" m");
        String distancia = String.valueOf(object.getDistancia());
        txtDist.setText(distancia+" Km");
        txtSev.setText(String.valueOf(object.getNotaSev()));
        txtOr.setText(String.valueOf(object.getNotaOr()));
        txtDif.setText(String.valueOf(object.getNotaDiff()));
        txtCant.setText(String.valueOf(object.getNotaEsf()));
        txtDatos.setText(String.valueOf(object.getAño()));
        txtTipo.setText(object.getTipoR());

        Log.d("Pasos", object.getnPasos()+"  pasos");
        Log.d("Rapel", String.valueOf(object.getMetrosRapel()));
        Log.d("Nieve", String.valueOf(object.getAngPend()));

        if(object.getnPasos().equals("")){
            Log.d("Pasos", object.getnPasos()+"pasos vacio");

            TableRow tableRow = table.findViewById(R.id.row_pasos);
            table.removeView(tableRow);
        }else{
            txtPasos.setText(object.getnPasos());
        }

        if(object.getMetrosRapel()==0){
            //txtRapel.setVisibility(View.GONE);
            TableRow tableRow = table.findViewById(R.id.row_rapel);
            table.removeView(tableRow);
        }else{
            txtRapel.setText(String.valueOf(object.getMetrosRapel()));
        }

        if(object.getAngPend()==0){
            //txtNieve.setVisibility(View.GONE);
            TableRow tableRow = table.findViewById(R.id.row_nieve);
            table.removeView(tableRow);
        }else {

            txtNieve.setText(String.valueOf(object.getAngPend()));
        }

        tableContent.addView(table);

    }
}
