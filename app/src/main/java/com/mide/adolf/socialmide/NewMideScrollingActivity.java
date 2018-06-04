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

/**
 * Esta actividad contiene todos los elementos y metodos necesarios para crear un nuevo mide
 */
public class NewMideScrollingActivity extends AppCompatActivity {

    private ViewGroup layout;
    private int nChecked = 0;
    private int checkedIt = 0, checkedDes =0;
    private MideObject mideObject = new MideObject();
    private MideObject editMide;
    BBDDLocal bdhelper;
    SQLiteDatabase db ;
    ArrayList<String> opciones;
    int editId = 0;
    boolean edit= false;
    ArrayList<Integer> checkBoxMarcados = new ArrayList<>();



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        layout = (ViewGroup) findViewById(R.id.content);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editId = prefs.getInt("editId", 0);
        Log.d("editId", String.valueOf(editId));
        if(editId!=0){
            rellenarEdit();
            edit = true;
        }


        final LinearLayout ll1 = añadirGrupos(1, edit);
        final LinearLayout ll2 = añadirGrupos(2, edit);
        final LinearLayout ll3 = añadirGrupos(3, edit);
        final LinearLayout ll4 = añadirGrupos(4, edit);
        final LinearLayout ll5 = añadirGrupos(5, edit);
        final LinearLayout ll6 = añadirGrupos(6, edit);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showErrors(ll1, ll2, ll3, ll4, ll5, ll6);

            }
        });



    }

    /**
     * Metodo que carga los diferentes xml que componen esta actividad en el orden indicado
     * Este metodo tambien añade los listener para los spinner.
     * @param nGrupo orden de creación
     * @param edit si estamos editando un elemento existente o creando uno nuevo
     * @return devuelve el layout con los datos cargados para su addicion al layout principal
     */
    private LinearLayout añadirGrupos(int nGrupo, boolean edit){

        int grupo = nGrupo;

        LayoutInflater inflater = LayoutInflater.from(this);

        switch (grupo) {
            case 1:
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.resource_general, null, false);
                TextView tituloG = (TextView) linearLayout.findViewById(R.id.titulo_general);
                tituloG.setText(getResources().getString(R.string.titulo_general));
                if(edit){
                    EditText nombre = linearLayout.findViewById(R.id.txt_general_name);
                    nombre.setText(editMide.getNombre());
                    Spinner epoca = linearLayout.findViewById(R.id.general_condiciones_spinner);
                    epoca.setSelection(editMide.getSelectedEpoca());
                    Spinner año = linearLayout.findViewById(R.id.general_año_spinner);
                    año.setSelection(editMide.getSelectedAño());
                }
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
                int checkboxid = 0;
                for(String s: opciones) {
                   final CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.style_checkbox, null);
                   checkBox.setText(s);
                   checkBox.setId(checkboxid);
                   if(edit){
                       for(int i : editMide.getListaCheckPulsados()){
                           if (checkboxid==i){
                               checkBox.setChecked(true);
                           }
                       }
                   }
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
                   checkBox.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           boolean checked = ((CheckBox) v).isChecked();
                           if(checked) {
                               checkBoxMarcados.add(v.getId());
                               Log.d("marcado", String.valueOf(v.getId()));
                           }else{
                               checkBoxMarcados.remove(new Integer(v.getId()));
                           }
                       }
                   });
                   checkboxid++;
                   linearLayout2.addView(checkBox, checkboxParams);
                }

                layout.addView(linearLayout2);
                añadirSeparador();
                return linearLayout2;


            case 3:
                int selectedOptIt=0;
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
                    selectedOptIt++;
                    if(edit && selectedOptIt==editMide.getCheckedIt()){
                        radioGroup.check(radioButton.getId());
                    }
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
                int selectedOptDes= 0;
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
                    selectedOptDes++;
                    if(edit && selectedOptDes==editMide.getCheckedIt()){
                        radioGroup2.check(radioButton.getId());
                    }
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

                LinearLayout linearLayout5 = (LinearLayout) inflater.inflate(R.layout.resource_esfuerzo, null, false);
                if(edit){
                    Spinner tipoR = linearLayout5.findViewById(R.id.firme_esf_spinner);
                    tipoR.setSelection(editMide.getSelectedTipo());
                    EditText editTiempo = linearLayout5.findViewById(R.id.tiempo_esf);
                    editTiempo.setText(editMide.getHorario());
                    EditText editDist = linearLayout5.findViewById(R.id.dist_esf);
                    editDist.setText(String.valueOf(editMide.getDistMetros()));
                    EditText editAsc = linearLayout5.findViewById(R.id.des_pos_esf);
                    editAsc.setText(String.valueOf(editMide.getDesSubida()));
                    EditText editDesc = linearLayout5.findViewById(R.id.des_neg_esf);
                    editDesc.setText(String.valueOf(editMide.getDesBajada()));
                }

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

                if(edit){
                    sppasos.setSelection(editMide.getSelectedPasos());
                    sprapel.setSelection(editMide.getSelectedRapel());
                    spNieve.setSelection(editMide.getSelectedNieve());
                    spNievePend.setSelection(editMide.getSelectedNievePend());
                }

                spNieve.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("NievePostition", String.valueOf(position));
                        if(position!=0){

                            spNievePend.setVisibility(View.VISIBLE);
                            txt_pend.setVisibility(View.VISIBLE);
                            mideObject.setSelectedNieve(position);
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
                        mideObject.setSelectedPasos(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spNievePend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mideObject.setAngPend(position);
                        mideObject.setSelectedNievePend(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sprapel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mideObject.setMetrosRapel(position);
                        mideObject.setSelectedRapel(position);
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

    /**
     * Método que añade un separador entre cada uno de los layouts de la secciones.
     */
    private void añadirSeparador() {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout linearLayout2 = (LinearLayout) inflater.inflate(R.layout.resource_separator, null, false);

        layout.addView(linearLayout2);
    }

    /*private void añadirEsfuerzoItem(LinearLayout principal) {
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
    }*/

    /**
     * Metodo que recoge los datos una vez que estos han sido introducidos y se ha pulsado el boton de previsualizar.
     * @param contenedor el layout del que tiene que recoger los datos
     * @param opcion identificador de seccion
     * @return verdadero si estan todos los datos requerido y falso en caso contrario.
     */
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
                    mideObject.setSelectedEpoca(spCond.getSelectedItemPosition());
                    mideObject.setAño(spAño.getSelectedItemPosition());
                    mideObject.setSelectedAño(spAño.getSelectedItemPosition());
                    String ruta = nombre+mideObject.getMideId();
                    Log.d("newmidescrolingactiv" ,  ruta);
                    mideObject.setRuta(ruta);
                    return true;
                }
                return false;

            case 2:

                if(nChecked!=0) {
                    mideObject.setNotaSev(nChecked);
                    mideObject.setListaCheckPulsados(checkBoxMarcados);

                    return true;
                }
                return false;

            case 3 :
                if(checkedIt!=0) {
                    mideObject.setCheckedIt(checkedIt);
                    mideObject.setNotaOr(checkedIt);
                    return true;
                }else if(edit){
                    mideObject.setCheckedIt(editMide.getCheckedIt());
                    mideObject.setNotaOr(editMide.getCheckedIt());
                    return true;
                }
                return false;

            case 4:

                if(checkedDes!=0) {
                    mideObject.setCheckedDespl(checkedDes);
                    mideObject.setNotaDiff(checkedDes);
                    return true;
                }else if(edit){
                    mideObject.setCheckedDespl(editMide.getCheckedDespl());
                    mideObject.setNotaDiff(editMide.getCheckedDespl());
                    return true;
                }
                return false;

            case 5:
                int tipoR;
                String distancia, desBaj, desSub;
                double horas;

                EditText txtDist = contenedor.findViewById(R.id.dist_esf);
                EditText tiempo = contenedor.findViewById(R.id.tiempo_esf);
                EditText txtPos = contenedor.findViewById(R.id.des_pos_esf);
                EditText txtNeg = contenedor.findViewById(R.id.des_neg_esf);
                Spinner spinner1 = contenedor.findViewById(R.id.firme_esf_spinner);



                distancia = txtDist.getText().toString()+"";
                horas = Double.parseDouble(tiempo.getText().toString());
                desBaj = txtNeg.getText().toString()+"";
                desSub = txtPos.getText().toString()+"";
                tipoR = spinner1.getSelectedItemPosition();



                if(distancia!= "" && tipoR != 0 && horas !=0) {
                    if(desSub == "") desSub = "0";
                    if(desBaj == "") desBaj = "0";
                  int notaesf = calculoNotaTramo(horas);

                    mideObject.setHorario(String.valueOf(tiempo.getText()));
                    mideObject.setSelectedTipo(tipoR);
                    mideObject.setTipoR(tipoR);
                    mideObject.setDistancia(Integer.valueOf(distancia));
                    mideObject.setDistMetros(Integer.valueOf(distancia));
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

    /**
     * Método que calcula la nota de un tramo en base a su duración
     * @param hora
     * @return
     */
    private int calculoNotaTramo(double hora){
        double horas = hora;

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

    /**
     * Metodo que avisa al usuario en el caso de haber algun error en la introducción de los parámetros
     * y le dice el lugar en el que esta el error
     * @param l1 layout de la seccion 1
     * @param l2 layout de la seccion 2
     * @param l3 layout de la seccion 3
     * @param l4 layout de la seccion 4
     * @param l5 layout de la seccion 5
     * @param l6 layout de la seccion 6
     */
    private void showErrors(LinearLayout l1, LinearLayout l2, LinearLayout l3, LinearLayout l4, LinearLayout l5, LinearLayout l6) {

        NestedScrollView scrollView = findViewById(R.id.scroll_view);

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
                                        getResources().getString(R.string.toast_error_4), Toast.LENGTH_SHORT);

                        toast1.show();
                        scrollView.scrollTo(0, l5.getBottom());
                    }
                }else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.toast_error_3), Toast.LENGTH_SHORT);

                    toast1.show();
                    //scrollView.scrollTo(0, l4.findViewById(R.id.titulo_rb).getBottom());
                }
            }else {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.toast_error_2), Toast.LENGTH_SHORT);

                toast1.show();
                //scrollView.scrollTo(0, l3.findViewById(R.id.titulo_rb).getBottom());
            }
        }else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.toast_error_1), Toast.LENGTH_SHORT);

            toast1.show();
            //scrollView.scrollTo(0, l1.getBottom());
        }

    }


    /**
     * Metodo que se llama si en lugar de crear un nuevo mide venimos desde la opcion de editar.
     * Este metodo busca el elemento a editar en la base de datos y carga los datos que contiene en las
     * diferentes opciones.
     */
    private void rellenarEdit(){
        BBDDLocal bdhelper = new BBDDLocal(this, "editMide", null, 1);
        SQLiteDatabase db = bdhelper.getWritableDatabase();

        String[] args = new String[] {String.valueOf(editId)};
        Cursor c = db.rawQuery(" SELECT * FROM editMide WHERE id=? ", args);

        if(c.moveToFirst()) {
            do {

                int id = c.getInt(0);
                String nombre = c.getString(1);
                int epoca = c.getInt(2);
                int ano = c.getInt(3);
                int itOp = c.getInt(4);
                int desOp = c.getInt(5);
                int tipoOp = c.getInt(6);
                String horas =c.getString(7);
                int dist = c.getInt(8);
                int despos = c.getInt(9);
                int desneg = c.getInt(10);
                int pasoOp = c.getInt(11);
                int rapelOp = c.getInt(12);
                int nieve = c.getInt(13);
                int nieveP = c.getInt(14);
                String ruta = c.getString(15);
                String cadenaCB = c.getString(16);

                editMide = new MideObject(id, nombre, epoca, ano, itOp, desOp, tipoOp, horas, dist, despos, desneg, pasoOp, rapelOp, nieve, nieveP, ruta);
                editMide.setListaCheckPulsados(editMide.stringToLista(cadenaCB));

            } while (c.moveToNext());

            db.close();
        }
    }
}
