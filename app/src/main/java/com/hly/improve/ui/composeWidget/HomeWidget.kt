package com.hly.improve.ui.composeWidget

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hly.improve.ui.composeWidget.MessageWidget.Companion.MessageList

class HomeWidget {
    companion object {
        @Composable
        fun HomeShow() {
            // 获取图片
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.absolutePadding(20.dp, 20.dp, 20.dp, 20.dp)
            ) {
                Text(text = "风景列表展示", textAlign = TextAlign.Center, modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
                val current = LocalContext.current
                Button(
                    onClick = { Toast.makeText(current, "哈哈哈哈", Toast.LENGTH_SHORT).show() },
                    //水平居中显示
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text("点我")
                }
                //下面就是列表布局的数据
                MessageList(
                    listOf(
                        "Hello",
                        "Android",
                        "我超❤ JetPack Compose的！",
                        "三天打鱼",
                        "两天晒网",
                        "葫芦岛上卖葫芦",
                        "上海水来自海上",
                        "江东父老有江东",
                        "乌江自刎有霸王"
                    )
                )
            }
        }
    }
}