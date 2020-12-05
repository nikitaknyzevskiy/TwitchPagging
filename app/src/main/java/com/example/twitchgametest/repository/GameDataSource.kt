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

        val loadKey: Int? = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND ->
                return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                page++
                page
            }
        }

        gameRepository.load(loadKey?:0)

        return MediatorResult.Success(
            endOfPaginationReached = false
        )
    }

}