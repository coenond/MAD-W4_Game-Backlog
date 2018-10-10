package com.coen.mad_w4_game_backlog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.TextView
import com.coen.mad_w4_game_backlog.R
import com.coen.mad_w4_game_backlog.db.GameRepository
import com.coen.mad_w4_game_backlog.model.Game
import kotlinx.android.synthetic.main.activity_add_game.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditGameActivity : AppCompatActivity() {

    private val statusList = arrayOf("Want to Play", "Playing", "Stalled", "Dropped")
    lateinit var repo: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repo = GameRepository(this)
        val game = intent.getSerializableExtra("game") as Game

        val spinAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, statusList)
        sp_status.adapter = spinAdapter
        fillTextFields(game)

        fab_submit.setOnClickListener {
            onSubmit(game)
        }
    }

    private fun fillTextFields(game: Game) {
        et_title.setText(game.title, TextView.BufferType.EDITABLE)
        et_platform.setText(game.platform, TextView.BufferType.EDITABLE)
        et_notes.setText(game.note, TextView.BufferType.EDITABLE)

        val spinAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, statusList)
        sp_status.setSelection(spinAdapter.getPosition(game.status))
    }

    @SuppressLint("NewApi")
    private fun onSubmit(game: Game) {
        val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var date = LocalDateTime.now().format(dateFormat)

        game.apply {
            title = et_title.text.toString()
            platform = et_platform.text.toString()
            note = et_notes.text.toString()
            status = sp_status.selectedItem as String
            date = date
        }

        repo.update(game)
        finish()
    }

}