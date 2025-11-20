package com.example.e_waste_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {

    TextView tvName, tvUsername, tvEmail, tvPhone;
    ImageView imgAvatar, imgPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        // Initialize views
        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);

        imgAvatar = findViewById(R.id.imgAvatar);
        imgPost = findViewById(R.id.imgPost);

        // Get data from Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String avatarUrl = getIntent().getStringExtra("avatarUrl");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Set values to views
        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);

        // Extract username from email (before @)
        if (email != null && email.contains("@")) {
            tvUsername.setText(email.split("@")[0]);
        } else {
            tvUsername.setText("");
        }

        // Load images using Picasso
        Picasso.get()
                .load(avatarUrl)
                .placeholder(R.drawable.ic_person)
                .into(imgAvatar);

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_image)
                .into(imgPost);
    }
}
