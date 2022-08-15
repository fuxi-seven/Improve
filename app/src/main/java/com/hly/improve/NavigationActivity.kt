package com.hly.improve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hly.improve.navigation.ParamsConfig
import com.hly.improve.navigation.RouteConfig
import com.hly.improve.ui.theme.ImproveTheme

class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImproveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHostDemo()
                }
            }
        }
    }
}

@Composable
fun PageOne(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                Color.White
            )
    ) {
        Text(text = "这是页面1")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            //点击跳转到页面2
            navController.navigate("${RouteConfig.ROUTE_PAGETWO}/Seven/33")
        }) {
            Text(
                text = "跳转页面2",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PageTwo(name: String?, age: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                Color.White
            )
    ) {
        Text(text = "这是页面2")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "我是$name,我今年$age 岁了")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            //点击返回页面1
            navController.popBackStack()
        }) {
            Text(
                text = "返回页面1",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NavHostDemo() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteConfig.ROUTE_PAGEONE) {

        composable(RouteConfig.ROUTE_PAGEONE) {
            PageOne(navController)
        }

        composable(
            "${RouteConfig.ROUTE_PAGETWO}/{${ParamsConfig.PARAMS_NAME}}/{${ParamsConfig.PARAMS_AGE}}",
            arguments = listOf(
                navArgument(ParamsConfig.PARAMS_NAME) {},
                navArgument(ParamsConfig.PARAMS_AGE) { type = NavType.IntType }
            )
        ) {
            val argument = requireNotNull(it.arguments)
            val name = argument.getString(ParamsConfig.PARAMS_NAME)
            val age = argument.getInt(ParamsConfig.PARAMS_AGE)
            PageTwo(name, age, navController)
        }
    }

}