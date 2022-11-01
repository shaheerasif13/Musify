package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeScreenActivity extends AppCompatActivity {

    private static final String TAG = "HomeScreenActivity";
    private FirebaseAuth firebaseAuth;
    private ImageView menuButton;
    private DrawerLayout profileDrawer;
    private RelativeLayout logOutRelativeLayout;
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.menuButton = findViewById(R.id.menu_button);
        this.profileDrawer = findViewById(R.id.profile_drawer);
        this.logOutRelativeLayout = findViewById(R.id.home_screen_logout);
        this.usernameTextView = findViewById(R.id.home_screen_username);

        // To disable swapping gesture to open and close drawer
        this.profileDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // Opening profile drawer on menu button click
        this.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!profileDrawer.isDrawerOpen(Gravity.LEFT)) {
                    profileDrawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        // Logging user out on logout button press
        this.logOutRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutUser();
            }
        });
    }

    // Method to sign user out
    public void signOutUser() {
        firebaseAuth.signOut();
        Toast.makeText(HomeScreenActivity.this, "Signed out successfully!", Toast.LENGTH_SHORT).show();

        // Navigating to start screen after sign out
        startActivity(new Intent(HomeScreenActivity.this, SignInActivity.class));
        finish();
    }
}