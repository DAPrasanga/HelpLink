package com.s23010166.helplink;


import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullName, email, phone, password, confirmPassword;
    private RadioGroup accountTypeGroup;
    private RadioButton radioDonor, radioRecipient;
    private Button registerButton;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);


        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        accountTypeGroup = findViewById(R.id.accountTypeGroup);
        radioDonor = findViewById(R.id.radioDonor);
        radioRecipient = findViewById(R.id.radioRecipient);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> {
            String name = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String phoneNumber = phone.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            if (name.isEmpty() || mail.isEmpty() || phoneNumber.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = accountTypeGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Select account type", Toast.LENGTH_SHORT).show();
                return;
            }

            String accountType = (selectedId == R.id.radioDonor) ? "Donor" : "Recipient";

            boolean success = dbHelper.registerUser(mail, pass);

            if (success) {
                Toast.makeText(this, "Account created as " + accountType, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email already exists. Please login.", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
