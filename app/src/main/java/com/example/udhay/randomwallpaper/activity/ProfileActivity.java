package com.example.udhay.randomwallpaper.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.User;
import com.example.udhay.randomwallpaper.model.UserStatistics;
import com.example.udhay.randomwallpaper.util.RetrofitClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    @BindView(R.id.profile_image)
    ImageView profileImageView;

    @BindView(R.id.location_text_view)
    TextView locationTextView;

    @BindView(R.id.bio_text_view)
    TextView bioTextView;

    @BindView(R.id.followers_text_view)
    TextView followersTextView;

    @BindView(R.id.following_text_view)
    TextView followingTextView;

    @BindView(R.id.graph_view)
    GraphView statisticsGraphView;

    private UnSplashApi unSplashApi;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        userName = getIntent().getStringExtra(USER_NAME);

        unSplashApi.getUser(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                user = response.body();
                setToolbarLayout();
                setLocation();
                setBio();
                setFollows();
                setGraph();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    private void setToolbarLayout() {

        getSupportActionBar().setTitle(user.getName());
        Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(user.getProfileImage().getLarge())
                .into(profileImageView);

    }

    private void setLocation() {

        locationTextView.setText(user.getLocation());
    }

    private void setBio() {

        bioTextView.setText(user.getBio());
    }

    private void setFollows() {

        followersTextView.setText(user.getFollowersCount().toString());
        followingTextView.setText(user.getFollowingCount().toString());
    }

    private void setGraph() {

        unSplashApi.getUserStatistics(userName).enqueue(new Callback<UserStatistics>() {
            @Override
            public void onResponse(Call<UserStatistics> call, Response<UserStatistics> response) {

                addDownloadSeries(response);
                addViewsSeries(response);
                addLikesSeries(response);
                statisticsGraphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
                statisticsGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);// remove horizontal x labels and line
                statisticsGraphView.getGridLabelRenderer().setVerticalLabelsVisible(false);
                statisticsGraphView.getGridLabelRenderer().setVerticalAxisTitle("Frequency");
                statisticsGraphView.getGridLabelRenderer().setHorizontalAxisTitle("Time");
                statisticsGraphView.getLegendRenderer().setVisible(true);
                statisticsGraphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

            }

            @Override
            public void onFailure(Call<UserStatistics> call, Throwable t) {

            }
        });

    }

    private ArrayList<Date> getDateList(ArrayList<UserStatistics.Value> values) {

        ArrayList<Date> dates = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        for (UserStatistics.Value value : values) {
            try {

                dates.add(format.parse(value.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dates;
    }

    private ArrayList<Integer> getValueList(ArrayList<UserStatistics.Value> values) {

        ArrayList<Integer> valueList = new ArrayList<>();

        for (UserStatistics.Value value : values) {
            valueList.add(value.getValue());
        }

        return valueList;
    }

    private void addDownloadSeries(Response<UserStatistics> response) {

        ArrayList<Date> dates = getDateList(new ArrayList<UserStatistics.Value>(response.body().getDownloads().getHistorical().getValues()));
        ArrayList<Integer> values = getValueList(new ArrayList<UserStatistics.Value>(response.body().getDownloads().getHistorical().getValues()));

        DataPoint[] downloadDataPoints = new DataPoint[dates.size()];
        int max = Collections.max(values);
        for (int i = 0; i < dates.size(); i++) {

            downloadDataPoints[i] = new DataPoint(dates.get(i), values.get(i) * 100 / max);
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(downloadDataPoints);
        series.setAnimated(true);
        series.setColor(Color.parseColor("#3A1412"));
        series.setThickness(4);
        series.setTitle("downloads");

        statisticsGraphView.addSeries(series);
    }

    private void addLikesSeries(Response<UserStatistics> response) {

        ArrayList<Date> dates = getDateList(new ArrayList<UserStatistics.Value>(response.body().getLikes().getHistorical().getValues()));
        ArrayList<Integer> values = getValueList(new ArrayList<UserStatistics.Value>(response.body().getLikes().getHistorical().getValues()));

        DataPoint[] likesDataPoints = new DataPoint[dates.size()];
        int max = Collections.max(values);
        for (int i = 0; i < dates.size(); i++) {

            likesDataPoints[i] = new DataPoint(dates.get(i), values.get(i) * 100 / max);
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(likesDataPoints);
        series.setAnimated(true);
        series.setColor(Color.parseColor("#ED2D7B"));
        series.setThickness(4);
        series.setTitle("likes");

        statisticsGraphView.addSeries(series);
    }

    private void addViewsSeries(Response<UserStatistics> response) {

        ArrayList<Date> dates = getDateList(new ArrayList<UserStatistics.Value>(response.body().getViews().getHistorical().getValues()));
        ArrayList<Integer> values = getValueList(new ArrayList<UserStatistics.Value>(response.body().getViews().getHistorical().getValues()));

        DataPoint[] viewsDataPoints = new DataPoint[dates.size()];
        int max = Collections.max(values);

        for (int i = 0; i < dates.size(); i++) {

            viewsDataPoints[i] = new DataPoint(dates.get(i), values.get(i) * 100 / max);
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(viewsDataPoints);
        series.setAnimated(true);
        series.setColor(Color.parseColor("#BE9275"));
        series.setThickness(4);
        series.setTitle("views");

        statisticsGraphView.addSeries(series);
    }




}
