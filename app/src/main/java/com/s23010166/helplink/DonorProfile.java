package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DonorProfile extends AppCompatActivity {

    // UI Components
    private ImageView ivSettings, ivNotification;
    private TextView tvProfileInitials, tvDonorName, tvPeopleHelped, tvTrustScore;
    private CardView cvReceipt, cvSms, cvShareGoodDeed, cvBackToFeed;
    private LinearLayout navFeed, navNearby, navProfile, navCamera;

    // Data variables
    private String donorName = "sanuka sahas";
    private int peopleHelped = 10;
    private double trustScore = 4.9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);

        initializeViews();
        setupData();
        setupClickListeners();
    }

    private void initializeViews() {
        // Header components
        ivSettings = findViewById(R.id.iv_settings);
        ivNotification = findViewById(R.id.iv_notification);

        // Profile components
        tvProfileInitials = findViewById(R.id.tv_profile_initials);
        tvDonorName = findViewById(R.id.tv_donor_name);
        tvPeopleHelped = findViewById(R.id.tv_people_helped);
        tvTrustScore = findViewById(R.id.tv_trust_score);

        // Action buttons
        cvReceipt = findViewById(R.id.cv_receipt);
        cvSms = findViewById(R.id.cv_sms);
        cvShareGoodDeed = findViewById(R.id.cv_share_good_deed);
        cvBackToFeed = findViewById(R.id.cv_back_to_feed);

        // Bottom navigation
        navFeed = findViewById(R.id.nav_feed);
        navNearby = findViewById(R.id.nav_nearby);
        navProfile = findViewById(R.id.nav_profile);
        navCamera = findViewById(R.id.nav_camera);
    }

    private void setupData() {
        // Set donor name
        tvDonorName.setText(donorName);

        // Set profile initials (first letter of first and last name)
        String initials = getInitials(donorName);
        tvProfileInitials.setText(initials);

        // Set people helped count
        tvPeopleHelped.setText(String.valueOf(peopleHelped));

        // Set trust score
        tvTrustScore.setText(String.valueOf(trustScore));
    }

    private String getInitials(String fullName) {
        String[] names = fullName.split(" ");
        StringBuilder initials = new StringBuilder();

        for (String name : names) {
            if (!name.isEmpty()) {
                initials.append(name.charAt(0));
            }
        }

        return initials.toString().toUpperCase();
    }

    private void setupClickListeners() {
        // Header click listeners
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotifications();
            }
        });

        // Action button click listeners
        cvReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReceiptInfo();
            }
        });

        cvSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSmsInfo();
            }
        });

        cvShareGoodDeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareGoodDeed();
            }
        });

        cvBackToFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToFeed();
            }
        });

        // Bottom navigation click listeners
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
                // Already on profile page
                Toast.makeText(DonorProfile.this, "You're already on Profile", Toast.LENGTH_SHORT).show();
            }
        });

        navCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCamera();
            }
        });
    }

    // Header action methods
    private void openSettings() {
        Toast.makeText(this, "Opening Settings", Toast.LENGTH_SHORT).show();
        // Intent to settings activity
        // Intent intent = new Intent(this, SettingsActivity.class);
        // startActivity(intent);
    }

    private void openNotifications() {
        Toast.makeText(this, "Opening Notifications", Toast.LENGTH_SHORT).show();
        // Intent to notifications activity
        // Intent intent = new Intent(this, NotificationsActivity.class);
        // startActivity(intent);
    }

    // Action button methods
    private void showReceiptInfo() {
        Toast.makeText(this, "Receipt has been sent to your email", Toast.LENGTH_LONG).show();
    }

    private void showSmsInfo() {
        Toast.makeText(this, "SMS confirmation has been sent", Toast.LENGTH_LONG).show();
    }

    private void shareGoodDeed() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Good Deed Shared");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "I just helped " + peopleHelped + " people through donation. Join me in making a difference!");

        startActivity(Intent.createChooser(shareIntent, "Share Good Deed"));
    }

    private void backToFeed() {
        // Navigate back to feed activity
        Intent intent = new Intent(this, FeedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    // Bottom navigation methods
    private void navigateToFeed() {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToNearby() {
        Toast.makeText(this, "Opening Nearby", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, NearbyActivity.class);
        // startActivity(intent);
    }

    private void navigateToCamera() {
        Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, CameraActivity.class);
        // startActivity(intent);
    }

    // Update profile data method
    public void updateProfileData(String name, int helped, double score) {
        this.donorName = name;
        this.peopleHelped = helped;
        this.trustScore = score;
        setupData();
    }

    // Getter methods for profile data
    public String getDonorName() {
        return donorName;
    }

    public int getPeopleHelped() {
        return peopleHelped;
    }

    public double getTrustScore() {
        return trustScore;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}