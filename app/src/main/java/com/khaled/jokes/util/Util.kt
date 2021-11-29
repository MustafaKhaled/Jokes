package com.khaled.jokes.util

import android.view.View
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object Util {
    fun updateLoadingVisibility(view: View, isShown: Boolean) {
        when (view) {
            is ProgressBar -> {
                if (isShown)
                    view.visibility = View.VISIBLE
                else
                    view.visibility = View.GONE
            }
            is SwipeRefreshLayout -> {
                view.isRefreshing = isShown
            }
        }
    }
}
