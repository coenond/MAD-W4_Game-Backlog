package com.coen.mad_w4_game_backlog.model

import android.arch.persistence.room.ColumnInfo
import java.io.Serializable
import java.util.*
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "games")
data class Game(
        var title: String,
        var platform: String,
        var note: String,
        var status: String,
        var date: String
): Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0

}