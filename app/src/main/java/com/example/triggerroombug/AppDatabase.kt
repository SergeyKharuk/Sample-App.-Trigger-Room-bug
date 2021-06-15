package com.example.triggerroombug

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        User::class, Car::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao

}