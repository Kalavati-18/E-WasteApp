package com.example.e_waste_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignIn;
    TextView tvGotoSignup;

    DatabaseHelper db; // Added DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this); // Initialize database

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvGotoSignup = findViewById(R.id.tvSignup);

        btnSignIn.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ SQLite check added
            if (db.checkUser(email, pass)) {

                // ✅ ADD THIS: store logged-in email
                getSharedPreferences("user_pref", MODE_PRIVATE)
                        .edit()
                        .putString("email", email)
                        .apply();

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });


        tvGotoSignup.setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class)));
    }
}
