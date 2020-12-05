package com.example.twitchgametest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.twitchgametest.R
import com.example.twitchgametest.adapter.viewholder.GameViewHolder
import com.example.twitchgametest.entity.full.GameFull

class GameAdapter : PagingDataAdapter<GameFull, GameViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<GameFull>() {
            override fun areItemsTheSame(oldItem: GameFull, newItem: GameFull): Boolean = oldItem.info._id == newItem.info._id

            override fun areContentsTheSame(oldItem: GameFull, newItem: GameFull): Boolean = oldItem == newItem
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
       val itemView = LayoutInflater
           .from(parent.context)
           .inflate(
               R.layout.viewholder_game,
               parent,
               false
           )

        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)?.let {
            holder.bind(it)
        }
    }
}