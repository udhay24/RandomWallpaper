package com.example.udhay.randomwallpaper.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.User;
import com.example.udhay.randomwallpaper.util.RetrofitClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    public static final String USER_NAME = "user_id";

    private String userName;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private UnSplashApi unSplashApi;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        userName = getIntent().getStringExtra(USER_NAME);

        unSplashApi.getUser(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                user = response.body();
                getSupportActionBar().setTitle(user.getName());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    private void setGraph() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date d1, d2, d3, d4, d5;
        d1 = d2 = d3 = d4 = d5 = new Date();
        try {
            d1 = format.parse("2017-02-25");
            d2 = format.parse("2017-02-26");
            d3 = format.parse("2017-02-27");
            d4 = format.parse("2017-02-28");
            d5 = format.parse("2017-03-1");


        } catch (ParseException e) {
            e.printStackTrace();
        }

        GraphView graph = findViewById(R.id.graph_view);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3),
                new DataPoint(d4, 2),
                new DataPoint(d5, 6)
        });
        series.setAnimated(true);
        series.setColor(Color.GRAY);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(7);
        series.setThickness(4);
        series.setTitle("Views");

        graph.addSeries(series);

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);// remove horizontal x labels and line
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalAxisTitle("views");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(d1, 5),
                new DataPoint(d2, 4),
                new DataPoint(d3, 6),
                new DataPoint(d4, 8),
                new DataPoint(d5, 1)
        });

        graph.addSeries(series2);
    }
}
