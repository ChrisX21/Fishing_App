package com.example.fishingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fishingapp.MainActivity;
import com.example.fishingapp.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    Button registerButton = findViewById(R.id.registerBtn);
    Button loginButton = findViewById(R.id.loginBtn);

    registerButton.setOnClickListener(v -> {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    });

    loginButton.setOnClickListener(v -> {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    });
    }
}
