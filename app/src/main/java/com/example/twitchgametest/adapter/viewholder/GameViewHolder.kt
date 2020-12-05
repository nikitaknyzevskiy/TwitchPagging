package com.example.twitchgametest.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitchgametest.R
import com.example.twitchgametest.entity.full.GameFull
import com.squareup.picasso.Picasso

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val bannerImg = itemView.findViewById<ImageView>(R.id.game_banner)
    private val titleTxt = itemView.findViewById<TextView>(R.id.game_title)
    private val viewsTxt = itemView.findViewById<TextView>(R.id.game_views)
    private val channelsTxt = itemView.findViewById<TextView>(R.id.game_chanels)


    fun bind(gameFull: GameFull) {
        if (gameFull.box != null) {
            Picasso
                .get()
                .load(gameFull.box.large)
                .into(bannerImg)
        }

        titleTxt.text = gameFull.info.name
        viewsTxt.text = gameFull.info.viewers.toString()
        channelsTxt.text = gameFull.info.channels.toString()
    }

}