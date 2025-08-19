package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RecipientActivity extends AppCompatActivity {

    // UI Components
    private TextView recipientName;
    private TextView avatarInitials;
    private TextView helpReceivedCount;
    private TextView gratitudeScore;
    private Button shareGratitudeBtn;
    private Button backToFeedBtn;
    private ImageView settingsIcon;
    private ImageView notificationIcon;

    // Bottom Navigation
    private LinearLayout navFeed;
    private LinearLayout navNearby;
    private LinearLayout navProfile;
    private LinearLayout navCamera;

    // Sample data - replace with actual user data
    private String currentRecipientName = "recipient name";
    private int helpReceivedTotal = 5;
    private double currentGratitudeScore = 4.8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        initializeViews();
        setupUserData();
        setupClickListeners();
    }

    private void initializeViews() {
        // Profile data views
        recipientName = findViewById(R.id.recipient_name);
        avatarInitials = findViewById(R.id.avatar_initials);
        helpReceivedCount = findViewById(R.id.help_received_count);
        gratitudeScore = findViewById(R.id.gratitude_score);

        // Action buttons
        shareGratitudeBtn = findViewById(R.id.share_gratitude_btn);
        backToFeedBtn = findViewById(R.id.back_to_feed_btn);

        // Header icons
        settingsIcon = findViewById(R.id.settings_icon);
        notificationIcon = findViewById(R.id.notification_icon);

        // Bottom navigation
        navFeed = findViewById(R.id.nav_feed);
        navNearby = findViewById(R.id.nav_nearby);
        navProfile = findViewById(R.id.nav_profile);
        navCamera = findViewById(R.id.nav_camera);
    }

    private void setupUserData() {
        // Set recipient name
        recipientName.setText(currentRecipientName);

        // Generate and set avatar initials
        String initials = generateInitials(currentRecipientName);
        avatarInitials.setText(initials);

        // Set statistics
        helpReceivedCount.setText(String.valueOf(helpReceivedTotal));
        gratitudeScore.setText(String.valueOf(currentGratitudeScore));
    }

    private void setupClickListeners() {
        // Header icons
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotifications();
            }
        });

        // Action buttons
        shareGratitudeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareGratitude();
            }
        });

        backToFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToFeed();
            }
        });

        // Bottom navigation
        navFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFeed();
            }
        });

        navNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNearby();
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Already on profile - do nothing or refresh
                refreshProfile();
            }
        });

        navCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCamera();
            }
        });
    }

    private String generateInitials(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "R";
        }

        String[] words = name.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (int i = 0; i < Math.min(words.length, 2); i++) {
            if (!words[i].isEmpty()) {
                initials.append(words[i].charAt(0));
            }
        }

        return initials.toString().toUpperCase();
    }

    // Action methods
    private void openSettings() {
        // TODO: Implement settings navigation
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);
    }

    private void openNotifications() {
        // TODO: Implement notifications navigation
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    private void shareGratitude() {
        // TODO: Implement share gratitude functionality
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "I'm grateful for the help I've received through this amazing community! 🙏");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Gratitude");
        startActivity(Intent.createChooser(shareIntent, "Share Gratitude"));
    }

    private void backToFeed() {
        // TODO: Navigate back to feed
        Intent intent = new Intent(this, FeedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    // Navigation methods
    private void navigateToFeed() {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }

    private void navigateToNearby() {
        Intent intent = new Intent(this, NearbyActivity.class);
        startActivity(intent);
    }

    private void refreshProfile() {
        // Refresh profile data
        setupUserData();
    }

    private void navigateToCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    // Method to update profile data (call this when user data changes)
    public void updateProfileData(String name, int helpReceived, double gratitudeScore) {
        this.currentRecipientName = name;
        this.helpReceivedTotal = helpReceived;
        this.currentGratitudeScore = gratitudeScore;

        // Update UI
        setupUserData();
    }

    // Method to increment help received count
    public void incrementHelpReceived() {
        this.helpReceivedTotal++;
        helpReceivedCount.setText(String.valueOf(helpReceivedTotal));
    }

    // Method to update gratitude score
    public void updateGratitudeScore(double newScore) {
        this.currentGratitudeScore = newScore;
        gratitudeScore.setText(String.valueOf(newScore));
    }
}
