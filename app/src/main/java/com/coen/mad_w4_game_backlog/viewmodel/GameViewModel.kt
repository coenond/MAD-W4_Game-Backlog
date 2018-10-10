package com.coen.mad_w4_game_backlog.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.coen.mad_w4_game_backlog.db.GameRepository
import com.coen.mad_w4_game_backlog.model.Game

class GameViewModel(context: Context)  : ViewModel()  {

    private var repo: GameRepository = GameRepository(context)

    companion object {
        lateinit var allGames: LiveData<List<Game>>
    }

    init {
        allGames = repo.getAll()
    }

    fun insert(g: Game) { repo.insert(g) }
}