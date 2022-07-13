package com.hly.improve.mvi.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hly.improve.mvi.data.repository.MainRepository
import com.hly.improve.mvi.ui.main.intent.MainIntent
import com.hly.improve.mvi.ui.main.intent.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    // channel本来是用来做协程之间通讯的,而我们的view层的触发操作和viewModel层获取数据这个流程恰巧应该是需要完全分离的，
    // 并且channel具备flow的特性，所以用channel来做view和viewModel的通讯非常适合
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    // state是个和Intent一样的枚举，但是不同的是intent是个事件流，state是个状态流
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            //订阅(注册)要处理的Intent以及对应的action
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                    is MainIntent.PreFetch -> preFetch()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            //在接收到Intent后进行处理，然后更新State值
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Users(repository.getUsers().data)
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

    private fun preFetch() {
        _state.value = MainState.Prepare
    }
}