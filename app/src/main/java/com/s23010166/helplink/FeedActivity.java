package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FeedActivity extends AppCompatActivity {

    private Button donateButton;
    private ImageView notify;
    private ImageView setting1;
    private ImageView nav_camera;
    private ImageView navNearby;
    private ImageView profile;
    private FloatingActionButton fabAddPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        donateButton = findViewById(R.id.btnDonate);
        donateButton.setOnClickListener(view ->{
                Intent intent = new Intent(FeedActivity.this, DonateActivity.class);
                startActivity(intent);
        });
        notify = findViewById(R.id.notify);
        notify.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, NotificationActivity.class);
            startActivity(intent);
        });
        setting1 = findViewById(R.id.setting1);
        setting1.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, Settings.class);
            startActivity(intent);
        });
        nav_camera = findViewById(R.id.nav_camera);
        nav_camera.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, CameraActivity.class);
            startActivity(intent);
        });
        navNearby = findViewById(R.id.navNearby);
        navNearby.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, NearbyActivity.class);
            startActivity(intent);
        });
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, DonorProfile.class);
            startActivity(intent);
        });
        fabAddPost = findViewById(R.id.fabAddPost);
        fabAddPost.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, CreateActivity.class);
            startActivity(intent);
        });

    }
}
