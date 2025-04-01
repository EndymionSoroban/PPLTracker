package com.example.ppltracker.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class for Exercise table
 */
@Entity(tableName = "exercises")
public class ExerciseEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private double oneRepMax;
    private double currentWeight;
    private double progressionRate;
    private int sets;
    private int minReps;
    private int maxReps;
    private boolean lastSetAmrap;
    private String category; // "PUSH", "PULL", "LEGS"

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

    public double getOneRepMax() {
        return oneRepMax;
    }

    public void setOneRepMax(double oneRepMax) {
        this.oneRepMax = oneRepMax;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getProgressionRate() {
        return progressionRate;
    }

    public void setProgressionRate(double progressionRate) {
        this.progressionRate = progressionRate;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getMinReps() {
        return minReps;
    }

    public void setMinReps(int minReps) {
        this.minReps = minReps;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(int maxReps) {
        this.maxReps = maxReps;
    }

    public boolean isLastSetAmrap() {
        return lastSetAmrap;
    }

    public void setLastSetAmrap(boolean lastSetAmrap) {
        this.lastSetAmrap = lastSetAmrap;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}