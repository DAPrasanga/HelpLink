package com.s23010166.helplink;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private static final String PREFS_NAME = "AppSettings";
    private ImageView feed;
    private ImageView notify1;


    // Switches
    private Switch switchUrgentRequests;
    private Switch switchNearbyRequests;
    private Switch switchPaymentUpdates;
    private Switch switchEmailNotifications;
    private Switch switchGpsTracking;

    // Other views
    private TextView tvSearchRadius;
    private LinearLayout layoutChangePassword;

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    // Search radius options
    private String[] radiusOptions = {"1 km", "3 km", "5 km", "10 km", "15 km", "20 km"};
    private int selectedRadiusIndex = 2; // Default to 5 km

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();
        initializeSharedPreferences();
        loadSettings();
        setupClickListeners();
    }

    private void initializeViews() {
        switchUrgentRequests = findViewById(R.id.switch_urgent_requests);
        switchNearbyRequests = findViewById(R.id.switch_nearby_requests);
        switchPaymentUpdates = findViewById(R.id.switch_payment_updates);
        switchEmailNotifications = findViewById(R.id.switch_email_notifications);
        switchGpsTracking = findViewById(R.id.switch_gps_tracking);

        tvSearchRadius = findViewById(R.id.tv_search_radius);
        layoutChangePassword = findViewById(R.id.layout_change_password);
        feed = findViewById(R.id.feed);
        feed.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.this,FeedActivity.class);
            startActivity(intent);
        });
        notify1 = findViewById(R.id.notify1);
        notify1.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.this, NotificationActivity.class);
            startActivity(intent);
        });
    }

    private void initializeSharedPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    private void loadSettings() {
        // Load switch states from SharedPreferences
        switchUrgentRequests.setChecked(sharedPreferences.getBoolean("urgent_requests", true));
        switchNearbyRequests.setChecked(sharedPreferences.getBoolean("nearby_requests", true));
        switchPaymentUpdates.setChecked(sharedPreferences.getBoolean("payment_updates", false));
        switchEmailNotifications.setChecked(sharedPreferences.getBoolean("email_notifications", true));
        switchGpsTracking.setChecked(sharedPreferences.getBoolean("gps_tracking", true));

        // Load search radius
        selectedRadiusIndex = sharedPreferences.getInt("search_radius_index", 2);
        tvSearchRadius.setText(radiusOptions[selectedRadiusIndex]);
    }

    private void setupClickListeners() {
        // Switch listeners
        switchUrgentRequests.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettingBoolean("urgent_requests", isChecked);
            showToast("Urgent requests notifications " + (isChecked ? "enabled" : "disabled"));
        });

        switchNearbyRequests.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettingBoolean("nearby_requests", isChecked);
            showToast("Nearby requests notifications " + (isChecked ? "enabled" : "disabled"));
        });

        switchPaymentUpdates.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettingBoolean("payment_updates", isChecked);
            showToast("Payment updates notifications " + (isChecked ? "enabled" : "disabled"));
        });

        switchEmailNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettingBoolean("email_notifications", isChecked);
            showToast("Email notifications " + (isChecked ? "enabled" : "disabled"));
        });

        switchGpsTracking.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettingBoolean("gps_tracking", isChecked);
            showToast("GPS tracking " + (isChecked ? "enabled" : "disabled"));

            if (!isChecked) {
                showGpsWarningDialog();
            }
        });

        // Search radius selector
        tvSearchRadius.setOnClickListener(v -> showRadiusSelectionDialog());

        // Change password
        layoutChangePassword.setOnClickListener(v -> showChangePasswordDialog());
    }

    private void saveSettingBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private void saveSettingInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private void showRadiusSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Search Radius");

        builder.setSingleChoiceItems(radiusOptions, selectedRadiusIndex,
                (dialog, which) -> {
                    selectedRadiusIndex = which;
                    tvSearchRadius.setText(radiusOptions[selectedRadiusIndex]);
                    saveSettingInt("search_radius_index", selectedRadiusIndex);
                    showToast("Search radius set to " + radiusOptions[selectedRadiusIndex]);
                    dialog.dismiss();
                });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showGpsWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GPS Tracking Disabled");
        builder.setMessage("Disabling GPS tracking may affect your ability to receive nearby requests and provide accurate location services. Are you sure you want to continue?");

        builder.setPositiveButton("Keep Disabled", (dialog, which) -> {
            // GPS remains disabled, do nothing
            dialog.dismiss();
        });

        builder.setNegativeButton("Re-enable GPS", (dialog, which) -> {
            switchGpsTracking.setChecked(true);
            saveSettingBoolean("gps_tracking", true);
            dialog.dismiss();
        });

        builder.show();
    }

    private void showChangePasswordDialog() {
        // Simple placeholder for change password functionality
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Password");
        builder.setMessage("This would typically open a password change screen or form.");

        builder.setPositiveButton("Open Password Screen", (dialog, which) -> {
            // TODO: Launch password change activity
            showToast("Password change screen would open here");
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // Getter methods for settings (can be used by other parts of the app)
    public boolean isUrgentRequestsEnabled() {
        return sharedPreferences.getBoolean("urgent_requests", true);
    }

    public boolean isNearbyRequestsEnabled() {
        return sharedPreferences.getBoolean("nearby_requests", true);
    }

    public boolean isPaymentUpdatesEnabled() {
        return sharedPreferences.getBoolean("payment_updates", false);
    }

    public boolean isEmailNotificationsEnabled() {
        return sharedPreferences.getBoolean("email_notifications", true);
    }

    public boolean isGpsTrackingEnabled() {
        return sharedPreferences.getBoolean("gps_tracking", true);
    }

    public String getSearchRadius() {
        int index = sharedPreferences.getInt("search_radius_index", 2);
        return radiusOptions[index];
    }

    public int getSearchRadiusInKm() {
        String radius = getSearchRadius();
        return Integer.parseInt(radius.split(" ")[0]);
    }
}