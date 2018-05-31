package com.mide.adolf.socialmide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

public class NewMideScrollingActivity extends AppCompatActivity {

    private ViewGroup layout;
    private int nChecked = 0;
    private int checkedIt = 0, checkedDes =0;
    private MideObject mideObject = new MideObject();
    private int nEsfItems = 0;
    BBDDLocal bdhelper;
    SQLiteDatabase db ;
    ArrayList<String> opciones;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        layout = (ViewGroup) findViewById(R.id.content);


        final LinearLayout ll1 = añadirGrupos(1);
        final LinearLayout ll2 = añadirGrupos(2);
        final LinearLayout ll3 = añadirGrupos(3);
        final LinearLayout ll4 = añadirGrupos(4);
        final LinearLayout ll5 = añadirGrupos(5);
        final LinearLayout ll6 = añadirGrupos(6);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showErrors(ll1, ll2, ll3, ll4, ll5, ll6);

            }
        });

    }

    private LinearLayout añadirGrupos(int nGrupo){

        int grupo = nGrupo;

        LayoutInflater inflater = LayoutInflater.from(this);

        switch (grupo) {
            case 1:
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.resource_general, null, false);
                TextView tituloG = (TextView) linearLayout.findViewById(R.id.titulo_general);
                tituloG.setText(getResources().getString(R.string.titulo_general));
                layout.addView(linearLayout);
                añadirSeparador();
                return linearLayout;

            case 2:
                LinearLayout linearLayout2 = (LinearLayout) inflater.inflate(R.layout.resource_medio, null, false);


                TextView tituloM = (TextView) linearLayout2.findViewById(R.id.titulo_medio);
                tituloM.setText(getResources().getString(R.string.titulo_medio));

                bdhelper = new BBDDLocal(this, "options", null, 1);
                db = bdhelper.getWritableDatabase();

                String[] array = getResources().getStringArray(R.array.options);

                opciones = new ArrayList<>(Arrays.asList(array));

                Cursor c = db.rawQuery("SELECT * FROM options", null);

                if(c.moveToFirst()) {
                    do {

                        String opcion = c.getString(0);
                        opciones.add(opcion);

                    } while (c.moveToNext());

                    db.close();
                }

                LinearLayout.LayoutParams checkboxParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                checkboxParams.topMargin = 10;
                checkboxParams.bottomMargin =10;

                for(String s: opciones) {
                   CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.style_checkbox, null);
                   checkBox.setText(s);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                nChecked++;
                                Log.d(this.getClass().getName(), "Uno mas");
                            }else {
                                nChecked--;
                                Log.d(this.getClass().getName(), "Uno menos");
                            }
                        }
                    });
                   linearLayout2.addView(checkBox, checkboxParams);
                }

                layout.addView(linearLayout2);
                añadirSeparador();
                return linearLayout2;


            case 3:
                LinearLayout linearLayout3 = (LinearLayout) inflater.inflate(R.layout.resource_radiobutton, null, false);

                TextView txt_titulo = linearLayout3.findViewById(R.id.titulo_rb);
                RadioGroup radioGroup = new RadioGroup(this);

                if(radioGroup == null) break;
                txt_titulo.setText(getResources().getString(R.string.titulo_it));

                String[] array1 = getResources().getStringArray(R.array.options_rb_1);

                LinearLayout.LayoutParams rbParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rbParams.topMargin = 10;
                rbParams.bottomMargin =10;

                for(String s: array1) {
                    RadioButton radioButton = (RadioButton) getLayoutInflater().inflate(R.layout.style_radio_button, null);
                    LinearLayout.LayoutParams params = new RadioGroup.LayoutParams(
                            RadioGroup.LayoutParams.WRAP_CONTENT,
                            RadioGroup.LayoutParams.WRAP_CONTENT);
                    radioButton.setLayoutParams(params);
                    radioButton.setText(s);
                    radioGroup.addView(radioButton, rbParams);
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        checkedIt = checkedId;
                    }
                });

                linearLayout3.addView(radioGroup);
                layout.addView(linearLayout3);
                añadirSeparador();
                return linearLayout3;


            case 4:

                LinearLayout linearLayout4 = (LinearLayout) inflater.inflate(R.layout.resource_radiobutton, null, false);


                RadioGroup radioGroup2 = new RadioGroup(this);
                TextView txt_titulo2 = linearLayout4.findViewById(R.id.titulo_rb);

                if(radioGroup2 == null) break;

                txt_titulo2.setText(getResources().getString(R.string.titulo_desp));

                String[] array2 = getResources().getStringArray(R.array.options_rb_2);

                LinearLayout.LayoutParams rbParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rbParams2.topMargin = 10;
                rbParams2.bottomMargin =10;
                for(String s: array2) {
                    RadioButton radioButton = (RadioButton) getLayoutInflater().inflate(R.layout.style_radio_button, null);
                    LinearLayout.LayoutParams params = new RadioGroup.LayoutParams(
                            RadioGroup.LayoutParams.WRAP_CONTENT,
                            RadioGroup.LayoutParams.WRAP_CONTENT);
                    radioButton.setLayoutParams(params);
                    radioButton.setText(s);
                    radioGroup2.addView(radioButton, rbParams2);
                }
                radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case 6:
                                checkedDes = 1;
                                break;
                            case 7:
                                checkedDes = 2;
                                break;
                            case 8:
                                checkedDes = 3;
                                break;
                            case 9:
                                checkedDes = 4;
                                break;
                            case 10:
                                checkedDes = 5;
                                break;
                        }
                    }
                });

                linearLayout4.addView(radioGroup2);
                layout.addView(linearLayout4);
                añadirSeparador();
                linearLayout4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                return linearLayout4;


            case 5:

                final LinearLayout linearLayout5 = (LinearLayout) inflater.inflate(R.layout.resource_esfuerzo, null, false);

                añadirEsfuerzoItem(linearLayout5);

                layout.addView(linearLayout5);

                añadirSeparador();
                return linearLayout5;


            case 6:

                LinearLayout linearLayout6 = (LinearLayout) inflater.inflate(R.layout.resource_tecnicas, null, false);

                final TextView txt_pend = linearLayout6.findViewById(R.id.txt_pend);
                Spinner spNieve = linearLayout6.findViewById(R.id.tecnicas_nieve_spinner);
                final Spinner spNievePend = linearLayout6.findViewById(R.id.tecnicas_nieve_spinner_pen);
                Spinner sppasos = linearLayout6.findViewById(R.id.tecnicas_pasos_spinner);
                Spinner sprapel = linearLayout6.findViewById(R.id.tecnicas_rapel_spinner);

                spNievePend.setVisibility(View.INVISIBLE);
                txt_pend.setVisibility(View.INVISIBLE);

                spNieve.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("NievePostition", String.valueOf(position));
                        if(position!=0){

                            spNievePend.setVisibility(View.VISIBLE);
                            txt_pend.setVisibility(View.VISIBLE);
                        }else {
                            spNievePend.setVisibility(View.INVISIBLE);
                            txt_pend.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Log.d(getClass().getName(), "Nada seleccionado");
                    }
                });

                sppasos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mideObject.setnPasos(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spNievePend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mideObject.setAngPend(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sprapel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mideObject.setMetrosRapel(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                layout.addView(linearLayout6);
                añadirSeparador();
                return linearLayout6;

        }
        return null;

    }

    private void añadirSeparador() {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout linearLayout2 = (LinearLayout) inflater.inflate(R.layout.resource_separator, null, false);

        layout.addView(linearLayout2);
    }

    private void añadirEsfuerzoItem(LinearLayout principal) {
        LayoutInflater inflater2 = LayoutInflater.from(principal.getContext());
        LinearLayout layoutEsfuerzoItem = (LinearLayout) inflater2.inflate(R.layout.resource_esfuerzo_item, null, false);
        layoutEsfuerzoItem.setId(nEsfItems);
        ViewGroup contenedor = (ViewGroup) principal.findViewById(R.id.layout_esf_item);
        contenedor.addView(layoutEsfuerzoItem);
        nEsfItems++;
    }

    private void eliminarEsfuerzoItem(LinearLayout principal) {
        LinearLayout contenedor = (LinearLayout) principal.findViewById(R.id.layout_esf_item);
        int nhijos = contenedor.getChildCount();
        if (nhijos >= 2){
            contenedor.removeViewAt(1);
        }
    }

    private boolean recogerDatos(LinearLayout contenedor, int opcion) {
        switch (opcion){
            case 1:

                String nombre;

                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                int lastId = prefs.getInt("lastId", 0);

                mideObject.setMideId(lastId+1);

                TextView txtName = contenedor.findViewById(R.id.txt_general_name);
                Spinner spCond = contenedor.findViewById(R.id.general_condiciones_spinner);
                Spinner spAño = contenedor.findViewById(R.id.general_año_spinner);

                nombre = txtName.getText().toString()+"";

                if(nombre!="") {
                    mideObject.setNombre(nombre);
                    mideObject.setEpoca(spCond.getSelectedItemPosition());
                    mideObject.setAño(spAño.getSelectedItemPosition());
                    String ruta = nombre+mideObject.getMideId();
                    Log.d("newmidescrolingactiv" ,  ruta);
                    mideObject.setRuta(ruta);
                    return true;
                }
                return false;

            case 2:

                if(nChecked!=0) {
                    mideObject.setNotaSev(nChecked);
                    return true;
                }
                return false;

            case 3 :
                if(checkedIt!=0) {
                    mideObject.setNotaOr(checkedIt);
                    return true;
                }
                return false;

            case 4:

                if(checkedDes!=0) {
                    mideObject.setNotaDiff(checkedDes);
                    return true;
                }
                return false;

            case 5:
                int tipoR;
                String distancia, desBaj, desSub;

                LinearLayout contenedor_esf = (LinearLayout) contenedor.findViewById(R.id.layout_esf_item);
                EditText txtDist = contenedor_esf.findViewById(R.id.dist_esf);
                EditText txtPos = contenedor_esf.findViewById(R.id.txt_esf_despos);
                EditText txtNeg = contenedor_esf.findViewById(R.id.txt_esf_desneg);
                Spinner spinner = contenedor_esf.findViewById(R.id.firme_tipo_spinner);
                Spinner spinner1 = contenedor.findViewById(R.id.firme_esf_spinner);



                distancia = txtDist.getText().toString()+"";
                desBaj = txtNeg.getText().toString()+"";
                desSub = txtPos.getText().toString()+"";
                tipoR = spinner1.getSelectedItemPosition();



                if(distancia!= "" && tipoR != 0) {
                    if(desSub == "") desSub = "0";
                    if(desBaj == "") desBaj = "0";
                  int notaesf = calculoNotaTramo(Integer.valueOf(txtDist.getText().toString()), Integer.valueOf(txtPos.getText().toString()), Integer.valueOf(txtNeg.getText().toString()), spinner.getSelectedItemPosition());

                    mideObject.setTipoR(tipoR);
                    mideObject.setDistancia(Integer.valueOf(distancia));
                    mideObject.setDesSubida(Integer.valueOf(desSub));
                    mideObject.setDesBajada(Integer.valueOf(desBaj));
                    mideObject.setNotaEsf(notaesf);
                    return true;
                }
                return false;

            case 6:

                return true;


        }
        return false;
    }

    private int calculoNotaTramo(int distancia, int despos, int desneg, int pista){

        double oneKm = 1000;
        double velPos = 400, velNeg = 600;
        double horas;
        double horasRec = 0;


        // Horas de marcha horizontal
        double kmRectos = distancia/oneKm;

        switch (pista) {
            case 0:
                horasRec = kmRectos/5;
                break;
            case 1:
                horasRec = kmRectos/4;
                break;
            case 2:
                horasRec = kmRectos/3;
                break;
        }


        // Horas de marcha desnivel positivo
        double horasPos = despos/velPos;

        // Horas de marcha desnivel negativo
        double horasNeg = desneg/velNeg;

        double totalDesn = horasNeg+ horasPos;

        if(totalDesn>horasRec){
            horas = totalDesn + (horasRec/2);
        }else {
            horas = horasRec + (totalDesn/2);
        }

        mideObject.setHorario(String.valueOf(horas));

        if(horas<=1){
            return 1;
        }else if(horas>1 && horas <=3) {
            return 2;
        }else if(horas>3 && horas <=6) {
            return 3;
        }else if(horas>6 && horas <=10) {
            return 4;
        }else if(horas>10) {
            return 5;
        }

        return 0;
    }

    private boolean hayFallos(NestedScrollView scroll) {
        if(mideObject.getNombre().equals("")){
            scroll.scrollTo(0, 1);
            return false;
        }else if(mideObject.getNotaSev()==0){
            scroll.scrollTo(0, 2);
            return false;
        }else if(mideObject.getNotaOr()==0){
            scroll.scrollTo(0, 3);
            return false;
        }else if(mideObject.getNotaDiff()==0){
            return false;
        }else if(mideObject.getDistancia().equals("")||mideObject.getDesBajada()==0||mideObject.getDesSubida()==0){
            return false;
        }else if(mideObject.getTipoR().equals("")){
            return false;
        }
        return true;
    }

    private void showErrors(LinearLayout l1, LinearLayout l2, LinearLayout l3, LinearLayout l4, LinearLayout l5, LinearLayout l6) {
        if(recogerDatos(l1, 1)){
            recogerDatos(l2, 2);
            if(recogerDatos(l3, 3)){
                if(recogerDatos(l4, 4)){
                    if(recogerDatos(l5, 5)){
                        recogerDatos(l6, 6);

                        Intent intent = new Intent(NewMideScrollingActivity.this, ShowNewMide.class);
                        intent.putExtra("mideObject", mideObject);
                        startActivity(intent);
                    }else{
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Flatan datos en la seccion Esfuerzo", Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Debe seleccionar alguna opcion de dificultad", Toast.LENGTH_SHORT);

                    toast1.show();
                }
            }else {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Faltan datos para calcular la orientación", Toast.LENGTH_SHORT);

                toast1.show();
            }
        }else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Falta nombre de la excursión", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }
}
