package com.khaled.jokes.domain

import com.khaled.jokes.util.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}
