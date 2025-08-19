package com.s23010166.helplink;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CreateActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    // UI Components
    private ImageView btnBack;
    private Spinner spinnerCategory;
    private EditText etTitle, etDescription, etAmount, etContact;
    private Button btnAddPhoto, btnAddGallery, btnSubmit, btnCancel;
    private ImageView ivPreview;

    // Image handling
    private Uri selectedImageUri;
    private Bitmap capturedBitmap;

    // Activity Result Launchers
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        initViews();
        setupSpinner();
        setupClickListeners();
        initActivityResultLaunchers();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btn_back);
        spinnerCategory = findViewById(R.id.spinner_category);
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etAmount = findViewById(R.id.et_amount);
        etContact = findViewById(R.id.et_contact);
        btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnAddGallery = findViewById(R.id.btn_add_gallery);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);
        ivPreview = findViewById(R.id.iv_preview);
    }

    private void setupSpinner() {
        String[] categories = {
                "Select Category",
                "Medical",
                "Education",
                "Emergency",
                "Food & Shelter",
                "Transportation",
                "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnAddPhoto.setOnClickListener(v -> {
            if (checkCameraPermission()) {
                openCamera();
            } else {
                requestCameraPermission();
            }
        });

        btnAddGallery.setOnClickListener(v -> {
            if (checkStoragePermission()) {
                openGallery();
            } else {
                requestStoragePermission();
            }
        });

        btnSubmit.setOnClickListener(v -> submitPost());

        btnCancel.setOnClickListener(v -> finish());
    }

    private void initActivityResultLaunchers() {
        // Camera launcher
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle extras = result.getData().getExtras();
                            capturedBitmap = (Bitmap) extras.get("data");
                            ivPreview.setImageBitmap(capturedBitmap);
                            ivPreview.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        // Gallery launcher
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            selectedImageUri = result.getData().getData();
                            ivPreview.setImageURI(selectedImageUri);
                            ivPreview.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_CODE);
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_PERMISSION_CODE);
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            cameraLauncher.launch(cameraIntent);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }

    private void submitPost() {
        // Validate input
        if (!validateInput()) {
            return;
        }






        // savePostToDatabase(newPost);

        Toast.makeText(this, "Help request posted successfully!", Toast.LENGTH_SHORT).show();

        // Return to main feed
        Intent resultIntent = new Intent();
        resultIntent.putExtra("new_post", true);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private boolean validateInput() {
        if (spinnerCategory.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etTitle.getText().toString().trim())) {
            etTitle.setError("Title is required");
            etTitle.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etDescription.getText().toString().trim())) {
            etDescription.setError("Description is required");
            etDescription.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etContact.getText().toString().trim())) {
            etContact.setError("Contact information is required");
            etContact.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
                break;

            case STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}