package com.hly.improve.ui.composeWidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

class NetworkImgWidget {

    companion object {
        @Composable
        fun NetworkImg(index: Int) {
            val imgList = listOf(
                "https://img1.baidu.com/it/u=2943699027,12737242&fm=253&fmt=auto&app=120&f=JPEG?w=781&h=500",
                "https://img-blog.csdnimg.cn/20210823085620179.jpeg",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.tuchong.com%2F7778538%2Ff%2F449478719.jpg&refer=http%3A%2F%2Fphoto.tuchong.com&app=2002&size=f9999,10000&q=a80&n=0",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.tuchong.com%2F1454835%2Ff%2F8764156.jpg&refer=http%3A%2F%2Fphoto.tuchong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg8.zol.com.cn%2Fbbs%2Fupload%2F19768%2F19767286.jpg&refer=http%3A%2F%2Fimg8.zol.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.tuchong.com%2F5063625%2Ff%2F756815784.jpg&refer=http%3A%2F%2Fphoto.tuchong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1312%2F20%2Fc0%2F29812058_1387481203947.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1404%2F29%2Fc3%2F33695617_33695617_1398756004682.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpp.qn.img-space.com%2Fg1%2FM00%2F08%2F5B%2FCg-4q1dzGseIXQSRAA4uyv-86_sAATFWQDWz-kADi7i541.jpg%3FimageView2%2F2%2Fw%2F1200%2Fq%2F100%2Fignore-error%2F1%2F&refer=http%3A%2F%2Fpp.qn.img-space.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg"
            )
            Image(
                modifier = Modifier.size(1000.dp, 300.dp),
                // painter = rememberImagePainter("https://img-blog.csdnimg.cn/20210823085620179.jpeg"),
                painter = rememberImagePainter(imgList[index]),
                contentDescription = null
            )
        }
    }
}