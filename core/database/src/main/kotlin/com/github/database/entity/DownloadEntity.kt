package com.github.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_table")
data class DownloadEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val fullName: String,
    val branches: String,
    val downloadsUrl: String,
    val url: String,
    val htmlUrl: String,
    val branchesUrl: String,
)
