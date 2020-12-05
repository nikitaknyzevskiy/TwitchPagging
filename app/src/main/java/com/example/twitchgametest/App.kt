package com.example.twitchgametest

import android.app.Application
import com.example.twitchgametest.database.DatabaseBuilder
import com.example.twitchgametest.database.GameDataBase

lateinit var database: GameDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        database = DatabaseBuilder.Builder<GameDataBase>()
            .context(this)
            .database(GameDataBase::class.java)
            .name("game_db")
            .build()
    }

}