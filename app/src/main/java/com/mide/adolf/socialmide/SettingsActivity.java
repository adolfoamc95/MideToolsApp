package com.mide.adolf.socialmide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

/**
 * Actividad de ajustes.
 * Desde esta actividad se controla el flujo de los framentos que la componen y el cambio de ajustes.
 */
public class SettingsActivity extends FragmentActivity
        implements GeneralFragment.OnGeneralFragmentInteractionListener,
        NewMedioObjFragment.OnNewFragmentInteractionListener,
        LanguajeFragment.OnLangFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        GeneralFragment generalFragment = new GeneralFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.unique_fragment, generalFragment)
                        .addToBackStack("general")
                        .commit();

    }

    /**
     * Metodo que se llama si se pulsa la opcion de añadir Opcion de Medio
     * Este metodo carga el fragmento correspondiente
     */
    private void clickOnAdd(){

        NewMedioObjFragment newMedioObjFragment = new NewMedioObjFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.unique_fragment, newMedioObjFragment)
                .addToBackStack("medio")
                .commit();

    }

    /**
     * Metodo que se llama si se pulsa la opcion de cambiar lenguage
     * Este metodo carga el fragmento correspondiente
     */
    private void clickOnLang(){

        LanguajeFragment lang = new LanguajeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.unique_fragment, lang)
                .addToBackStack("lang")
                .commit();

    }


    /**
     * Metodo que controla la pulsación en el boton de atrás
     */
    @Override
    public void onBackPressed() {

        Intent volver = new Intent(getApplicationContext(), MisMidesActivity.class);
        startActivity(volver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();


    }

    /**
     * Metodo que controla las diferentes acciones capturadas en los fragmentos en base a unos tag
     * enviados por los mismos
     * @param s tag que indica que metodo ejecutar.
     */
    @Override
    public void onFragmentInteraction(String s) {
        Log.d("EnFragment", s);
        switch (s){
            case "add":
                clickOnAdd();
                break;
            case "lang":
                clickOnLang();
                break;
            case "setEN":

                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language", "en");
                editor.commit();

                break;
            case "setES":

                SharedPreferences prefs2 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor2 = prefs2.edit();
                editor2.putString("language", "es");
                editor2.commit();

                break;
        }
    }


}
