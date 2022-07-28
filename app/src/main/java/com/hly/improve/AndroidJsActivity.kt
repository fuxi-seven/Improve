package com.hly.improve

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hly.improve.databinding.ActivityAndroidjsBinding

class AndroidJsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidjsBinding
    private lateinit var settings : WebSettings

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidjsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        androidToJs()
        jsToAndroid()
    }

    private fun androidToJs() {
        settings = binding.androidWeb.settings
        //设置WebView可以与JS交互 这里必须设置
        settings.javaScriptEnabled = true
        //设置允许JS中的弹窗
        settings.javaScriptCanOpenWindowsAutomatically = true

        //然后加载JS代码
        binding.androidWeb.loadUrl("file:///android_asset/index.html")
        //调用JS无参方法
        binding.androidBtn.setOnClickListener {
            binding.androidWeb.post {
                run {
                    //第一种方法 通过loadUrl调用JS代码
                    //调用无参JS方法
                    binding.androidWeb.loadUrl("javascript:clickJS()")
                    //调用有参JS方法
                    // androidWeb.loadUrl("javascript:clickJS('我调用了JS的方法')")
                }
            }
        }
    }

    private fun jsToAndroid() {
        binding.androidWeb.addJavascriptInterface(JsObject(),"android")
        binding.androidWeb.webViewClient = MyWebViewClient()
        binding.androidWeb.webChromeClient = MyWebChromeClient()
    }

    inner class JsObject {
        @JavascriptInterface
        fun jsAndroid(msg : String){
            //点击html的Button调用Android的Toast代码
            //我这里让Toast居中显示了
            val makeText = Toast.makeText(this@AndroidJsActivity, msg,Toast.LENGTH_LONG)
            makeText.setGravity(Gravity.CENTER,0,0)
            makeText.show()
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            //获取Uri,这里的URL是我们在JS方法中写的URL协议"js://webview?name=zhangsan&age=20&sex=0"
            val uri = Uri.parse(url)
            if (uri.scheme == "js") {
                if (uri.authority == "webview") {
                    val makeText = Toast.makeText(this@AndroidJsActivity, url, Toast.LENGTH_LONG)
                    makeText.setGravity(Gravity.CENTER, 0, 0)
                    makeText.show()
                }
                return true
            }
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    inner class MyWebChromeClient : WebChromeClient(){
        override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
            val makeText = Toast.makeText(this@AndroidJsActivity, message, Toast.LENGTH_LONG)
            makeText.setGravity(Gravity.CENTER,0,0)
            makeText.show()
            return super.onJsPrompt(view, url, message, defaultValue, result)
        }
    }
}