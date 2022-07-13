package com.hly.improve.mvi.data.model

import com.squareup.moshi.Json

data class Support(@Json(name = "url")
                   val url: String = "",
                   @Json(name = "text")
                   val text: String = "")
