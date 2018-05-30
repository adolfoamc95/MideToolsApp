package com.mide.adolf.socialmide;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ShowMideActivity extends AppCompatActivity {

    private static Bitmap bitMap;
    private MideObject mideObject;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mide);

        ImageView imageView = findViewById(R.id.imageview_mide);


        mideObject = (MideObject) getIntent().getExtras().getSerializable("mideObject");


        cargar(imageView);
        /*Button btn = (Button) findViewById(R.id.btn);


        LinearLayout tableContent = (LinearLayout) findViewById(R.id.content_table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableLayout table = (TableLayout) inflater.inflate(R.layout.image_mide_linear, null, false);
        tableContent.addView(table);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout view = (LinearLayout) findViewById(R.id.content_table);
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap bm = view.getDrawingCache();
                if(bm != null){
                    Log.d(getClass().getName(), "Bitmap NO NULO");
                    String ruta = guardarImagen(getApplicationContext(), "tabla", bm);
                    Log.d(getClass().getName(), ruta);



                }else Log.d(getClass().getName(), "Bitmap NULL");
            }
        });*/
    }
/*
    private String guardarImagen (Context context, String nombre, Bitmap imagen){
        ContextWrapper cw = new ContextWrapper(context);
        File dirImages = cw.getDir("imagenes", Context.MODE_PRIVATE);
        File myPath = new File(dirImages, nombre + ".png");

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            imagen.compress(Bitmap.CompressFormat.JPEG, 10, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return myPath.getAbsolutePath();
    }
    public final static String APP_PATH_SD_CARD = "SocialMide";
    public final static String APP_THUMBNAIL_PATH_SD_CARD = "thumbnails";

    public boolean saveImageToExternalStorage(Bitmap image) {


        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD + APP_THUMBNAIL_PATH_SD_CARD;

        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            OutputStream fOut;
            File file = new File(fullPath, "desiredFilename.png");
            file.createNewFile();
            fOut = new FileOutputStream(file);

            // 100 means no compression, the lower you go, the stronger the compression
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();

            MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());

            return true;

        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
            return false;
        }
    }
*/

    private void cargar(ImageView imageView){
        ContextWrapper cw = new ContextWrapper(this.getApplicationContext());

        //path to /data/data/yourapp/app_data/dirName
        File directory = cw.getDir("imagenes", Context.MODE_PRIVATE);
        File mypath=new File(directory,mideObject.getRuta()+".png");

        Log.d("Ruta", mypath.toString());
        imageView.setImageDrawable(Drawable.createFromPath(mypath.toString()));
    }

    private void compartir(ImageView imageView){

        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();

/***** COMPARTIR IMAGEN *****/
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
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            getApplicationContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
