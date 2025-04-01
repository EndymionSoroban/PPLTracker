package com.example.ppltracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ppltracker.R;
import com.example.ppltracker.adapters.ExercisePreviewAdapter;
import com.example.ppltracker.data.AppRepository;
import com.example.ppltracker.databinding.ActivityMainBinding;
import com.example.ppltracker.model.Exercise;
import com.example.ppltracker.model.Workout;
import com.example.ppltracker.model.WorkoutExercise;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppRepository repository;
    private List<WorkoutExercise> nextWorkoutExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize repository
        repository = new AppRepository(this);

        setupNextWorkoutSection();
        setupProgressSection();
        setupButtonListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when returning to this activity
        setupNextWorkoutSection();
        setupProgressSection();
    }

    private void setupNextWorkoutSection() {
        // In a real app, this would come from the repository
        // For demo, we'll create sample data
        Workout nextWorkout = getSampleNextWorkout();

        binding.textWorkoutName.setText(nextWorkout.getName());

        // Set up RecyclerView
        binding.recyclerNextExercises.setLayoutManager(new LinearLayoutManager(this));
        ExercisePreviewAdapter adapter = new ExercisePreviewAdapter(nextWorkout.getExercises());
        binding.recyclerNextExercises.setAdapter(adapter);

        binding.buttonStartWorkout.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
            intent.putExtra("WORKOUT_NAME", nextWorkout.getName());
            startActivity(intent);
        });
    }

    private void setupProgressSection() {
        // Update progress bars for main lifts
        // In a real app, these would come from the repository
        binding.progressBench.setProgress(75);
        binding.progressSquat.setProgress(65);
        binding.progressDeadlift.setProgress(80);
    }

    private void setupButtonListeners() {
        binding.buttonViewProgress.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ProgressActivity.class));
        });

        binding.buttonHistory.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
        });

        binding.button1rm.setOnClickListener(view -> {
            // TODO: Implement 1RM calculator
        });

        binding.buttonSettings.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });

        binding.buttonEditProgram.setOnClickListener(view -> {
            // TODO: Implement program editor
        });
    }

    // Sample data for demonstration
    private Workout getSampleNextWorkout() {
        Workout workout = new Workout();
        workout.setName("Push Day 1");

        List<WorkoutExercise> exercises = new ArrayList<>();

        Exercise benchPress = new Exercise();
        benchPress.setName("Bench Press");
        benchPress.setSets(4);
        benchPress.setMinReps(5);
        benchPress.setMaxReps(5);
        benchPress.setCurrentWeight(205.0);
        benchPress.setLastSetAmrap(true);

        Exercise overheadPress = new Exercise();
        overheadPress.setName("Overhead Press");
        overheadPress.setSets(3);
        overheadPress.setMinReps(8);
        overheadPress.setMaxReps(12);
        overheadPress.setCurrentWeight(105.0);

        Exercise inclineDB = new Exercise();
        inclineDB.setName("Incline DB Press");
        inclineDB.setSets(3);
        inclineDB.setMinReps(8);
        inclineDB.setMaxReps(12);
        inclineDB.setCurrentWeight(95.0);

        exercises.add(new WorkoutExercise(benchPress));
        exercises.add(new WorkoutExercise(overheadPress));
        exercises.add(new WorkoutExercise(inclineDB));

        workout.setExercises(exercises);
        return workout;
    }
}