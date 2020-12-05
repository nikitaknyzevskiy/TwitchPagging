package com.example.twitchgametest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.Pager
import com.example.twitchgametest.entity.GameEntity
import com.example.twitchgametest.entity.full.GameFull
import com.example.twitchgametest.repository.GameRepository

class MainViewModel : ViewModel() {

    private val gameRepository = GameRepository()

    fun games(): Pager<Int, GameFull> {
        return gameRepository.gamesList()
    }

    fun loadPage(pageNum: Int) {
        gameRepository.load(pageNum)
    }

}