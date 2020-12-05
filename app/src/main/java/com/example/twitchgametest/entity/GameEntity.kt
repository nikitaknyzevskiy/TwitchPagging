package com.example.twitchgametest.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class GameEntity(
    @PrimaryKey
    val _id: Int,
    val name: String,
    val viewers: Long = 0,
    val channels: Long = 0
)