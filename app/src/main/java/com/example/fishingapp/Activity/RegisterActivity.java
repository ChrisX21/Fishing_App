package com.example.fishingapp.Activity;

import static android.content.ContentValues.TAG;

import static com.example.fishingapp.Validator.Validator.validateEmail;
import static com.example.fishingapp.Validator.Validator.validatePassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fishingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
        private FirebaseAuth mAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            mAuth = FirebaseAuth.getInstance();

            EditText emailText = findViewById(R.id.emailText);
            EditText passwordText = findViewById(R.id.passwordText);
            EditText confirmPasswordText = findViewById(R.id.passwordRepeatText);
            Button registerButton = findViewById(R.id.registerBtn);

            registerButton.setOnClickListener(v -> {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String confirmPassword = confirmPasswordText.getText().toString();

                if(!password.equals(confirmPassword)){
                     confirmPasswordText.setError("Passwords do not match");
                }
                else if(!validateEmail(email)){
                    emailText.setError("Invalid Email");
                }
                else if(!validatePassword(password)){
                    passwordText.setError("Invalid Password");
                }
                else{
                    signUp(email, password);
                }
            });
        }

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (task.getException() != null) {
                                Log.e(TAG, "Sign up failed: " + task.getException().getMessage());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}