package com.example.fishingapp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton weatherBtn = findViewById(R.id.weatherBtn);
        ImageButton forecastBtn = findViewById(R.id.forecastBtn);
        ImageButton postsBtn = findViewById(R.id.postsBtn);

        weatherBtn.setOnClickListener(v -> {
            // Handle weather button click
        });
        forecastBtn.setOnClickListener(v -> {
            // Handle forecast button click
        });
        postsBtn.setOnClickListener(v -> {
            // Handle posts button click
        });
    }
}