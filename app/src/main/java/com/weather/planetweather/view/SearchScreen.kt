package com.weather.planetweather.view

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.weather.planetweather.R
import com.weather.planetweather.ui.theme.PlanetWeatherTheme
import com.weather.planetweather.view_models.SearchViewModel

@Composable
fun SearchScreen(navController: NavHostController, cities: String,
                 search: (city: String) -> Unit,
                 viewModel: SearchViewModel = hiltViewModel()){
    val context = LocalContext.current
    println("SearchScreen")
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = cities ){
        viewModel.searchCityFromCityList(cities, context)
    }

    val locatedCities = viewModel.locatedCities.observeAsState().value
    locatedCities?.let {
        println(it)
        ResponseSearchedCities(cities = it, context = context, navController, search)
        return
    }
    val fakeCities = listOf<String>("Silopi", "London")
    ResponseSearchedCities(
        cities = fakeCities,
        context = context,
        navController = navController,
        search = search
    )
}


@Composable
fun ResponseSearchedCities (
    cities: List<String>,
    context: Context,
    navController: NavHostController,
    search: (city: String) -> Unit
){
    LazyColumn(modifier = Modifier.background(Color.White)){
        items(cities.size){position ->
            Divider(modifier = Modifier.fillMaxWidth().background(Color.LightGray).height(1.dp).padding(0.dp, 5.dp))
            Spacer(modifier = Modifier.padding(4.dp))
            CityItem(cities, position, context, navController, search)
        }
    }
}

@Composable
private fun CityItem(
    cities: List<String>,
    position: Int,
    context: Context,
    navController: NavHostController,
    search: (city: String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable {
                search(cities[position])
                navController.navigate("main_screen"){
                    popUpTo("main_screen"){
                        inclusive = true
                    }

                }
            }
            .fillMaxWidth()
            .padding(12.dp)) {
        Text(text = cities[position])
        Image(painter = painterResource(id = R.drawable.add_favorite_button), contentDescription = null )
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreviewSearchScreen() {
    PlanetWeatherTheme {
        SearchScreen(navController = rememberNavController() , cities = "London",{

        })
    }
}
