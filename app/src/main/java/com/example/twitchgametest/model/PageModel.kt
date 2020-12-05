package com.example.twitchgametest.model

data class PageModel<T>(
    val _total: Int,
    val top: List<T>
)