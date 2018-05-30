package com.mide.adolf.socialmide;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MisMidesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<MideObject> datos;
    private int lastId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mides);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deleteCache();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewMideScrollingActivity.class);
                startActivity(intent);
            }
        });

        BBDDLocal bdhelper = new BBDDLocal(this, "mides", null, 1);
        SQLiteDatabase db = bdhelper.getWritableDatabase();

        datos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM mides", null);

        if(c.moveToFirst()) {
            do {

                int id = c.getInt(0);
                String nombre = c.getString(1);
                String año = c.getString(4);
                String ruta = c.getString(7);

                datos.add(new MideObject(id, nombre, año, ruta));
                lastId++;
            } while (c.moveToNext());

            db.close();
        }else {
            Toast toast = Toast.makeText(MisMidesActivity.this, "Aún no tienes ningun Mide,  crea algunos!!", Toast.LENGTH_SHORT);
            toast.show();
        }

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("lastId", lastId);
        editor.commit();

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final RecyclerView recyclerView = findViewById(R.id.list_mides_recycler);


        final MideAdapter adapter = new MideAdapter(datos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                MideObject mideObject = datos.get(position);

                Intent intent = new Intent(getApplicationContext(), ShowMideActivity.class);
                intent.putExtra("mideObject", mideObject);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mis_mides, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intenSet = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intenSet);
            return true;
        }
        if (id == R.id.action_about){
            Intent intenAbout = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intenAbout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent restartMain = new Intent(getApplicationContext(), MisMidesActivity.class);
            startActivity(restartMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            //finish();
        } else if (id == R.id.nav_new) {
            Intent startNew = new Intent(getApplicationContext(), NewMideScrollingActivity.class);
            startActivity(startNew);
        } else if (id == R.id.nav_help) {
            Intent help = new Intent(getApplicationContext(), HelpScrollingActivity.class);
            startActivity(help);
        } else if (id == R.id.nav_settings) {
            Intent intenSet = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intenSet);
        } else if (id == R.id.nav_about) {
            Intent about = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(about);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onRestart() {
        Log.d("MisMidesActivity", "OnRestart...");
        super.onRestart();
        recargarLista();
    }

    private void recargarLista() {
        BBDDLocal bdhelper = new BBDDLocal(this, "mides", null, 1);
        SQLiteDatabase db = bdhelper.getWritableDatabase();

        datos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM mides", null);

        if(c.moveToFirst()) {
            do {

                int id = c.getInt(0);
                String nombre = c.getString(1);
                String año = c.getString(4);
                String ruta = c.getString(7);
                datos.add(new MideObject(id, nombre, año, ruta));

            } while (c.moveToNext());

            db.close();
        }else {
            Toast toast = Toast.makeText(MisMidesActivity.this, "Aún no tienes ningun Mide,  crea algunos!!", Toast.LENGTH_SHORT);
            toast.show();
        }

        final RecyclerView recyclerView = findViewById(R.id.list_mides_recycler);


        final MideAdapter adapter = new MideAdapter(datos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = recyclerView.getChildAdapterPosition(v);
                MideObject mideObject = datos.get(position);

                Intent intent = new Intent(getApplicationContext(), ShowMideActivity.class);
                intent.putExtra("mideObject", mideObject);
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(adapter);

    }

    private void deleteCache(){
        File myDir = new File(getApplicationContext().getCacheDir()+"/");
        if (myDir.isDirectory()) {
            String[] children = myDir.list();
            for (int i = 0; i < children.length; i++) {
                new File(myDir, children[i]).delete();
            }
        }else Log.d("No es un directorio", "no loes , no lo es!!");
    }
}
