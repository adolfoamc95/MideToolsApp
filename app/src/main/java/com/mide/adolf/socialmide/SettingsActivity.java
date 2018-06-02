package com.mide.adolf.socialmide;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


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
        }
    }


}
