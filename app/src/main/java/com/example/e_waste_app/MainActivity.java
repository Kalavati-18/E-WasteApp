package com.example.e_waste_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnHome, btnInfo, btnUploadNav, btnProfile;
    FloatingActionButton fabCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = findViewById(R.id.btnHome);
        btnInfo = findViewById(R.id.btnInfo);
        btnUploadNav = findViewById(R.id.btnUploadNav);
        btnProfile = findViewById(R.id.btnProfile);
        fabCenter = findViewById(R.id.fabCenter);

        // default fragment
        replaceFragment(new HomeFragment());

        btnHome.setOnClickListener(v -> replaceFragment(new HomeFragment()));
//        btnInfo.setOnClickListener(v -> replaceFragment(new NGOListFragment()));
//        btnUploadNav.setOnClickListener(v -> replaceFragment(new UploadFragment()));
//        btnProfile.setOnClickListener(v -> replaceFragment(new ProfileFragment()));
//
//        fabCenter.setOnClickListener(v -> replaceFragment(new UploadFragment()));
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
