package com.khaled.jokes.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.khaled.jokes.R
import com.khaled.jokes.presentation.SplashScreenViewModel
import com.khaled.jokes.util.ErrorMessageHandler
import com.khaled.jokes.util.Resource
import com.khaled.jokes.util.Util
import com.khaled.jokes.util.updateLaunchTimes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_splash_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash_screen)
        observeLaunchTimes()
        lifecycleScope.launch {
            splashScreenViewModel.getAppLaunchTimes()
            delay(3000)
            navigateJokesActivity()
        }
    }

    private fun observeLaunchTimes() {
        splashScreenViewModel.launchTimeLiveData.observe(this) { state ->
            when (state) {
                is Resource.Failure -> {
                    ErrorMessageHandler.execute(state.error, frameLayout)
                    Util.updateLoadingVisibility(progressBar, false)
                }
                is Resource.Loading -> {
                    Util.updateLoadingVisibility(progressBar, true)
                }
                is Resource.Success -> {
                    launchTimesTextView.updateLaunchTimes(state.data)
                }
            }
        }
    }

    private fun navigateJokesActivity() {
        startActivity(Intent(this, JokesActivity::class.java))
    }
}
