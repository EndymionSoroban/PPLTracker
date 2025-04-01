package com.example.ppltracker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ppltracker.R;
import com.example.ppltracker.data.AppRepository;
import com.example.ppltracker.databinding.ActivityHistoryBinding;

import java.time.LocalDate;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding binding;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize repository
        repository = new AppRepository(this);

        setupCalendar();
        setupWorkoutList();
        setupButtons();
    }

    private void setupCalendar() {
        CalendarView calendarView = binding.calendarWorkouts;
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // When a date is selected, show workouts for that date
            LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
            updateWorkoutsForDate(selectedDate);
        });
    }

    private void updateWorkoutsForDate(LocalDate date) {
        // In a real app, this would query the database for workouts on the selected date
        // For the demo, just display a message or update UI
    }

    private void setupWorkoutList() {
        // In a real app, this would load recent workouts from the repository
        // For the demo, sample data is already in the layout

        // Set click listeners for workout details buttons
        binding.buttonViewWorkout1.setOnClickListener(v -> {
            // Open workout details
        });

        binding.buttonViewWorkout2.setOnClickListener(v -> {
            // Open workout details
        });
    }

    private void setupButtons() {
        binding.buttonViewAllHistory.setOnClickListener(v -> {
            // Show complete workout history
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}