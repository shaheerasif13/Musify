package com.example.musify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private TextView backButton;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView signUpButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.backButton = findViewById(R.id.back_button);
        this.nameEditText = findViewById(R.id.sign_up_name);
        this.emailEditText = findViewById(R.id.sign_up_email);
        this.passwordEditText = findViewById(R.id.sign_up_password);
        this.signUpButton = findViewById(R.id.sign_up_button);
        this.firebaseAuth = FirebaseAuth.getInstance();

        // Switching back to sign up options screen
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Switching to home screen
        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
            }
        });
    }

    // Method to sign up user
    public void signUpUser() {

        // If input fields are empty
        if (nameEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
            Toast.makeText(SignUpActivity.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }

        // If fields are not empty
        else {
            firebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                // Initializing firebase user information
                                setUserProfileData(nameEditText.getText().toString());

                                Toast.makeText(SignUpActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(SignUpActivity.this, firebaseAuth.getUid(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, HomeScreenActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    // Method to set firebase user information
    public void setUserProfileData(String name) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        // Updating user profile here
        firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Log.d(TAG, "success");
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "failure");
                    }
                });
    }
}