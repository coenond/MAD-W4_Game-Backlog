package com.coen.mad_w4_game_backlog.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.coen.mad_w4_game_backlog.model.Game

@Dao
interface GameDAO {

    @Insert
    fun insert(game: Game)

    @Update
    fun update(game: Game)

    @Query("DELETE FROM games")
    fun deleteAll()

    @Query("SELECT * from games")
    fun getAll(): LiveData<List<Game>>
}