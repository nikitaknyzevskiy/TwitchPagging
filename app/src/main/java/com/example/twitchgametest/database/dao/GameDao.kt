package com.example.twitchgametest.database.dao

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.twitchgametest.entity.GameEntity
import com.example.twitchgametest.entity.ImageEntity
import com.example.twitchgametest.entity.full.GameFull

@Dao
interface GameDao {

    @Query("select * from gameentity")
    @Transaction
    fun gamePages() : PagingSource<Int, GameFull>

    @Insert(onConflict = REPLACE)
    fun save(data: List<GameEntity>)

    @Insert(onConflict = REPLACE)
    fun saveImage(images: List<ImageEntity>)

}