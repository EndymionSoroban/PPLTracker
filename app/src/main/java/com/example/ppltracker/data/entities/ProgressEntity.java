package com.example.ppltracker.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ppltracker.data.Converters;

import java.time.LocalDate;

/**
 * Entity class for Progress table
 */
@Entity(tableName = "progress",
        foreignKeys = @ForeignKey(
                entity = ExerciseEntity.class,
                parentColumns = "id",
                childColumns = "exerciseId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = @Index("exerciseId"))
public class ProgressEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long exerciseId;

    @TypeConverters(Converters.class)
    private LocalDate date;

    private double weight;
    private int reps;

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}