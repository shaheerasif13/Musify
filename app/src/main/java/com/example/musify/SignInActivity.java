package com.example.musify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
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

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private TextView signUpButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView signInButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Handle the splash screen transition.
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.signUpButton = findViewById(R.id.sign_up_button);
        this.emailEditText = findViewById(R.id.sign_in_email);
        this.passwordEditText = findViewById(R.id.sign_in_password);
        this.signInButton = findViewById(R.id.sign_in_button);
        this.firebaseAuth = FirebaseAuth.getInstance();

        // Switching to sign up options screen
        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpOptionsActivity.class);
                startActivity(intent);
            }
        });

        // Switching to home screen
        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Checking if user is already authenticated (Signed in)
        FirebaseUser firebaseUser = this.firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            Toast.makeText(this, firebaseUser.getUid(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, HomeScreenActivity.class));
            finish();
        }
    }

    // Method to sign in user
    public void signInUser() {

        // If input fields are not empty
        if (!emailEditText.getText().toString().equals("") && !passwordEditText.getText().toString().equals("")) {
            firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(SignInActivity.this, firebaseAuth.getUid(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, HomeScreenActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignInActivity.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        // If input fields are empty
        else {
            Toast.makeText(SignInActivity.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }
}