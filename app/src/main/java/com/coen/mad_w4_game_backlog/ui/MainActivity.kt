package com.coen.mad_w4_game_backlog.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.coen.mad_w4_game_backlog.R
import com.coen.mad_w4_game_backlog.db.GameRepository
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
        rv_game_list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = gameAdapter
        }

        fab.setOnClickListener {
            val intent = Intent(this, AddGameActivity::class.java)
            startActivity(intent)
        }

//        val swipeCallback = GameAdapterSwipeCallback(cl_fab, gamesAdapter, gameRepository, this)
//        ItemTouchHelper(swipeCallback).attachToRecyclerView(rv_games)
    }

    private fun onGameClick(game: Game) {
//        val intent = Intent(this, onGameClick::class.java)
//        intent.putExtra(GAME_EXTRA, game)
//        startActivity(intent)
    }
}
