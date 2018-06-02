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

    private void clickOnAdd(){

        NewMedioObjFragment newMedioObjFragment = new NewMedioObjFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.unique_fragment, newMedioObjFragment)
                .addToBackStack("medio")
                .commit();

    }

    private void clickOnLang(){

        LanguajeFragment lang = new LanguajeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.unique_fragment, lang)
                .addToBackStack("lang")
                .commit();

    }



    @Override
    public void onBackPressed() {

        Intent volver = new Intent(getApplicationContext(), MisMidesActivity.class);
        startActivity(volver.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();


    }

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

               /* Locale localizacion = new Locale("es");

                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, null);*/
                break;
            case "setES":

                SharedPreferences prefs2 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor2 = prefs2.edit();
                editor2.putString("language", "es");
                editor2.commit();

                Locale localizacion2 = new Locale("en");
                Locale.setDefault(localizacion2);
                Configuration config2 = new Configuration();
                config2.locale = localizacion2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());
                break;
        }
    }


}
