package com.example.cronometre_24_25;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        Button startButton = findViewById(R.id.button_start);
        Button stopButton = findViewById(R.id.button_stop);
        Button resetButton = findViewById(R.id.button_reset);

        if (chronometer == null || startButton == null || stopButton == null || resetButton == null) {
            Toast.makeText(this, R.string.initialization_error, Toast.LENGTH_SHORT).show();
            return;
        }

        // Configure the chronometer initial format and base time.
        chronometer.setFormat(getString(R.string.time_format));
        chronometer.setBase(SystemClock.elapsedRealtime());

        // Listen to ticks and reset automatically with an alert after 10 seconds.
        chronometer.setOnChronometerTickListener(ch -> {
            if ((SystemClock.elapsedRealtime() - ch.getBase()) >= 10_000) {
                resetChronometer();
                Toast.makeText(MainActivity.this, R.string.bing_message, Toast.LENGTH_SHORT).show();
            }
        });

        startButton.setOnClickListener(this::startChronometer);
        stopButton.setOnClickListener(v -> stopChronometer());
        resetButton.setOnClickListener(v -> resetChronometer());
    }

    private void startChronometer(View view) {
        if (chronometer != null && !isRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            isRunning = true;
        }
    }

    // Stop the chronometer while preserving elapsed time for a future resume.
    private void stopChronometer() {
        if (chronometer != null && isRunning) {
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isRunning = false;
        }
    }

    // Reset the chronometer to 00:00 and clear any paused elapsed offset.
    private void resetChronometer() {
        if (chronometer != null) {
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0L;
            isRunning = false;
        }
    }
}
