package com.mide.adolf.socialmide;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

    MideObject mideObject;
    boolean edit = false;
    int editId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_new_mide);

        mideObject = (MideObject) getIntent().getExtras().getSerializable("mideObject");

        cargarDatos(mideObject);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton_save);
        setClickOnSaveFab(fab);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editId = prefs.getInt("editId", 0);
        Log.d("editId", String.valueOf(editId));
        if(editId!=0){
            edit = true;
        }

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


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowNewMide.this);

                // Set a title for alert dialog
                builder.setTitle(getResources().getString(R.string.delete_quest));

                // Ask the final question
                builder.setMessage(getResources().getString(R.string.delete_quest2));

                // Set the alert dialog yes button click listener
                builder.setPositiveButton(getResources().getString(R.string.yes_opt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LinearLayout view = (LinearLayout) findViewById(R.id.content_table);
                        view.setDrawingCacheEnabled(true);
                        view.buildDrawingCache();
                        Bitmap bm = view.getDrawingCache();
                        if(edit){
                            delete();
                        }
                        if(bm != null){
                            String nombreImagen = mideObject.getRuta();
                            String ruta = guardarImagen(getApplicationContext(), nombreImagen, bm);

                            if(guardarDatosMide() && guardarDatosEdit()){
                                Toast toast1 = Toast.makeText(getApplicationContext(), "Su MIDE se ha guardado en su dispostivo", Toast.LENGTH_SHORT);
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

                // Set the alert dialog no button click listener
                builder.setNegativeButton(getResources().getString(R.string.no_opt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();

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
        String distancia = object.getDistancia();
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

    private boolean guardarDatosMide(){
        BBDDLocal bdhelper = new BBDDLocal(this, "mides", null, 1);
        SQLiteDatabase db = bdhelper.getWritableDatabase();
        if (db != null) {
            MideObject mide1 = mideObject;

            ContentValues nuevoRegistroA = new ContentValues();

            nuevoRegistroA.put("id", mide1.getMideId());
            nuevoRegistroA.put("nombre", mide1.getNombre());
            nuevoRegistroA.put("ano", mide1.getAño());
            nuevoRegistroA.put("ruta", mide1.getRuta());

            if(!edit) {
                db.insert("mides", null, nuevoRegistroA);
            }else{
                String[] args = new String[]{String.valueOf(editId)};
                db.update("mides", nuevoRegistroA, "id=?", args);
            }
            Log.d("BBDD", "mideobject inserted");
            db.close();

            return true;
        }
        return false;
    }

    private boolean guardarDatosEdit(){
        BBDDLocal bdhelper = new BBDDLocal(this, "editMide", null, 1);
        SQLiteDatabase db = bdhelper.getWritableDatabase();
        if (db != null) {
            MideObject mide1 = mideObject;

            ContentValues nuevoRegistroA = new ContentValues();

            nuevoRegistroA.put("id", mide1.getMideId());
            nuevoRegistroA.put("nombre", mide1.getNombre());
            nuevoRegistroA.put("epoca", mide1.getSelectedEpoca());
            nuevoRegistroA.put("ano", mide1.getSelectedAño());
            nuevoRegistroA.put("itOp", mide1.getCheckedIt());
            nuevoRegistroA.put("desOp", mide1.getCheckedDespl());
            nuevoRegistroA.put("tipoOp", mide1.getSelectedTipo());
            nuevoRegistroA.put("horas", mide1.getHorario());
            nuevoRegistroA.put("distancia", mide1.getDistMetros());
            nuevoRegistroA.put("despos", mide1.getDesSubida());
            nuevoRegistroA.put("desneg", mide1.getDesBajada());
            nuevoRegistroA.put("pasosOp", mide1.getSelectedPasos());
            nuevoRegistroA.put("rapelOp", mide1.getSelectedRapel());
            nuevoRegistroA.put("nieveOp", mide1.getSelectedNieve());
            nuevoRegistroA.put("npendOp", mide1.getSelectedNievePend());
            nuevoRegistroA.put("ruta", mide1.getRuta());

            if(!edit) {
                db.insert("editMide", null, nuevoRegistroA);
            }else {
                String[] args = new String[]{String.valueOf(editId)};
                db.update("editMide", nuevoRegistroA, "id=?", args);
            }
            Log.d("BBDD", "mideedit inserted");
            db.close();
            return true;
        }
        return false;
    }

    private boolean delete() {

        // Delete from internal storage.
        ContextWrapper cw = new ContextWrapper(this.getApplicationContext());

        File directory = cw.getDir("imagenes", Context.MODE_PRIVATE);
        File mypath=new File(directory,mideObject.getRuta()+".png");

        if(mypath.delete()) {
            return true;
        }else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ha habido un problema con el acceso a la memoria Interna", Toast.LENGTH_SHORT);

            toast1.show();
            return false;
        }
    }
}
