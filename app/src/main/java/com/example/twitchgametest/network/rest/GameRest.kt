package com.example.twitchgametest.network.rest

import com.example.twitchgametest.model.GameInfo
import com.example.twitchgametest.model.PageModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GameRest {

    @GET("games/top")
    @Headers("Accept: application/vnd.twitchtv.v5+json")
    suspend fun gameList(@Query("offset") pageNum: Int) : PageModel<GameInfo>

}