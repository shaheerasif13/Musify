package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    private TextView signUpButton;
    private TextView signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Handle the splash screen transition.
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Switching to sign up options screen
        this.signUpButton = findViewById(R.id.sign_up_button);

        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpOptionsActivity.class);
                startActivity(intent);
            }
        });

        // Switching to home screen
        this.signInButton = findViewById(R.id.sign_in_button);

        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}