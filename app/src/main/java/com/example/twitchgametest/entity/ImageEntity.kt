package com.example.twitchgametest.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val parentID: Int,
    val large: String,
    val medium: String,
    val small: String,
    val template: String
)