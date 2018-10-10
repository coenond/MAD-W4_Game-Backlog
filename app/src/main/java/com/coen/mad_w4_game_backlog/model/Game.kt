package com.coen.mad_w4_game_backlog.model

import android.arch.persistence.room.ColumnInfo
import java.io.Serializable
import java.util.*
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "games")
data class Game(
        val title: String,
        val platform: String,
        val note: String,
        val status: String,
        val date: Date
): Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0

}