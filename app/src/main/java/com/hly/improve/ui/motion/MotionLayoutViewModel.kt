package com.hly.improve.ui.motion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MotionLayoutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is motionLayout Study"
    }
    val text: LiveData<String> = _text
}