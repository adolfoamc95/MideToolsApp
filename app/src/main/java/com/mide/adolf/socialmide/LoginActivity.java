package com.mide.adolf.socialmide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.util.Locale;

/**
 * Esta actividad muestra la información basica de la aplicación,  contiene un boton para navegar a
 * la siguiente actividad y un checkbox que nos permite no volver a mostrar esta información en sucesivos
 * usos de la app.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void avoidPresentation(View v) {

        boolean checked = ((CheckBox) v).isChecked();

        if(checked) {
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("logged", true);
            editor.commit();
        }else{
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("logged", false);
            editor.commit();
        }
    }

    public void continueToMain(View v){
        Intent intent = new Intent(LoginActivity.this, MisMidesActivity.class);
        startActivity(intent);
        finish();
    }
}
