package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

public class HomeScreenActivity extends AppCompatActivity {

    private ImageView menuButton;
    private DrawerLayout profileDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        this.menuButton = findViewById(R.id.menu_button);
        this.profileDrawer = findViewById(R.id.profile_drawer);

        // To disable swapping gesture to open and close drawer
        profileDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // Opening profile drawer on menu button click
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!profileDrawer.isDrawerOpen(Gravity.LEFT)) {
                    profileDrawer.openDrawer(Gravity.LEFT);
                }
            }
        });
    }
}