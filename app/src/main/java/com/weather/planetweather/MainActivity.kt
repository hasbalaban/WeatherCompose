package com.weather.planetweather

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weather.planetweather.ui.theme.PlanetWeatherTheme
import com.weather.planetweather.view.MainScreen
import com.weather.planetweather.view.SearchScreen
import com.weather.planetweather.view.drawers.MainDrawer
import com.weather.planetweather.view.toolbars.HomeTopBar
import com.weather.planetweather.view.toolbars.TopAppBarType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PlanetWeatherTheme {
                val navController = rememberNavController()
                val searchedCity = remember { mutableStateOf("") }
                val topBarType  = remember { mutableStateOf(TopAppBarType.MainAppBar) }
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar =
                    {
                        HomeTopBar(scaffoldState, navController, topBarType) {
                        searchedCity.value = it }
                    },
                    drawerContent =
                    {
                        MainDrawer()
                    },

                ) {innerPadding ->
                    Column (modifier = Modifier
                        .padding(innerPadding)) {
                        NavHostGraph(navController, topBarType, searchedCity)
                    }
                }
            }
        }
    }
}


@Composable
fun NavHostGraph(
    navController: NavHostController,
    topAppBarType: MutableState<TopAppBarType>,
    searchedCity: MutableState<String>
) {

    NavHost(navController =navController , startDestination = "main_screen" ){

        composable("main_screen"){
            topAppBarType.value = TopAppBarType.MainAppBar
            MainScreen(city = searchedCity.value, navController = navController )
        }

        composable("search_screen"){
            topAppBarType.value = TopAppBarType.SearchAppBar
            SearchScreen(navController = navController, searchedCity.value,{
                searchedCity.value = it
            } )
        }

    }

}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreview() {
    PlanetWeatherTheme {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val navController = rememberNavController()
        val topAppBarType  = remember {
        mutableStateOf(TopAppBarType.MainAppBar)
        }
        val searchedCity = remember {
            mutableStateOf("")
        }
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {HomeTopBar(scaffoldState, navController, topAppBarType){
                searchedCity.value = it
            } },
            drawerContent = { MainDrawer() }


        ) {innerPadding ->
            Column (modifier = Modifier
                .padding(innerPadding)) {
                NavHostGraph(navController, topAppBarType, searchedCity)
            }
        }
    }
}