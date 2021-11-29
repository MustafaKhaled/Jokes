package com.khaled.jokes.util

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.khaled.jokes.R

fun TextView.updateLaunchTimes(counter: Int){
    this.text = if(counter>1)
        this.context.getString(
            R.string.app_launch_times, counter,
            this.context.getString(R.string.times_label))
    else
        this.context.getString(
            R.string.app_launch_times, counter,
            this.context.getString(R.string.time_label))
}

fun View.showSnackBar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snackBar = Snackbar.make(this, resources.getString(messageRes), length)
    snackBar.f()
    snackBar.show()
}
fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    setAction(actionRes, listener)
    color?.let { setActionTextColor(color) }
}