package com.example.e_waste_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnHome, btnInfo, btnUploadNav, btnProfile;
    FloatingActionButton fabCenter;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 101;
    Uri imageUri; // Added for full-size camera image

    DatabaseHelper db; // Added DatabaseHelper
    int userId; // Logged-in user ID (set from LoginActivity or SharedPreferences)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this); // Initialize database
        userId = getLoggedInUserId(); // ✅ Get actual logged-in user ID

        btnHome = findViewById(R.id.btnHome);
        btnInfo = findViewById(R.id.btnInfo);
        btnUploadNav = findViewById(R.id.btnUploadNav);
        btnProfile = findViewById(R.id.btnProfile);
        fabCenter = findViewById(R.id.fabCenter);

        ImageButton btnInfo = findViewById(R.id.btnInfo);

        // default fragment
        replaceFragment(new HomeFragment());

        btnHome.setOnClickListener(v -> replaceFragment(new HomeFragment()));
//        btnInfo.setOnClickListener(v -> replaceFragment(new NGOListFragment()));
//        btnUploadNav.setOnClickListener(v -> replaceFragment(new UploadFragment()));
//        btnProfile.setOnClickListener(v -> replaceFragment(new ProfileFragment()));
//        fabCenter.setOnClickListener(v -> replaceFragment(new UploadFragment()));

        btnInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        // Camera functionality added to FAB
        fabCenter.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            } else {
                openCamera();
            }
        });

        btnUploadNav.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        });

    }

    // Open camera with full-size image
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // Save full-size
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // Save photo to SQLite for logged-in user
                db.addPost(userId, photo);

                Toast.makeText(this, "Photo saved!", Toast.LENGTH_SHORT).show();

                // Optionally, navigate to UploadFragment
//                replaceFragment(new UploadFragment());

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to save photo", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                db.addPost(userId, photo);
                Toast.makeText(this, "Photo saved!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to save photo", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            try {
                Uri selectedImageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                db.addPost(userId, bitmap); // save to database
                Toast.makeText(this, "Image added from gallery!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to add image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    // ✅ Correct method to get logged-in user ID from SharedPreferences
    private int getLoggedInUserId() {
        String email = getSharedPreferences("user_pref", MODE_PRIVATE)
                .getString("email", null);
        if (email != null) {
            return db.getUserIdByEmail(email); // use DatabaseHelper method
        }
        return -1; // fallback
    }
}
