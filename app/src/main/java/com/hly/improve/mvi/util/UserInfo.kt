package com.hly.improve.mvi.util

import com.hly.improve.mvi.data.model.Support
import com.hly.improve.mvi.data.model.User

//定义类似JavaBean实体类
data class UserInfo(val page : Int, val perPage : Int, val totalPages : Int, val data : List<User>, val support : Support)