package com.example.ppltracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ppltracker.data.entities.ExerciseEntity;

import java.util.List;

/**
 * Data Access Object for Exercise
 */
@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ExerciseEntity exercise);

    @Update
    void update(ExerciseEntity exercise);

    @Delete
    void delete(ExerciseEntity exercise);

    @Query("SELECT * FROM exercises ORDER BY name")
    LiveData<List<ExerciseEntity>> getAllExercises();

    @Query("SELECT * FROM exercises WHERE category = :category ORDER BY name")
    LiveData<List<ExerciseEntity>> getExercisesByCategory(String category);

    @Query("SELECT * FROM exercises WHERE id = :id")
    ExerciseEntity getExerciseById(long id);

    @Query("SELECT * FROM exercises WHERE name = :name LIMIT 1")
    ExerciseEntity getExerciseByName(String name);
}