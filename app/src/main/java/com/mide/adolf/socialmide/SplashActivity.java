package com.mide.adolf.socialmide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 3000; // 3 segundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            public void run(){

                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                boolean logged = prefs.getBoolean("logged", false);

                if(logged) {

                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    Intent intent = new Intent(SplashActivity.this, MisMidesActivity.class);
                    startActivity(intent);
                    finish();

                }else {

                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
        }, DURACION_SPLASH);
    }
}
