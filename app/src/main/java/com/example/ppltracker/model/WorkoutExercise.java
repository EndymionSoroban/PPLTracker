package com.example.ppltracker.model;

/**
 * Model class for WorkoutExercise
 */
public class WorkoutExercise {
    private long id;
    private Exercise exercise;
    private double workingWeight;
    private int[] repsPerformed;
    private int lastSetReps;

    public WorkoutExercise(Exercise exercise) {
        this.exercise = exercise;
        this.workingWeight = exercise.getCurrentWeight();
        this.repsPerformed = new int[exercise.getSets()];
        this.lastSetReps = 0;
    }

    /**
     * Record reps for a set
     * @param setNumber Zero-based set index
     * @param reps Number of reps performed
     */
    public void recordSet(int setNumber, int reps) {
        if (setNumber >= 0 && setNumber < repsPerformed.length) {
            repsPerformed[setNumber] = reps;

            // If this is the last set, record for progression
            if (setNumber == repsPerformed.length - 1) {
                lastSetReps = reps;
            }
        }
    }

    /**
     * Calculate total volume for this exercise
     * Volume = Weight × Sets × Reps
     */
    public double calculateVolume() {
        double volume = 0;

        for (int reps : repsPerformed) {
            if (reps > 0) {
                volume += workingWeight * reps;
            }
        }

        return volume;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public double getWorkingWeight() {
        return workingWeight;
    }

    public void setWorkingWeight(double workingWeight) {
        this.workingWeight = workingWeight;
    }

    public int[] getRepsPerformed() {
        return repsPerformed;
    }

    public void setRepsPerformed(int[] repsPerformed) {
        this.repsPerformed = repsPerformed;
    }

    public int getLastSetReps() {
        return lastSetReps;
    }

    public void setLastSetReps(int lastSetReps) {
        this.lastSetReps = lastSetReps;
    }
}