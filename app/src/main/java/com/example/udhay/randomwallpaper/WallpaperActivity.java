package com.example.udhay.randomwallpaper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {


    //UI Components
    ImageView imageView;
    Button set;


    //onCreate Functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);


        //Setting Views
        imageView = findViewById(R.id.wallpaper);
        set = findViewById(R.id.setbutton);





    }

    public void setwallpaper(View view){

        new AlertDialog.Builder(this).
                setMessage("Wallpaper Confirmation")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                        try{

                            Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                            wallpaperManager.setBitmap(bitmap);

                        }catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        showSuccessDialog();

                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

    //Confirm Dialog Box
    private void showSuccessDialog() {

        new AlertDialog.Builder(this)
                .setMessage("Image set successfully")
                .setPositiveButton("OK", null)
                .show();

    }


}
