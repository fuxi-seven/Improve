package com.hly.improve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.*
import com.hly.improve.ui.composeWidget.HomeWidget.Companion.HomeShow
import com.hly.improve.ui.theme.ImproveTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImproveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    //Text(text = "Hello $name!")
    HomeShow()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImproveTheme {
        Greeting("Android")
    }
}

@Composable
fun ImageTest() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            //.verticalScroll(state = rememberScrollState())
    ) {
        Image(
            painterResource(id = R.drawable.ic_home),
            contentDescription = "测试图片",
            modifier = Modifier.size(50.dp, 50.dp)
        )
        ImageItemShow(CircleCropTransformation())
        ImageItemShow(RoundedCornersTransformation(10f))
        ImageItemShow(GrayscaleTransformation())
        ImageItemShow(BlurTransformation(LocalContext.current, 0.1f, 2f))
    }
}

@Composable
private fun ImageItemShow(transformations: Transformation) {
    Image(
        modifier = Modifier.size(50.dp, 50.dp),
        painter = rememberImagePainter(
            data = "https://img-blog.csdnimg.cn/20210823085620179.jpeg",
            builder = {
                placeholder(R.drawable.head_icon)//占位图
                crossfade(true)//淡出效果
                transformations(transformations)//圆角效果
            }),
        contentScale = ContentScale.Inside,
        contentDescription = null
    )
    Spacer(modifier = Modifier.padding(5.dp))
}