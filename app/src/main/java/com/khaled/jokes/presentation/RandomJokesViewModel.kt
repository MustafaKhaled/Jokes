package com.khaled.jokes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaled.jokes.data.model.Jokes
import com.khaled.jokes.domain.usecases.RandomJokesUseCase
import com.khaled.jokes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomJokesViewModel @Inject constructor(private val randomJokesUseCase: RandomJokesUseCase) : ViewModel() {
    private val _randomJokesLiveData = MutableLiveData<Resource<Jokes?>>()
    val randomJokesLiveData: LiveData<Resource<Jokes?>>
        get() = _randomJokesLiveData

    fun getRandomJokes() {
        viewModelScope.launch {
            randomJokesUseCase.getRandomJokes().collect { state ->
                when (state) {
                    is Resource.Failure ->
                        _randomJokesLiveData.value = Resource.Failure(state.error)
                    Resource.Loading ->
                        _randomJokesLiveData.value = Resource.Loading
                    is Resource.Success ->
                        _randomJokesLiveData.value = Resource.Success(state.data)
                }
            }
        }
    }
}
