package com.example.fishingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fishingapp.MainActivity;
import com.example.fishingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button registerButton = findViewById(R.id.registerBtn);
        Button loginButton = findViewById(R.id.loginBtn);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

    registerButton.setOnClickListener(v -> {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    });

        loginButton.setOnClickListener(v -> {
            String email = username.getText().toString();
            String pass = password.getText().toString();
            String signInResult = SignIn(email, pass);
            if (!signInResult.equals("successful")) {
                Toast.makeText(LoginActivity.this, signInResult, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    private String SignIn(String email, String password) {
        if(!validateEmail(email)){
            return "Invalid email";
        }
        if(!validatePassword(password)){
            return "Invalid password";
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                    }
                });
        return "successful";
    }

    private boolean validateEmail(String email){
        final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        //basic regex for email validation
        final Pattern pattern = Pattern.compile(emailRegex);

        if(email == null){
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean validatePassword(String password){
        final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{2,}$";
        //at least 2 characters, capital and small letters at least one of each and at least one number
        final Pattern pattern = Pattern.compile(passwordRegex);

        if(password == null){
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
