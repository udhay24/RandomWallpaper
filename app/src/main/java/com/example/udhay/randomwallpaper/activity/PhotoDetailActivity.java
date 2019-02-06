package com.example.udhay.randomwallpaper.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.ScaleXY;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.Photo;
import com.example.udhay.randomwallpaper.util.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PhotoDetailActivity extends AppCompatActivity {

    public static final String ID = "photo_id";
    private static final int SET_WALLPAPER_ID = 24;

    private String id;
    private Photo selectedPhoto;

    @BindView(R.id.photo_image_view)
    ImageView photoImageView;

    @BindView(R.id.bottom_sheet)
    ConstraintLayout bottomSheetLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.save)
    LottieAnimationView saveButton;

    @BindView(R.id.download)
    ImageView downloadButton;

    @BindView(R.id.share)
    TextView shareTextView;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.bottom_sheet_arrow)
    LottieAnimationView arrowImageView;

    @BindView(R.id.profile_image)
    CircleImageView profileImageView;

    @BindView(R.id.views)
    TextView viewsTextView;

    @BindView(R.id.downloads_text_view)
    TextView downloadsTextView;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        photoImageView.setVisibility(View.INVISIBLE);

        arrowImageView.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new SimpleColorFilter(Color.WHITE));

        arrowImageView.addValueCallback(
                new KeyPath("**"),
                LottieProperty.TRANSFORM_SCALE,
                frameInfo -> new ScaleXY(2, 2));


        arrowImageView.setScale(1.5f);
        openArrow();

        id = getIntent().getStringExtra(ID);

        loadImage();

        saveButton.setOnClickListener(v -> saveImage());

        downloadButton.setOnClickListener(v -> downloadImage());

        shareTextView.setOnClickListener(v -> shareImage());

        profileImageView.setOnClickListener(v -> showProfile());

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                        closeArrow();
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        openArrow();
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBottomSheet();
            }
        });
    }

    private void loadImage() {

        UnSplashApi unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getPhotoById(id).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {

                selectedPhoto = response.body();
                Picasso.get()
                        .load(selectedPhoto.getUrls().getRegular())
                        .into(photoImageView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                postponeEnterTransition();
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

                if (selectedPhoto.getDescription() != null) {
                    description.setText(selectedPhoto.getDescription());
                    photoImageView.setContentDescription(selectedPhoto.getDescription());
                } else {
                    description.setText("No Description");
                }

                Picasso.get()
                        .load(selectedPhoto.getUser().getProfileImage().getMedium())
                        .into(profileImageView);

                viewsTextView.setText(selectedPhoto.getViews().toString());
                downloadsTextView.setText(selectedPhoto.getDownloads().toString());

                photoImageView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItemCompat.setShowAsAction(menu.add(Menu.NONE, SET_WALLPAPER_ID, Menu.NONE, "Set Wallpaper"), MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == SET_WALLPAPER_ID) {
            setWallpaper();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setWallpaper() {

        Toast.makeText(this, "Set Wallpaper", Toast.LENGTH_SHORT).show();
    }

    private void saveImage() {

        saveButton.playAnimation();
        Timber.v(saveButton.getDuration() + "");

    }

    private void shareImage() {

        Toast.makeText(PhotoDetailActivity.this, "share", Toast.LENGTH_SHORT).show();
    }

    private void downloadImage() {

        Toast.makeText(PhotoDetailActivity.this, "downloaded", Toast.LENGTH_SHORT).show();
    }

    private void showProfile() {

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.USER_NAME, selectedPhoto.getUser().getUsername());
        startActivity(intent);
    }

    public void toggleBottomSheet() {
        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    private void openArrow() {

        arrowImageView.setMinAndMaxFrame(0, 10);
        arrowImageView.setSpeed(1.4f);
        arrowImageView.playAnimation();
    }

    private void closeArrow() {

        arrowImageView.setMinAndMaxFrame(35, 48);
        arrowImageView.setSpeed(1.4f);
        arrowImageView.playAnimation();
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }

}