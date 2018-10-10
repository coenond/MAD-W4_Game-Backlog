package com.coen.mad_w4_game_backlog.db

import android.arch.lifecycle.LiveData
import android.content.Context
import com.coen.mad_w4_game_backlog.model.Game
import org.jetbrains.anko.doAsync

class GameRepository(context: Context) {
    var gameDao: GameDAO

    init {
        val gameDb = GameDatabase.getDatabase(context)
        gameDao = gameDb!!.gameDao()
    }

    fun getAll(): LiveData<List<Game>> {
        return gameDao.getAll()
    }

    fun insert(g: Game) {
        doAsync { gameDao.insert(g) }
    }


    fun delete(g: Game) {
        doAsync { gameDao.delete(g) }
    }

    fun update(g: Game) {
        doAsync { gameDao.update(g) }
    }

    fun deleteAll() {
        doAsync { gameDao.deleteAll() }
    }
}