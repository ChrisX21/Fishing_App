package com.example.fishingapp.Activity;

import static com.example.fishingapp.Validator.Validator.validateEmail;
import static com.example.fishingapp.Validator.Validator.validatePassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fishingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button registerButton = findViewById(R.id.registerBtn);
        Button loginButton = findViewById(R.id.loginBtn);

        EditText emailText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String pass = passwordText.getText().toString();
            String signInResult = SignIn(email, pass);

            if(signInResult.equals("Invalid Email")){
                emailText.setError("Invalid Email");
            }
            else if(signInResult.equals("Invalid Password")){
                passwordText.setError("Invalid Password");
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
        if (validateEmail(email) == false) {
            return "Invalid Email";
        }
        if (validatePassword(password) == false) {
            return "Invalid Password";
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication failed. No user found.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return "successful";
    }

}
