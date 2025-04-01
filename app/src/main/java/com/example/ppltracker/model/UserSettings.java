package com.example.ppltracker.model;

/**
 * Singleton class for user settings
 */
public class UserSettings {
    private static UserSettings instance;

    private String weightUnit = "lbs"; // Default to lbs
    private double rounding = 5.0;     // Default to 5 lbs
    private int defaultRestTimer = 120; // Default to 2 minutes (120 seconds)
    private boolean workoutReminders = false;
    private String theme = "system";   // Default to system theme

    private UserSettings() {
        // Private constructor to enforce singleton pattern
    }

    public static UserSettings getInstance() {
        if (instance == null) {
            instance = new UserSettings();
        }
        return instance;
    }

    public static void setInstance(UserSettings settings) {
        instance = settings;
    }

    /**
     * Get weight unit with proper formatting
     */
    public String getFormattedWeightUnit() {
        return weightUnit.equals("kg") ? "kg" : "lbs";
    }

    // Getters and Setters

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;

        // Adjust rounding based on unit
        if (weightUnit.equalsIgnoreCase("kg")) {
            this.rounding = 2.5;
        } else {
            this.rounding = 5.0;
        }
    }

    public double getRounding() {
        return rounding;
    }

    public void setRounding(double rounding) {
        this.rounding = rounding;
    }

    public int getDefaultRestTimer() {
        return defaultRestTimer;
    }

    public void setDefaultRestTimer(int defaultRestTimer) {
        this.defaultRestTimer = defaultRestTimer;
    }

    public boolean isWorkoutReminders() {
        return workoutReminders;
    }

    public void setWorkoutReminders(boolean workoutReminders) {
        this.workoutReminders = workoutReminders;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}