package com.hly.improve.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val dataList: ArrayList<String> =
        arrayListOf("北京", "天津", "上海", "广州", "深圳", "济南", "青岛", "潍坊", "合肥", "长沙", "武汉", "哈尔滨", "沈阳")
}