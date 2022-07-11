package com.hly.improve.ui.composeWidget

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.hly.improve.ImageTest
import com.hly.improve.ui.composeWidget.NetworkImgWidget.Companion.NetworkImg

class MessageWidget {

    companion object {
        @Composable
        fun MessageList(messages: List<String>) {
            val current = LocalContext.current// 以是 List或者Array两种方式
            // LazyColumn这个控件类似android中的listview
            LazyColumn {
                /*Modifier.clickable(onClick = {
                    Toast.makeText(current, "点击了", Toast.LENGTH_LONG).show()
                })
                items(messages.size) { message ->//遍历索引的方式
                    Text(text = messages[message])
                }
                items(items = messages) { message -> //遍历内容
                    Text(text = message)
                }*/
                //itemsIndexed代表每个item布局,跟listview里面的item布局一个意思
                itemsIndexed(items = messages) { index, item -> //遍历内容和索引
                    if (index == 0) {
                        //角标0位置设置不一样的布局
                        ImageTest()
                    }
                    //这里是每个item对应的标题(角标0除外)
                    Text(
                        text = "索引:$index -- 内容:$item",
                        Modifier.clickable(enabled = true, onClick = {
                            Toast.makeText(current, "点击了$item", Toast.LENGTH_SHORT).show()
                        }).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    //这里是列表需要展示的图片
                    NetworkImg(index)
                }
            }
        }
    }
}