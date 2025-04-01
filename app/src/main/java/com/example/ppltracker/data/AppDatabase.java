package com.example.ppltracker.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ppltracker.data.dao.ExerciseDao;
import com.example.ppltracker.data.dao.ProgressDao;
import com.example.ppltracker.data.dao.WorkoutDao;
import com.example.ppltracker.data.entities.ExerciseEntity;
import com.example.ppltracker.data.entities.ProgressEntity;
import com.example.ppltracker.data.entities.WorkoutEntity;
import com.example.ppltracker.data.entities.WorkoutExerciseEntity;

/**
 * Main database class for Room
 */
@Database(
        entities = {
                ExerciseEntity.class,
                WorkoutEntity.class,
                WorkoutExerciseEntity.class,
                ProgressEntity.class
        },
        version = 1,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDao workoutDao();
    public abstract ProgressDao progressDao();
}