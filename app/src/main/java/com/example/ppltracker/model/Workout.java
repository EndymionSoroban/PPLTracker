package com.example.ppltracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for Workout
 */
public class Workout {
    private long id;
    private String name;
    private String category;
    private List<WorkoutExercise> exercises;
    private LocalDate date;
    private boolean completed;

    public Workout() {
        this.exercises = new ArrayList<>();
        this.date = LocalDate.now();
        this.completed = false;
    }

    /**
     * Add an exercise to the workout
     */
    public void addExercise(WorkoutExercise exercise) {
        exercises.add(exercise);
    }

    /**
     * Complete the workout and apply progression to exercises
     */
    public void completeWorkout() {
        this.completed = true;
        this.date = LocalDate.now();

        for (WorkoutExercise we : exercises) {
            // Apply progression based on last set reps
            if (we.getLastSetReps() > 0) {
                we.getExercise().applyProgression(we.getLastSetReps());
            }
        }
    }

    /**
     * Calculate total volume for the workout
     */
    public double calculateTotalVolume() {
        double totalVolume = 0;

        for (WorkoutExercise we : exercises) {
            double exerciseVolume = we.calculateVolume();
            totalVolume += exerciseVolume;
        }

        return totalVolume;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}