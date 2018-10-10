package com.coen.mad_w4_game_backlog.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.coen.mad_w4_game_backlog.model.Game

@Database(entities = [Game::class], version = 1)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDAO

    /**
     * Use companion object to make methods public.
     */
    companion object {
        @Volatile
        var gameDatabaseInstance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {
            if (gameDatabaseInstance == null) {
                synchronized(GameDatabase::class.java) {
                    if (gameDatabaseInstance == null) {
                        gameDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                GameDatabase::class.java, "games")
                                .build()
                    }
                }
            }

            return gameDatabaseInstance
        }
    }
}