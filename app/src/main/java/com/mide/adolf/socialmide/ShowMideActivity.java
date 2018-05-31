package com.mide.adolf.socialmide;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

public class ShowMideActivity extends AppCompatActivity {

    private MideObject mideObject;
    BBDDLocal bdhelper;
    SQLiteDatabase db ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mide);

        final ImageView imageView = findViewById(R.id.imageview_mide);


        mideObject = (MideObject) getIntent().getExtras().getSerializable("mideObject");


        cargar(imageView);

        FloatingActionButton fabShare = findViewById(R.id.floatingActionButton_share);
        FloatingActionButton fabDel = findViewById(R.id.floatingActionButton_delete);

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartir();
            }
        });
        
        fabDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowMideActivity.this);

                // Set a title for alert dialog
                builder.setTitle(getResources().getString(R.string.delete_quest));

                // Ask the final question
                builder.setMessage(getResources().getString(R.string.delete_quest2));

                // Set the alert dialog yes button click listener
                builder.setPositiveButton(getResources().getString(R.string.yes_opt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(delete()){
                            Intent restartMain = new Intent(getApplicationContext(), MisMidesActivity.class);
                            startActivity(restartMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                            finish();
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

    private void cargar(ImageView imageView){
        ContextWrapper cw = new ContextWrapper(this.getApplicationContext());

        //path to /data/data/yourapp/app_data/dirName
        File directory = cw.getDir("imagenes", Context.MODE_PRIVATE);
        File mypath=new File(directory,mideObject.getRuta()+".png");

        Log.d("Ruta", mypath.toString());
        imageView.setImageDrawable(Drawable.createFromPath(mypath.toString()));
    }

    private void compartir(){
        ImageView imageView = (ImageView) findViewById(R.id.imageview_mide);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();

        try {
            File file = new File(getApplicationContext().getCacheDir(), bitmap + ".png");
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    file));
            intent.setType("image/png");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean delete() {

        // Delete from internal storage.
        ContextWrapper cw = new ContextWrapper(this.getApplicationContext());

        File directory = cw.getDir("imagenes", Context.MODE_PRIVATE);
        File mypath=new File(directory,mideObject.getRuta()+".png");

        if(mypath.delete()) {

            // Delete from DB
            bdhelper = new BBDDLocal(this, "mides", null, 1);
            db = bdhelper.getWritableDatabase();

            String whereClause = "id=?";
            String[] whereArgs = new String[]{String.valueOf(mideObject.getMideId())};

            if (db.delete("mides", whereClause, whereArgs) > 0) {
                return true;
            } else {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Ha habido un problema con el borrado de su MIDE", Toast.LENGTH_SHORT);

                toast1.show();
                return false;
            }
        }else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ha habido un problema con el acceso a la memoria Interna", Toast.LENGTH_SHORT);

            toast1.show();
            return false;
        }
    }
}
