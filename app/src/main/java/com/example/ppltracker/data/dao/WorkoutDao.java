package com.example.ppltracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.ppltracker.data.entities.WorkoutEntity;
import com.example.ppltracker.data.entities.WorkoutExerciseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Access Object for Workout
 */
@Dao
public interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWorkout(WorkoutEntity workout);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWorkoutExercise(WorkoutExerciseEntity workoutExercise);

    @Update
    void updateWorkout(WorkoutEntity workout);

    @Update
    void updateWorkoutExercise(WorkoutExerciseEntity workoutExercise);

    @Delete
    void deleteWorkout(WorkoutEntity workout);

    @Query("SELECT * FROM workouts WHERE id = :id")
    WorkoutEntity getWorkoutById(long id);

    @Query("SELECT * FROM workouts ORDER BY date DESC")
    LiveData<List<WorkoutEntity>> getAllWorkouts();

    @Query("SELECT * FROM workouts WHERE completed = 1 ORDER BY date DESC")
    LiveData<List<WorkoutEntity>> getCompletedWorkouts();

    @Query("SELECT * FROM workouts WHERE date = :date")
    LiveData<List<WorkoutEntity>> getWorkoutsByDate(LocalDate date);

    @Query("SELECT * FROM workout_exercises WHERE workoutId = :workoutId")
    List<WorkoutExerciseEntity> getWorkoutExercisesForWorkout(long workoutId);

    @Transaction
    @Query("SELECT * FROM workouts ORDER BY date DESC LIMIT 1")
    WorkoutEntity getLatestWorkout();

    @Query("SELECT * FROM workouts WHERE completed = 0 ORDER BY date ASC LIMIT 1")
    WorkoutEntity getNextWorkout();
}