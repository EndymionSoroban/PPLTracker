package com.example.ppltracker.model;

/**
 * Model class for Exercise
 */
public class Exercise {
    private long id;
    private String name;
    private double oneRepMax;
    private double currentWeight;
    private double progressionRate;
    private int sets;
    private int minReps;
    private int maxReps;
    private boolean lastSetAmrap;
    private String category;

    public Exercise() {
        this.progressionRate = 5.0; // Default progression rate
        this.sets = 3;              // Default sets
        this.minReps = 8;           // Default min reps
        this.maxReps = 12;          // Default max reps
        this.lastSetAmrap = false;  // Default AMRAP setting
        this.category = "PUSH";     // Default category
    }

    /**
     * Calculate initial weight based on 1RM and rep range
     */
    public double calculateInitialWeight() {
        double intensityPercentage = 0.0;
        switch (maxReps) {
            case 5:
                intensityPercentage = 0.85;  // 85% for 5 reps
                break;
            case 8:
                intensityPercentage = 0.75;  // 75% for 8 reps
                break;
            case 10:
                intensityPercentage = 0.70;  // 70% for 10 reps
                break;
            case 12:
                intensityPercentage = 0.65;  // 65% for 12 reps
                break;
            default:
                intensityPercentage = 0.70;  // Default to 70%
        }

        double weight = oneRepMax * intensityPercentage;
        // Round to nearest increment based on app settings
        double rounding = UserSettings.getInstance().getRounding();
        return Math.round(weight / rounding) * rounding;
    }

    /**
     * Apply progression based on reps achieved
     */
    public void applyProgression(int repsAchieved) {
        if (repsAchieved >= maxReps) {
            // Add weight based on progression rate
            currentWeight += progressionRate;
            // Round to nearest increment based on app settings
            double rounding = UserSettings.getInstance().getRounding();
            currentWeight = Math.round(currentWeight / rounding) * rounding;
        }
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

    public double getOneRepMax() {
        return oneRepMax;
    }

    public void setOneRepMax(double oneRepMax) {
        this.oneRepMax = oneRepMax;
        this.currentWeight = calculateInitialWeight();
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