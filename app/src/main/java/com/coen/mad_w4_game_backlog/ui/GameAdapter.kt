package com.coen.mad_w4_game_backlog.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coen.mad_w4_game_backlog.R
import com.coen.mad_w4_game_backlog.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val onClickCallback: (Game?) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<Game>? = null
    private lateinit var context: Context

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvTitle.text = items?.get(position)?.title
            tvPlatform.text = items?.get(position)?.platform
            tvStatus.text = items?.get(position)?.status
            tvDate.text = items?.get(position)?.date

            cvGame.setOnClickListener {
                onClickCallback(items?.get(position))
            }
        }
    }

    fun update(items : ArrayList<Game>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Game? {
        return items?.get(position)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle = view.tv_title
    val tvPlatform = view.tv_platform
    val tvStatus = view.tv_status
    val tvDate = view.tv_date
    val cvGame = view.cv_game
}