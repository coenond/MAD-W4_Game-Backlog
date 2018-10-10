package com.coen.mad_w4_game_backlog.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.coen.mad_w4_game_backlog.model.Game

@Dao
interface GameDAO {

    @Insert
    fun insert(game: Game)

    @Delete
    fun delete(game: Game)

    @Update
    fun update(game: Game)

    @Query("DELETE FROM games")
    fun deleteAll()

    @Query("SELECT * from games")
    fun getAll(): LiveData<List<Game>>
}