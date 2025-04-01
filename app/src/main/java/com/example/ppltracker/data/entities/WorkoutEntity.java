package com.example.ppltracker.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ppltracker.data.Converters;

import java.time.LocalDate;

/**
 * Entity class for Workout table
 */
@Entity(tableName = "workouts")
public class WorkoutEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String category; // "PUSH", "PULL", "LEGS"

    @TypeConverters(Converters.class)
    private LocalDate date;

    private boolean completed;

    // Getters and setters

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