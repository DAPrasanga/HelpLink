package com.s23010166.helplink;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DonateActivity extends AppCompatActivity {

    private ImageView successCircle;
    private TextView successText;
    private TextView amountText;
    private TextView thankYouText;
    private TextView backButton;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // Initialize handler
        handler = new Handler();

        // Initialize views
        initViews();

        // Process the donation
        processDonation();

        // Start animations
        startSuccessAnimation();

        // Auto close after 4 seconds
        setupAutoClose();
    }

    private void initViews() {
        successCircle = findViewById(R.id.successCircle);
        successText = findViewById(R.id.successText);
        amountText = findViewById(R.id.amountText);
        thankYouText = findViewById(R.id.thankYouText);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void processDonation() {
        try {
            // Simulate wallet transaction processing
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Process successful donation
                    showDonationSuccess();
                }
            }, 500);

        } catch (Exception e) {
            // Handle payment failure
            showDonationFailure();
        }
    }

    private void showDonationSuccess() {
        // Update UI with success state
        amountText.setText("$5.00");
        successText.setText("Donation Successful!");
        thankYouText.setText("Thank you for your generosity!");

        // Show success toast
        Toast.makeText(this, "Donation processed successfully", Toast.LENGTH_SHORT).show();

        // Here you would integrate with your actual wallet system
        // Example: deductFromWallet(5.0);
    }

    private void showDonationFailure() {
        // Handle failure case
        successText.setText("Donation Failed");
        thankYouText.setText("Please try again later");

        // Change circle color to red for error
        successCircle.setColorFilter(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    private void startSuccessAnimation() {
        // Scale animation for the circle
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.drawable.scale_bounce);
        successCircle.startAnimation(scaleAnimation);

        // Fade in animation for text
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.drawable.fade_in);
        successText.startAnimation(fadeInAnimation);

        // Delayed animations for other text elements
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(DonateActivity.this, R.drawable.fade_in);
                amountText.startAnimation(fadeIn);
            }
        }, 300);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(DonateActivity .this, R.drawable.fade_in);
                thankYouText.startAnimation(fadeIn);
            }
        }, 600);
    }

    private void setupAutoClose() {
        // Auto close after 4 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 4000);
    }

    // Method to integrate with your wallet system
    private void deductFromWallet(double amount) {
        // Implement your wallet deduction logic here
        // Example:
        // WalletManager walletManager = WalletManager.getInstance();
        // boolean success = walletManager.deductAmount(amount);
        // if (!success) {
        //     showDonationFailure();
        // }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up handler callbacks to prevent memory leaks
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
