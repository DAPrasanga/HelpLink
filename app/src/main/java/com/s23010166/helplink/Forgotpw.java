package com.s23010166.helplink;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Forgotpw extends AppCompatActivity {

    private EditText emailEditText;
    private Button sendResetLinkButton;
    private Button resetViaSmsButton;
    private TextView backToLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpw);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        emailEditText = findViewById(R.id.emailEditText);
        sendResetLinkButton = findViewById(R.id.sendResetLinkButton);
        resetViaSmsButton = findViewById(R.id.resetViaSmsButton);
        backToLoginText = findViewById(R.id.backToLoginText);
    }

    private void setupClickListeners() {
        sendResetLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPasswordResetEmail();
            }
        });

        resetViaSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswordViaSms();
            }
        });

        backToLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToLogin();
            }
        });
    }

    private void sendPasswordResetEmail() {
        String email = emailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter your email address");
            emailEditText.requestFocus();
            return;
        }

        if (!isValidEmail(email)) {
            emailEditText.setError("Please enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        // TODO: Implement actual password reset email functionality
        // This is where you would integrate with your backend API or Firebase Auth

        showToast("Password reset link sent to " + email);

        // Example: Firebase Auth implementation
        // FirebaseAuth.getInstance().sendPasswordResetEmail(email)
        //     .addOnCompleteListener(task -> {
        //         if (task.isSuccessful()) {
        //             showToast("Password reset email sent!");
        //         } else {
        //             showToast("Error: " + task.getException().getMessage());
        //         }
        //     });
    }

    private void resetPasswordViaSms() {
        String email = emailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter your email address first");
            emailEditText.requestFocus();
            return;
        }

        // TODO: Implement SMS reset functionality
        // This would typically involve getting the phone number associated with the email
        // and sending an SMS with reset instructions

        showToast("SMS reset option not yet implemented");
    }

    private void goBackToLogin() {
        // Navigate back to login activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBackToLogin();
    }
}