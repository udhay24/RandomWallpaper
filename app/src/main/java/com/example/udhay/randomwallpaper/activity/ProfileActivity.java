package com.example.udhay.randomwallpaper.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.adapters.TagsAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.User;
import com.example.udhay.randomwallpaper.model.UserStatistics;
import com.example.udhay.randomwallpaper.util.AnimInterpolator;
import com.example.udhay.randomwallpaper.util.RetrofitClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.HttpUrl;
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

    @BindView(R.id.following_image_view)
    ImageView followingImageView;

    @BindView(R.id.followers_image_view)
    ImageView followersImageView;

    @BindView(R.id.photos_image_view)
    ImageView photosImageView;

    @BindView(R.id.collections_image_view)
    ImageView collectionImageView;

    @BindView(R.id.location_lottie_view)
    LottieAnimationView locationLottieView;

    @BindView(R.id.tags_recycler_view)
    RecyclerView tagsRecyclerView;

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

        locationLottieView.playAnimation();

        followersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.bounce_anim);
                animation.setInterpolator(new AnimInterpolator(0.1, 25));
                v.startAnimation(animation);
                follower();
            }
        });

        followingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                following();
            }
        });

        photosImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photos();
            }
        });

        collectionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collections();
            }
        });


        unSplashApi.getUser(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                user = response.body();
                setToolbarLayout();
                setLocation();
                setBio();
                setFollows();
                setGraph();
                setTags(response.body().getTags());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    private void setToolbarLayout() {

        getSupportActionBar().setTitle(user.getName());

        URL url = null;
        try {
            url = new URL(user.getProfileImage().getLarge());

            Picasso.get()
                    .load(HttpUrl.get(url.getProtocol() + "://" + url.getHost() + url.getPath())
                            .newBuilder()
                            .addQueryParameter("w", "600")
                            .addQueryParameter("h", "760")
                            .addQueryParameter("crop", "faces")
                            .build().toString())

                    .into(profileImageView);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    private void setLocation() {

        if (user.getLocation() != null || TextUtils.isEmpty(user.getLocation())) {
            locationTextView.setText(user.getLocation());
        }
    }

    private void setBio() {

        if (user.getBio() != null || !TextUtils.isEmpty(user.getBio())) {
            bioTextView.setText(user.getBio());
        }
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

        int max = Collections.max(values) == 0 ? 1 : Collections.max(values);

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
        int max = Collections.max(values) == 0 ? 1 : Collections.max(values);

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
        int max = Collections.max(values) == 0 ? 1 : Collections.max(values);

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

    private void follower() {

        followersImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.profileActivityIconSelected));
        followingImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        photosImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        collectionImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));

    }

    private void following() {

        followingImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.profileActivityIconSelected));
        followersImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        photosImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        collectionImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));

    }

    private void photos() {

        photosImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.profileActivityIconSelected));
        followingImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        followersImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        collectionImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));

    }

    private void collections() {

        collectionImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.profileActivityIconSelected));
        followingImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        photosImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));
        followersImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000));

    }

    private void setTags(User.Tags tags) {

        tagsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        tagsRecyclerView.setAdapter(new TagsAdapter(tags.getCustom(), tags.getAggregated()));

    }

}
