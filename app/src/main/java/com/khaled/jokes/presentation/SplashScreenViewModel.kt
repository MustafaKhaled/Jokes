package com.khaled.jokes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaled.jokes.domain.usecases.LaunchTimesUseCase
import com.khaled.jokes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val appLaunchTimesUseCase: LaunchTimesUseCase) : ViewModel() {
    private val _launchTimeLiveData = MutableLiveData<Resource<Int>>()
    val launchTimeLiveData: LiveData<Resource<Int>>
        get() = _launchTimeLiveData
    fun getAppLaunchTimes() {
        viewModelScope.launch {
            appLaunchTimesUseCase.getLaunchTimes().collect { state ->
                when(state){
                    is Resource.Failure ->
                        _launchTimeLiveData.value = Resource.Failure(state.error)
                    is Resource.Loading ->
                        _launchTimeLiveData.value = Resource.Loading
                    is Resource.Success ->
                        _launchTimeLiveData.value = Resource.Success(state.data)
                }
            }
        }

    }
}