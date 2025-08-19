package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;

public class NotificationActivity extends AppCompatActivity {

    private ImageView setting2;
    private ImageView feednav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setting2 = findViewById(R.id.setting2);
        setting2.setOnClickListener(view -> {
            Intent intent = new Intent(NotificationActivity.this, Settings.class);
            startActivity(intent);
        });
        feednav = findViewById(R.id.feednav);
        feednav.setOnClickListener(view -> {
            Intent intent = new Intent(NotificationActivity.this, FeedActivity.class);
            startActivity(intent);
        });
    }
}