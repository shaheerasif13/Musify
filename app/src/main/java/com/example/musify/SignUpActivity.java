package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView backButton;
    private TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Switching back to sign up options screen
        this.backButton = findViewById(R.id.back_button);

        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Switching to home screen
        this.signUpButton = findViewById(R.id.sign_up_button);

        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}