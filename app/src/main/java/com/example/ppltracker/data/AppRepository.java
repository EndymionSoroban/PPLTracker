package com.example.ppltracker.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.ppltracker.model.Exercise;
import com.example.ppltracker.model.ProgressEntry;
import com.example.ppltracker.model.UserSettings;
import com.example.ppltracker.model.Workout;
import com.example.ppltracker.model.WorkoutExercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that acts as a single source of truth for data
 * This would interact with Room database and other data sources
 */
public class AppRepository {
    private static final String PREFS_NAME = "ppl_tracker_prefs";
    private Context context;
    private AppDatabase database;
    private SharedPreferences preferences;

    public AppRepository(Context context) {
        this.context = context;

        // Initialize database
        database = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "ppl_tracker_database")
                .fallbackToDestructiveMigration()
                .build();

        // Initialize shared preferences
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // User Settings methods

    public UserSettings getUserSettings() {
        // Using getInstance() instead of new UserSettings()
        UserSettings settings = UserSettings.getInstance();
        settings.setWeightUnit(preferences.getString("weight_unit", "lbs"));
        settings.setRounding(preferences.getFloat("weight_rounding", 5.0f));
        return settings;
    }

    public void saveUserSettings(UserSettings settings) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("weight_unit", settings.getWeightUnit());
        editor.putFloat("weight_rounding", (float) settings.getRounding());
        editor.apply();
    }

    // Workout methods

    public Workout getNextWorkout() {
        // In a real app, this would come from the database
        // For demo purposes, we create and return a sample workout
        return createSampleWorkout();
    }

    public void saveWorkout(Workout workout) {
        // This would save the workout to the database
        // For demo purposes, we just print a success message
        System.out.println("Workout saved: " + workout.getName());
    }

    public List<Workout> getCompletedWorkouts() {
        // This would get completed workouts from the database
        // For demo purposes, we return sample data
        return getSampleWorkoutHistory();
    }

    // Exercise methods

    public List<Exercise> getAllExercises() {
        // This would get all exercises from the database
        // For demo purposes, we return sample data
        return getSampleExercises();
    }

    public void updateExercise(Exercise exercise) {
        // This would update the exercise in the database
        // For demo purposes, we just print a success message
        System.out.println("Exercise updated: " + exercise.getName());
    }

    // Progress methods

    public List<ProgressEntry> getProgressForExercise(String exerciseName) {
        // This would get progress entries for the exercise from the database
        // For demo purposes, we return sample data
        return getSampleProgressHistory(exerciseName);
    }

    public void saveProgressEntry(String exerciseName, ProgressEntry entry) {
        // This would save the progress entry to the database
        // For demo purposes, we just print a success message
        System.out.println("Progress saved for " + exerciseName + ": " + entry.getWeight() + " x " + entry.getReps());
    }

    // Helper methods for sample data

    private Workout createSampleWorkout() {
        Workout workout = new Workout();
        workout.setName("Push Day 1");
        workout.setDate(LocalDate.now());

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

    private List<Workout> getSampleWorkoutHistory() {
        List<Workout> history = new ArrayList<>();

        // Push Day 1
        Workout pushDay = new Workout();
        pushDay.setName("Push Day 1");
        pushDay.setDate(LocalDate.now().minusDays(3));
        pushDay.setCompleted(true);

        // Pull Day 1
        Workout pullDay = new Workout();
        pullDay.setName("Pull Day 1");
        pullDay.setDate(LocalDate.now().minusDays(5));
        pullDay.setCompleted(true);

        // Legs Day 1
        Workout legsDay = new Workout();
        legsDay.setName("Legs Day 1");
        legsDay.setDate(LocalDate.now().minusDays(7));
        legsDay.setCompleted(true);

        history.add(pushDay);
        history.add(pullDay);
        history.add(legsDay);

        return history;
    }

    private List<Exercise> getSampleExercises() {
        List<Exercise> exercises = new ArrayList<>();

        Exercise benchPress = new Exercise();
        benchPress.setName("Bench Press");
        benchPress.setSets(4);
        benchPress.setMinReps(5);
        benchPress.setMaxReps(5);
        benchPress.setCurrentWeight(205.0);
        benchPress.setOneRepMax(225.0);
        benchPress.setProgressionRate(5.0);
        benchPress.setLastSetAmrap(true);

        Exercise squat = new Exercise();
        squat.setName("Squat");
        squat.setSets(3);
        squat.setMinReps(5);
        squat.setMaxReps(5);
        squat.setCurrentWeight(225.0);
        squat.setOneRepMax(250.0);
        squat.setProgressionRate(5.0);
        squat.setLastSetAmrap(true);

        Exercise deadlift = new Exercise();
        deadlift.setName("Deadlift");
        deadlift.setSets(1);
        deadlift.setMinReps(5);
        deadlift.setMaxReps(5);
        deadlift.setCurrentWeight(275.0);
        deadlift.setOneRepMax(300.0);
        deadlift.setProgressionRate(10.0);
        deadlift.setLastSetAmrap(true);

        exercises.add(benchPress);
        exercises.add(squat);
        exercises.add(deadlift);

        return exercises;
    }

    private List<ProgressEntry> getSampleProgressHistory(String exerciseName) {
        List<ProgressEntry> progress = new ArrayList<>();

        if (exerciseName.equals("Bench Press")) {
            progress.add(new ProgressEntry(LocalDate.now().minusDays(28), 185.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(21), 190.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(14), 195.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(7), 200.0, 5));
            progress.add(new ProgressEntry(LocalDate.now(), 205.0, 6));
        } else if (exerciseName.equals("Squat")) {
            progress.add(new ProgressEntry(LocalDate.now().minusDays(26), 205.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(19), 210.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(12), 215.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(5), 220.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(2), 225.0, 5));
        } else if (exerciseName.equals("Deadlift")) {
            progress.add(new ProgressEntry(LocalDate.now().minusDays(24), 245.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(17), 255.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(10), 265.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(3), 275.0, 5));
        } else {
            // Generic progress for other exercises
            progress.add(new ProgressEntry(LocalDate.now().minusDays(28), 100.0, 8));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(21), 105.0, 8));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(14), 110.0, 8));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(7), 115.0, 8));
            progress.add(new ProgressEntry(LocalDate.now(), 120.0, 9));
        }

        return progress;
    }
}