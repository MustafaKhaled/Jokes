package com.khaled.jokes.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.khaled.jokes.R

object ErrorMessageHandler {
    fun execute(errorEntity: ErrorEntity, view: View) {
        when (errorEntity) {
            ErrorEntity.AccessDenied -> view.showSnackBar(
                R.string.access_denied_string,
                Snackbar.LENGTH_INDEFINITE
            ) {
                action(R.string.ok_snackbar_text) { this.dismiss() }
            }
            ErrorEntity.Network -> view.showSnackBar(
                R.string.no_internet_connection_msg,
                Snackbar.LENGTH_INDEFINITE
            ) {
                action(R.string.ok_snackbar_text) { this.dismiss() }
            }
            ErrorEntity.NotFound -> view.showSnackBar(
                R.string.data_not_found_msg,
                Snackbar.LENGTH_INDEFINITE
            ) {
                action(R.string.ok_snackbar_text) { this.dismiss() }
            }
            ErrorEntity.ServiceUnavailable -> view.showSnackBar(
                R.string.service_unavailable_msg,
                Snackbar.LENGTH_INDEFINITE
            ) {
                action(R.string.ok_snackbar_text) { this.dismiss() }
            }
            ErrorEntity.Unknown -> view.showSnackBar(
                R.string.general_error_msg,
                Snackbar.LENGTH_INDEFINITE
            ) {
                action(R.string.ok_snackbar_text) { this.dismiss() }
            }
        }

    }
}