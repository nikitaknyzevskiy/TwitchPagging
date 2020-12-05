package com.example.twitchgametest.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseBuilder<DATABASE : RoomDatabase> private constructor(
    context: Context,
    dataBaseCls: Class<DATABASE>,
    name: String
) {

    val dataBase: DATABASE = Room.databaseBuilder(
        context,
        dataBaseCls,
        name
    ).build()

    class Builder<DATABASE : RoomDatabase>
    {
        private var context: Context? = null
        private var dataBaseCls: Class<DATABASE>? = null
        private var name: String? = null

        fun context(context: Context) = apply { this.context = context }

        fun database(database: Class<DATABASE>) = apply { this.dataBaseCls = database }

        fun name(name: String) = apply { this.name = name }

        fun build() = DatabaseBuilder(
            context = context!!,
            dataBaseCls = dataBaseCls!!,
            name = name!!
        ).dataBase
    }

}