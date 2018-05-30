package com.mide.adolf.socialmide;

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
                        .commit();

    }

    private void clickOnAdd(){

        NewMedioObjFragment newMedioObjFragment = new NewMedioObjFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.unique_fragment, newMedioObjFragment)
                .commit();

    }

    private void clickOnLang(){

        LanguajeFragment lang = new LanguajeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.unique_fragment, lang)
                .commit();

    }



    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            GeneralFragment fragmentGeneral = new GeneralFragment();
            transaction.add(R.id.contenedor, fragmentGeneral);
            transaction.commit();
        } else {
            getFragmentManager().popBackStack();
        }

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
