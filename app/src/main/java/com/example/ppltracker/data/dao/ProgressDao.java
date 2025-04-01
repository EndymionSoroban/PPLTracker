package com.example.ppltracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ppltracker.data.entities.ProgressEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Access Object for Progress
 */
@Dao
public interface ProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ProgressEntity progress);

    @Update
    void update(ProgressEntity progress);

    @Delete
    void delete(ProgressEntity progress);

    @Query("SELECT * FROM progress WHERE exerciseId = :exerciseId ORDER BY date")
    LiveData<List<ProgressEntity>> getProgressForExercise(long exerciseId);

    @Query("SELECT * FROM progress WHERE exerciseId = :exerciseId AND date BETWEEN :startDate AND :endDate ORDER BY date")
    LiveData<List<ProgressEntity>> getProgressForExerciseInDateRange(long exerciseId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT * FROM progress WHERE exerciseId = :exerciseId ORDER BY date DESC LIMIT 1")
    ProgressEntity getLatestProgressForExercise(long exerciseId);

    @Query("SELECT MAX(weight) FROM progress WHERE exerciseId = :exerciseId")
    double getMaxWeightForExercise(long exerciseId);

    @Query("SELECT MIN(weight) FROM progress WHERE exerciseId = :exerciseId")
    double getMinWeightForExercise(long exerciseId);

    @Query("SELECT * FROM progress WHERE exerciseId = :exerciseId ORDER BY date DESC LIMIT :limit")
    List<ProgressEntity> getRecentProgressForExercise(long exerciseId, int limit);
}