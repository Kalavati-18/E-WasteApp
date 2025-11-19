package com.example.e_waste_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProfileDetailsActivity extends AppCompatActivity {

    ImageView profileImg, postImg;
    TextView nameTxt, emailTxt, phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        profileImg = findViewById(R.id.profileImg);
        postImg = findViewById(R.id.postImg);
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);

        Intent i = getIntent();

        nameTxt.setText(i.getStringExtra("name"));
        emailTxt.setText(i.getStringExtra("email"));
        phoneTxt.setText(i.getStringExtra("phone"));

        Picasso.get()
                .load(i.getStringExtra("profileUrl"))
                .placeholder(R.drawable.ic_person)
                .into(profileImg);

        Picasso.get()
                .load(i.getStringExtra("imageUrl"))
                .placeholder(R.drawable.ic_image)
                .into(postImg);
    }
}
