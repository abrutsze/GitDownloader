package com.github.database

import com.github.database.entity.DownloadEntity
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: DownloadEntity)

    @Delete
    suspend fun delete(track: DownloadEntity)

    @Query("SELECT * FROM download_table")
    fun getDownloads(): Flow<List<DownloadEntity>>

}