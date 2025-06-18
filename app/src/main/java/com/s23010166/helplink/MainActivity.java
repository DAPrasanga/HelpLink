package com.s23010166.helplink;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginBtn, facebookBtn, googleBtn;
    private TextView forgotPassword, registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);
        forgotPassword = findViewById(R.id.forgotPassword);
        registerLink = findViewById(R.id.registerLink);

        loginBtn.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Implement login logic
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
            }
        });

        registerLink.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });

        forgotPassword.setOnClickListener(view ->
                Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        );

        facebookBtn.setOnClickListener(view ->
                Toast.makeText(this, "Facebook login coming soon", Toast.LENGTH_SHORT).show()
        );

        googleBtn.setOnClickListener(view ->
                Toast.makeText(this, "Google login coming soon", Toast.LENGTH_SHORT).show()
        );
    }
}

