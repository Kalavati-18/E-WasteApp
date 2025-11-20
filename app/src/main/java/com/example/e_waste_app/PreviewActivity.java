package com.example.e_waste_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class PreviewActivity extends AppCompatActivity {

    ImageView imgPreview;
    Button btnUpload, btnRetake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imgPreview = findViewById(R.id.imgPreview);
        btnUpload = findViewById(R.id.btnUpload);
        btnRetake = findViewById(R.id.btnRetake);

        // Get bitmap from MainActivity
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("image");

        if (bitmap != null) {
            imgPreview.setImageBitmap(bitmap);
        }

        btnRetake.setOnClickListener(v -> {
            finish(); // go back to camera
        });

        btnUpload.setOnClickListener(v -> {
            // TODO: Upload to Firebase Storage OR database
        });
    }
}
