package com.example.twitchgametest.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.twitchgametest.database
import com.example.twitchgametest.entity.GameEntity
import com.example.twitchgametest.entity.ImageEntity
import com.example.twitchgametest.entity.full.GameFull
import com.example.twitchgametest.network.AppRest
import com.example.twitchgametest.network.rest.GameRest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameRepository {

    private val gameDao = database.gameDao

    private val gameRest : GameRest = AppRest(GameRest::class.java)
        .addHeader("Client-ID", "sd4grh0omdj9a31exnpikhrmsu3v46")
        //.addHeader("Accept", "application/vnd.twitchtv.v5+json")
        .url("https://api.twitch.tv/kraken/")
        .build()

    fun load(page: Int) = GlobalScope.launch(Dispatchers.IO) {
        try {
            val gameList = gameRest.gameList(page)

            val games = arrayListOf<GameEntity>()
            val images = arrayListOf<ImageEntity>()

            for (gameInfo in gameList.top) {
                val gameEntity = GameEntity(
                    _id = gameInfo.game._id,
                    name = gameInfo.game.name,
                    viewers = gameInfo.viewers,
                    channels = gameInfo.channels
                )
                games.add(gameEntity)

                val imageBox = gameInfo.game.box.copy(parentID = gameEntity._id)
                val imageLogo = gameInfo.game.logo.copy(parentID = gameEntity._id)

                images.add(imageBox)
                images.add(imageLogo)
            }

            gameDao.saveImage(images)
            gameDao.save(games)

        } catch (e: Exception) {
            Log.e("GameRepository", "error", e)
        }
    }

    fun gamesList(): Pager<Int, GameFull> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = GameDataSource(this, gameDao),
        ) {
            gameDao.gamePages()
        }
    }



}