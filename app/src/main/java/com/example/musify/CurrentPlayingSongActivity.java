package com.example.musify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class CurrentPlayingSongActivity extends AppCompatActivity {

    private static final String TAG = "CurrentPlayingSongActivity";
    private ImageView backTriangleButton;
    private ImageView playPauseButton;
    private SeekBar songProgressSeekBar;
    private boolean isSongPlaying;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_playing_song);

        this.backTriangleButton = findViewById(R.id.current_song_back_triangle_button);
        this.playPauseButton = findViewById(R.id.current_song_play_pause_button);
        this.songProgressSeekBar = findViewById(R.id.current_song_progress_seek_bar);
        this.mediaPlayer = MediaPlayer.create(CurrentPlayingSongActivity.this, R.raw.milne_hai_mujhse_aayi);

        // Going back to home screen when back button is tappe
        this.backTriangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Stopping music
                mediaPlayer.stop();

                startActivity(new Intent(CurrentPlayingSongActivity.this, HomeScreenActivity.class));
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
            }
        });

        // Play and pause music when button is pressed
        this.playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPauseMusic();
            }
        });

        // Setting current song progress on progress seek bar
        this.songProgressSeekBar.setMax(this.mediaPlayer.getDuration());

        // Seek bar auto progress as song is playing
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                isSongPlaying = true;
                songProgressSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 500);

        // Current song progress seek bar change listener
        this.songProgressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                // Seeking to current progress (i)
                if(!isSongPlaying){
                    mediaPlayer.seekTo(i);
                }

                else
                {
                    isSongPlaying = false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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