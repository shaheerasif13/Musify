package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    private ImageView playPauseButton;
    private RelativeLayout currentSongBar;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.menuButton = findViewById(R.id.menu_button);
        this.profileDrawer = findViewById(R.id.profile_drawer);
        this.logOutRelativeLayout = findViewById(R.id.home_screen_logout);
        this.usernameTextView = findViewById(R.id.home_screen_username);
        this.playPauseButton = findViewById(R.id.home_screen_play_pause_music_button);
        this.currentSongBar = findViewById(R.id.current_song_bar);
        this.mediaPlayer = MediaPlayer.create(HomeScreenActivity.this, R.raw.milne_hai_mujhse_aayi);

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

        // Play and pause music when button is pressed
        this.playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPauseMusic();
            }
        });

        // Navigate to current playing song when current song bar is clicked
        this.currentSongBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Stopping music
                mediaPlayer.stop();

                startActivity(new Intent(HomeScreenActivity.this, CurrentPlayingSongActivity.class));
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up);
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

    // Method to play/pause music
    public void playPauseMusic() {

        // If music is playing pause music
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();

            // Changing button image from pause to play
            this.playPauseButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        }

        // If music is paused play it
        else {
            this.mediaPlayer.start();

            // Changing button image from play to pause
            this.playPauseButton.setImageResource(R.drawable.ic_baseline_pause_24);
        }
    }
}