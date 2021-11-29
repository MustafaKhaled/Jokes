package com.khaled.jokes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaled.jokes.R
import com.khaled.jokes.data.model.JokeItem
import com.khaled.jokes.presentation.RandomJokesViewModel
import com.khaled.jokes.util.Constants.BOTTOM_SHEET_TAG
import com.khaled.jokes.util.CountingIdlingResourceSingleton
import com.khaled.jokes.util.ErrorMessageHandler
import com.khaled.jokes.util.Resource
import com.khaled.jokes.util.Util.updateLoadingVisibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_jokes.*

@AndroidEntryPoint
class JokesActivity : AppCompatActivity() {
    private val adapter = JokesAdapter()
    private val jokesViewModel: RandomJokesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        setupRecyclerView()
        observeLaunchTimes()
        pullToRefresh()

        jokesViewModel.getRandomJokes()
    }

    private fun observeLaunchTimes() {
        jokesViewModel.randomJokesLiveData.observe(this) { state ->
            when (state) {
                is Resource.Failure -> {
                    updateLoadingVisibility(swipeRefresh, false)
                    ErrorMessageHandler.execute(state.error, findViewById(R.id.swipeRefresh))
                    // For UI Test purpose
                    CountingIdlingResourceSingleton.decrement()
                }
                is Resource.Loading -> {
                    updateLoadingVisibility(swipeRefresh, true)
                    // For UI Test purpose
                    CountingIdlingResourceSingleton.increment()
                }
                is Resource.Success -> {
                    updateLoadingVisibility(swipeRefresh, false)
                    adapter.addAll(state.data?.jokes as ArrayList<JokeItem>)
                    // For UI Test purpose
                    CountingIdlingResourceSingleton.decrement()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        jokesList.adapter = adapter
        jokesList.setHasFixedSize(true)
        jokesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.onItemClick = {
            showBottomSheetDialog(it)
        }
    }

    private fun pullToRefresh() {
        swipeRefresh.setOnRefreshListener {
            jokesViewModel.getRandomJokes()
        }
    }

    private fun showBottomSheetDialog(jokeItem: JokeItem) {
        val bottomSheetDialog = JokeDetailsBottomSheet().newInstance(jokeItem)
        bottomSheetDialog.show(supportFragmentManager, BOTTOM_SHEET_TAG)
    }
}
