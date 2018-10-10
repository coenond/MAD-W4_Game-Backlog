package com.coen.mad_w4_game_backlog.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.LinearLayout
import com.coen.mad_w4_game_backlog.R
import com.coen.mad_w4_game_backlog.db.GameRepository
import com.coen.mad_w4_game_backlog.helper.SwipeToDelete
import com.coen.mad_w4_game_backlog.model.Game
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameBacklogList: LiveData<List<Game>>
    private val gameAdapter = GameAdapter { if (it != null) onGameClick(it) }
    private lateinit var repo: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init repository
        repo = GameRepository(this)
        gameBacklogList = repo.getAll()
        gameBacklogList.observe(this, Observer {
            gameAdapter.update(it as ArrayList<Game>)
        })

        // Init RecyclerView
        rv_game_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_game_list.adapter = gameAdapter

        // Set Swipe to delete
        val onSwipe = object : SwipeToDelete(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_game_list.adapter as GameAdapter
                val game = adapter.getItem(viewHolder.adapterPosition)!!
                repo.delete(game)
                Snackbar.make(rv_game_list, "Game ${game.title} deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Undo") { _ -> repo.insert(game) }.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(onSwipe)
        itemTouchHelper.attachToRecyclerView(rv_game_list)

        fab.setOnClickListener {
            val intent = Intent(this, AddGameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onGameClick(game: Game) {
        val intent = Intent(this, EditGameActivity::class.java)
        intent.putExtra("game", game)
        startActivity(intent)
    }

}
