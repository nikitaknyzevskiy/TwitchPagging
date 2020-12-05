package com.example.twitchgametest.entity.full

import androidx.room.Embedded
import androidx.room.Relation
import com.example.twitchgametest.entity.GameEntity
import com.example.twitchgametest.entity.ImageEntity

data class GameFull(
    @Embedded
    val info: GameEntity,

    @Relation(parentColumn = "_id", entityColumn = "parentID")
    val box: ImageEntity?,

    @Relation(parentColumn = "_id", entityColumn = "parentID")
    val logo: ImageEntity?
)