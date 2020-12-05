package com.example.twitchgametest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.twitchgametest.database.dao.GameDao
import com.example.twitchgametest.entity.GameEntity
import com.example.twitchgametest.entity.ImageEntity

@Database(
    entities = [
        GameEntity::class,
        ImageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GameDataBase : RoomDatabase() {

    abstract val gameDao: GameDao

}