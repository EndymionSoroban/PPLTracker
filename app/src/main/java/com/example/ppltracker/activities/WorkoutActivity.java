package com.example.ppltracker.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ppltracker.R;
import com.example.ppltracker.adapters.WorkoutExerciseAdapter;
import com.example.ppltracker.data.AppRepository;
import com.example.ppltracker.databinding.ActivityWorkoutBinding;
import com.example.ppltracker.model.Workout;
import com.example.ppltracker.model.WorkoutExercise;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Locale;

public class WorkoutActivity extends AppCompatActivity {

    private ActivityWorkoutBinding binding;
    private AppRepository repository;
    private CountDownTimer timer;
    private boolean timerRunning = false;
    private long timeLeftInMillis = 120000; // 2 minutes default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get workout name from intent
        String workoutName = getIntent().getStringExtra("WORKOUT_NAME");
        if (workoutName != null) {
            getSupportActionBar().setSubtitle(workoutName);
        }

        // Initialize repository
        repository = new AppRepository(this);

        setupTimerSection();
        setupExercisesSection();
        setupCompleteButton();
    }

    private void setupTimerSection() {
        updateTimerText();

        binding.buttonTimerStart.setOnClickListener(view -> {
            if (timerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });

        binding.buttonTimerReset.setOnClickListener(view -> {
            resetTimer();
        });

        binding.buttonTimer1min.setOnClickListener(view -> {
            timeLeftInMillis = 60000;
            updateTimerText();
        });

        binding.buttonTimer2min.setOnClickListener(view -> {
            timeLeftInMillis = 120000;
            updateTimerText();
        });

        binding.buttonTimer3min.setOnClickListener(view -> {
            timeLeftInMillis = 180000;
            updateTimerText();
        });
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                binding.buttonTimerStart.setText("START");
                timeLeftInMillis = 0;
                updateTimerText();

                // Alert the user
                Toast.makeText(WorkoutActivity.this, "Rest time finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        timerRunning = true;
        binding.buttonTimerStart.setText("PAUSE");
    }

    private void pauseTimer() {
        timer.cancel();
        timerRunning = false;
        binding.buttonTimerStart.setText("START");
    }

    private void resetTimer() {
        if (timerRunning) {
            timer.cancel();
            timerRunning = false;
        }
        timeLeftInMillis = 120000; // Reset to 2 minutes
        updateTimerText();
        binding.buttonTimerStart.setText("START");
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        binding.textTimer.setText(timeFormatted);
    }

    private void setupExercisesSection() {
        // In a real app, this would load the workout and exercises from the repository
        // For this demo, we're using hardcoded examples

        // For the demo, we're directly implementing the UI from activity_workout.xml
        // and not using a RecyclerView for simplicity

        // Set up the toggle groups for bench press sets
        binding.toggleBenchSet1.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                // Save the selected rep count
                // In a real app, this would update the workout data
            }
        });

        binding.toggleBenchSet2.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                // Save the selected rep count
            }
        });

        // The AMRAP set would save the value from the EditText
    }

    private void setupCompleteButton() {
        binding.buttonCompleteWorkout.setOnClickListener(view -> {
            // Check if any exercises are incomplete
            // For demo purposes, we'll just show a confirmation dialog

            new MaterialAlertDialogBuilder(this)
                    .setTitle("Complete Workout")
                    .setMessage("Are you sure you want to complete this workout?")
                    .setPositiveButton("Complete", (dialog, which) -> {
                        // In a real app, save the workout data to the repository
                        // and apply progression rules

                        Toast.makeText(this, "Workout completed successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        // Show confirmation dialog if workout is in progress
        new MaterialAlertDialogBuilder(this)
                .setTitle("Cancel Workout")
                .setMessage("Are you sure you want to cancel this workout? Your progress will be lost.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    super.onBackPressed();
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }
}