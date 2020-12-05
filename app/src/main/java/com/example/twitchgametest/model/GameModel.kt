package com.example.twitchgametest.model

import com.example.twitchgametest.entity.ImageEntity

data class GameModel(
    val _id: Int,
    val name: String,
    val box: ImageEntity,
    val logo: ImageEntity,
    val localized_name: String,
    val locale: String
)