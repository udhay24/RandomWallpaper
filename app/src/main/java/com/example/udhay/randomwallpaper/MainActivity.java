package com.example.udhay.randomwallpaper;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.Adapters.ImageAdapter;
import com.example.udhay.randomwallpaper.Util.BasicAuthInterceptor;
import com.example.udhay.randomwallpaper.api.PhotoApi;
import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

@BindView(R.id.imageView)
ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PhotoApi photoApi = getClient().create(PhotoApi.class);

        Call<List<Photo>> photoCall = photoApi.getPhotos(1,20);


        photoCall.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Toast.makeText(MainActivity.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    static Retrofit getClient() {

        Retrofit retrofit = null;

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor("374a680ea9dfd39c388e1411ba9229adfea3b257026d7b44bc7d053ca6729bb9"))
                .build();

        final String BASE_URL = "http://api.unsplash.com/";


            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
            return retrofit;

    }
}
