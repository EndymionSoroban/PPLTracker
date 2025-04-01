package com.example.ppltracker.data;

import androidx.room.TypeConverter;

import java.time.LocalDate;

/**
 * Type converters for Room database
 */
public class Converters {

    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : LocalDate.ofEpochDay(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDate date) {
        return date == null ? null : date.toEpochDay();
    }


}