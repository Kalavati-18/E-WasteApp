package com.example.e_waste_app;

import static com.example.e_waste_app.R.id.signup_Confirm_Password;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText email, password, confirm_password;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPassword);
        confirm_password=findViewById(R.id.signup_Confirm_Password);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(v -> {
            String e = email.getText().toString();
            String p = password.getText().toString();
            String cp=confirm_password.getText().toString();

            if(e.isEmpty() || p.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                finish(); // go back to login
            }
        });
    }
}
