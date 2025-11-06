package com.example.e_waste_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupLink = findViewById(R.id.signupLink);

        loginBtn.setOnClickListener(v -> {
            String e = email.getText().toString();
            String p = password.getText().toString();

            if(e.isEmpty() || p.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                // you can go to next activity
                // startActivity(new Intent(this, HomeActivity.class));
            }
        });

        signupLink.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(i);
        });
    }
}
