package com.github.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.database.entity.DownloadEntity

@Database(
    entities = [
        DownloadEntity::class,
    ],
    version = 1
)

abstract class GuthubDatabase : RoomDatabase() {

    abstract fun getDownloadDao(): DownloadDao


}