package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedActivity extends AppCompatActivity {

    private Button donateButton;
    private ImageView notify;
    private ImageView setting1;
    private ImageView nav_camera;
    private ImageView navNearby;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        donateButton = findViewById(R.id.btnDonate);
        donateButton.setOnClickListener(view ->
                Toast.makeText(FeedActivity.this, "Thank you for your kindness!", Toast.LENGTH_SHORT).show()
        );
        notify = findViewById(R.id.notify);
        notify.setOnClickListener(view -> {
            Intent intent = new Intent(FeedActivity.this, Notification.class);
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
    }
}
