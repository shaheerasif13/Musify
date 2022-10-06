package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SignUpOptionsActivity extends AppCompatActivity {

    private TextView signInButton;
    private RelativeLayout continueWithCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_options);

        // Switching to sign in screen
        this.signInButton = findViewById(R.id.sign_in_button);

        this.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpOptionsActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Switching to sign up screen (Create account by email)
        this.continueWithCreateAccount = findViewById(R.id.continue_with_create_account);

        this.continueWithCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpOptionsActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}