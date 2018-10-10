package com.coen.mad_w4_game_backlog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.coen.mad_w4_game_backlog.R
import com.coen.mad_w4_game_backlog.db.GameRepository
import com.coen.mad_w4_game_backlog.model.Game
import kotlinx.android.synthetic.main.activity_add_game.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddGameActivity : AppCompatActivity() {

    val statusList = arrayOf( "Want to Play", "Playing", "Stalled", "Dropped" )
    lateinit var repo: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repo = GameRepository(this)

        fillDropdown()

        fab_submit.setOnClickListener {
            onSubmit()
        }
    }

    private fun fillDropdown() {
        val spinAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, statusList)
        sp_status.adapter = spinAdapter
    }

    @SuppressLint("NewApi")
    private fun onSubmit() {
        val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDateTime.now().format(dateFormat)

        val game = Game(
                et_title.text.toString(),
                et_platform.text.toString(),
                et_notes.text.toString(),
                sp_status.selectedItem as String,
                date
        )

//        if (bundle?.getSerializable(GAME_EXTRA) != null) game.gameId = (bundle?.getSerializable(GAME_EXTRA) as Game).gameId

        repo.insert(game)
        finish()
    }
}