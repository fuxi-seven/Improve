package com.hly.improve.mvi.ui.main.intent

import com.hly.improve.mvi.data.model.User

sealed class MainState {

    object Idle : MainState()
    object Prepare : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}
