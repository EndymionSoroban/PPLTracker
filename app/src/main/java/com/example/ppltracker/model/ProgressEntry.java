package com.example.ppltracker.model;

import java.time.LocalDate;

/**
 * Model class for ProgressEntry
 */
public class ProgressEntry {
    private long id;
    private LocalDate date;
    private double weight;
    private int reps;

    public ProgressEntry() {
        this.date = LocalDate.now();
    }

    public ProgressEntry(LocalDate date, double weight, int reps) {
        this.date = date;
        this.weight = weight;
        this.reps = reps;
    }

    /**
     * Calculate estimated one-rep max using Epley formula
     * 1RM = w * (1 + r/30)
     */
    public double calculateEstimatedOneRM() {
        if (reps == 1) {
            return weight;
        }
        return weight * (1 + reps / 30.0);
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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