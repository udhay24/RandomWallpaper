package com.example.udhay.randomwallpaper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.DB.TinyDB;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {


    //UI Components
    ImageView imageView;
    Button set;
    TextView author;


    //Global Data
    TinyDB db;


    //onCreate Functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);


        //Setting Views
        imageView = findViewById(R.id.wallpaper);
        set = findViewById(R.id.setbutton);
        author =  findViewById(R.id.textView);


        //Setting Default visibility
        set.setVisibility(View.GONE);


        //Setting Tiny DB
        db = new TinyDB(WallpaperActivity.this);


        //Fetching Image Id and Author Name
        String ImageID = db.getString("ImageID");
        String Author = db.getString("Author");


        //setting Image
        setImage(ImageID);


        //Setting Author Name
        author.setText(Author);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/fontstyle.ttf");
        author.setTypeface(custom_font);


    }


    private void setImage(String id) {

         Picasso.get()
                .load(id)
                .centerCrop()
                .resize(800,920)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        imageView.setImageBitmap(bitmap);

                        supportStartPostponedEnterTransition();

                        set.setVisibility(View.VISIBLE);
                        author.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        imageView.setImageDrawable(errorDrawable);

                    }


                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                        imageView.setImageDrawable(placeHolderDrawable);

                    }
                });

    }

    public void setwallpaper(View view){

        new AlertDialog.Builder(this).
                setMessage("Wallpaper Confirmation")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
