package com.example.twitchgametest.repository

import androidx.paging.*
import com.example.twitchgametest.database.dao.GameDao
import com.example.twitchgametest.entity.GameEntity
import com.example.twitchgametest.entity.full.GameFull
import com.example.twitchgametest.network.rest.GameRest

@OptIn(ExperimentalPagingApi::class)
class GameDataSource(
    private val gameRepository: GameRepository,
    private val gameDao: GameDao
) : RemoteMediator<Int, GameFull>() {

    private var page = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameFull>
    ): MediatorResult {

        try {
            gameRepository.load(page)
            page++
        } catch (e: Exception) {
            return MediatorResult.Success(
                    endOfPaginationReached = true
            )
        }

        return MediatorResult.Success(
            endOfPaginationReached = false
        )
    }

}