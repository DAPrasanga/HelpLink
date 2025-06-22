package com.s23010166.helplink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class NearbyActivity extends AppCompatActivity {

    // UI Components
    private EditText searchEditText;
    private TextView chipAll, chipMedical, chipEducation, chipEmergency, chipHousing;
    private TextView viewMapText;
    private LinearLayout navFeed, navNearby, navProfile, navCamera;

    // Current selected chip
    private TextView selectedChip;
    private ImageView feed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        initializeViews();
        setupClickListeners();
        feed1 = findViewById(R.id.feed1);
        feed1.setOnClickListener(view -> {
            Intent intent = new Intent(NearbyActivity.this, FeedActivity.class);
            startActivity(intent);
        });

        // Set default selection
        selectedChip = chipAll;
    }

    private void initializeViews() {
        // Initialize search
        searchEditText = findViewById(R.id.searchEditText);

        // Initialize category chips
        chipAll = findViewById(R.id.chipAll);
        chipMedical = findViewById(R.id.chipMedical);
        chipEducation = findViewById(R.id.chipEducation);
        chipEmergency = findViewById(R.id.chipEmergency);
        chipHousing = findViewById(R.id.chipHousing);

        // Initialize other components
        viewMapText = findViewById(R.id.viewMapText);

        // Initialize navigation
        navFeed = findViewById(R.id.navFeed);
        navNearby = findViewById(R.id.navNearby);
        navProfile = findViewById(R.id.navProfile);
        navCamera = findViewById(R.id.navCamera);
    }

    private void setupClickListeners() {
        // Category chip listeners
        chipAll.setOnClickListener(v -> selectChip(chipAll, "All"));
        chipMedical.setOnClickListener(v -> selectChip(chipMedical, "Medical"));
        chipEducation.setOnClickListener(v -> selectChip(chipEducation, "Education"));
        chipEmergency.setOnClickListener(v -> selectChip(chipEmergency, "Emergency"));
        chipHousing.setOnClickListener(v -> selectChip(chipHousing, "Housing"));

        // View map listener
        viewMapText.setOnClickListener(v -> {
            // Create an intent to open Google Maps
            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Your Location Here"));
            mapIntent.setData(geoUri);

            // Check if Google Maps is available
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(this, "Google Maps not available", Toast.LENGTH_SHORT).show();
            }
        });

        // Navigation listeners
        navFeed.setOnClickListener(v -> navigateToSection("Feed"));
        navNearby.setOnClickListener(v -> navigateToSection("Nearby"));
        navProfile.setOnClickListener(v -> navigateToSection("Profile"));
        navCamera.setOnClickListener(v -> navigateToSection("Camera"));

        // Search functionality
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                performSearch(query);
            }
            return true;
        });
    }

    private void selectChip(TextView chip, String category) {
        // Reset previous selection
        if (selectedChip != null) {
            selectedChip.setBackground(ContextCompat.getDrawable(this, R.drawable.chip_unselected));
            selectedChip.setTextColor(ContextCompat.getColor(this, R.color.chip_unselected_text));
        }

        // Set new selection
        selectedChip = chip;
        chip.setBackground(ContextCompat.getDrawable(this, R.drawable.chip_selected));
        chip.setTextColor(ContextCompat.getColor(this, android.R.color.white));

        // Filter requests by category
        filterRequestsByCategory(category);

        Toast.makeText(this, "Filtering by: " + category, Toast.LENGTH_SHORT).show();
    }

    private void filterRequestsByCategory(String category) {

        System.out.println("Filtering requests by category: " + category);
    }

    private void performSearch(String query) {
        Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
        System.out.println("Performing search with query: " + query);
    }

    private void navigateToSection(String section) {
        switch (section) {
            case "Feed":
                // TODO: Navigate to feed activity/fragment
                Toast.makeText(this, "Opening Feed", Toast.LENGTH_SHORT).show();
                break;
            case "Nearby":
                // Already on nearby screen
                Toast.makeText(this, "Already on Nearby", Toast.LENGTH_SHORT).show();
                break;
            case "Profile":
                // TODO: Navigate to profile activity/fragment
                Toast.makeText(this, "Opening Profile", Toast.LENGTH_SHORT).show();
                break;
            case "Camera":
                // TODO: Open camera for creating new request
                Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void onRequestCardClick(View view) {
        // TODO: Navigate to request details
        Toast.makeText(this, "Opening request details...", Toast.LENGTH_SHORT).show();
    }


    public void onHelpButtonClick(View view) {
        // TODO: Implement help/donation functionality
        Toast.makeText(this, "Opening help options...", Toast.LENGTH_SHORT).show();
    }


    private void updateRequestCards(java.util.List<HelpRequest> requests) {

    }

    // Inner class to represent a help request
    public static class HelpRequest {
        private String title;
        private String category;
        private String amount;
        private String distance;
        private boolean isUrgent;
        private boolean isVerified;

        public HelpRequest(String title, String category, String amount, String distance, boolean isUrgent, boolean isVerified) {
            this.title = title;
            this.category = category;
            this.amount = amount;
            this.distance = distance;
            this.isUrgent = isUrgent;
            this.isVerified = isVerified;
        }

        // Getters
        public String getTitle() { return title; }
        public String getCategory() { return category; }
        public String getAmount() { return amount; }
        public String getDistance() { return distance; }
        public boolean isUrgent() { return isUrgent; }
        public boolean isVerified() { return isVerified; }
    }
}