package com.hly.improve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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

@Composable
fun showContraintLayout() {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(Color.LightGray)) {

        val (head, name, id, qr, right) = createRefs()

        Image(painter = painterResource(R.drawable.ic_account), "head",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(head) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 20.dp)
                .size(60.dp)
                .clip(CircleShape)
        )

        Text("Android开发那点事儿",
            style = TextStyle(fontSize = 16.sp,
                color = Color.Black, fontWeight = FontWeight(600)
            ),
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(head.top)
                    start.linkTo(head.end)
                }
                .padding(start = 10.dp, top = 5.dp)
        )

        Text("微信号:android-blog",
            style = TextStyle(fontSize = 12.sp,
                color = Color.DarkGray, fontWeight = FontWeight(400)),
            modifier = Modifier
                .constrainAs(id) {
                    top.linkTo(name.bottom)
                    start.linkTo(name.start)
                }
                .padding(start = 10.dp, top = 5.dp)
        )

        Image(
            painter = painterResource(R.drawable.ic_qr),"",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(qr) {
                    top.linkTo(head.top)
                    bottom.linkTo(head.bottom)
                    end.linkTo(parent.end, 30.dp)
                })

        Image(
            painter = painterResource(R.drawable.ic_right), "",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(right) {
                    top.linkTo(qr.top)
                    bottom.linkTo(qr.bottom)
                    start.linkTo(qr.end)
                })
    }
}