package com.example.ppltracker.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity class for WorkoutExercise table - junction table for Workout and Exercise
 */
@Entity(tableName = "workout_exercises",
        foreignKeys = {
                @ForeignKey(entity = WorkoutEntity.class,
                        parentColumns = "id",
                        childColumns = "workoutId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = ExerciseEntity.class,
                        parentColumns = "id",
                        childColumns = "exerciseId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("workoutId"),
                @Index("exerciseId")
        })
public class WorkoutExerciseEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long workoutId;
    private long exerciseId;
    private double workingWeight;
    private String repsPerformed; // Stored as JSON array
    private int lastSetReps;

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public double getWorkingWeight() {
        return workingWeight;
    }

    public void setWorkingWeight(double workingWeight) {
        this.workingWeight = workingWeight;
    }

    public String getRepsPerformed() {
        return repsPerformed;
    }

    public void setRepsPerformed(String repsPerformed) {
        this.repsPerformed = repsPerformed;
    }

    public int getLastSetReps() {
        return lastSetReps;
    }

    public void setLastSetReps(int lastSetReps) {
        this.lastSetReps = lastSetReps;
    }
}