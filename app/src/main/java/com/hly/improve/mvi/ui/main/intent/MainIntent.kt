package com.hly.improve.mvi.ui.main.intent

sealed class MainIntent {

    object FetchUser : MainIntent()

    object PreFetch : MainIntent()
}
