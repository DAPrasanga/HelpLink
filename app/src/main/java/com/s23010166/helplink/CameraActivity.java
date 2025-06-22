package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    private ImageView notify;
    private ImageView feed;
    private ImageView mainfeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        notify = findViewById(R.id.notify);
        notify.setOnClickListener(view -> {
            Intent intent = new Intent(CameraActivity.this, Notification.class);
            startActivity(intent);
        });
        feed = findViewById(R.id.feed);
        feed.setOnClickListener(view -> {
            Intent intent = new Intent(CameraActivity.this, FeedActivity.class);
            startActivity(intent);
        });
        mainfeed = findViewById(R.id.mainfeed);
        mainfeed.setOnClickListener(view -> {
            Intent intent = new Intent(CameraActivity.this, FeedActivity.class);
            startActivity(intent);
        });
    }
}




