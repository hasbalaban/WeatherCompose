package com.weather.planetweather.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.weather.planetweather.R
import com.weather.planetweather.models.CitySummery
import com.weather.planetweather.models.weather7days.WeatherModel
import com.weather.planetweather.view.texts.TextCreator
import com.weather.planetweather.view_models.HomeViewModel
import com.weather.planetweather.view_models.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(city : String?, navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    println("MainScreen")
    var loadingStatus  = produceState<Status>(initialValue = Status.LOADING){
        viewModel.weatherLoading(city).collect(){
            value = it
        }
    }

    val timer = remember{ mutableStateOf(0f) }

    if (timer.value < 1200 && loadingStatus.value == Status.LOADING){
        LaunchedEffect(key1 = Any() ){
            timer.value = timer.value+1f
        }
    }

    when {
        loadingStatus.value == Status.LOADING && viewModel.weather7Days.value == null -> {
            Column(modifier =  Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                //CircularProgressIndicator(color = Color(0xFF3AD86F), strokeWidth = 4.dp)
                CircularProgressIndicator(color = Color(0xFF3AD86F), strokeWidth = 4.dp, progress = timer.value / 1200,
                    modifier = Modifier.size(70.dp)
                )
            }
        }

        viewModel.weather7Days.value != null || loadingStatus.value == Status.SUCCESS ->  {
            Home(forecast = viewModel.weather7Days.value )
        }

        loadingStatus.value == Status.FAIL -> {
            Column(modifier =  Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(color = Color.Red)
                loadingStatus  = produceState<Status>(initialValue = Status.LOADING) {
                    viewModel.weatherLoading(null).collect() {
                        value = it
                    }
                }
            }
        }
    }
}


@Composable fun Home(forecast: WeatherModel?) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState(), reverseScrolling = false)) {

        val cityInfo = CitySummery()
        forecast?.let {
            SummeryCity(cityInfo, it)
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CityWeatherImageAndDegree(it)
            SelectedCityDetails(it)
            HoursDetailsContent(it)
        }
    }
}


@Composable
fun SummeryCity(cityInfo: CitySummery, forecast: WeatherModel) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, top = 20.dp)) {
        TextCreator(text = forecast.location.name +",",
            color =Color(0xFF313341), fontSize = 24.sp, weight = FontWeight.Bold )

        TextCreator(text = forecast.location.country,
            color =Color(0xFF313341), fontSize = 24.sp, weight = FontWeight.Bold )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)) {
            TextCreator(text =cityInfo.day,
                color = Color(0xFF9A938C), fontSize  = 14.sp, weight = FontWeight.Bold )

            TextCreator(text =cityInfo.month,
                color = Color(0xFF9A938C),
                fontSize  = 14.sp, weight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp))

            TextCreator(text =cityInfo.degree,
                color = Color(0xFF9A938C), fontSize  = 14.sp, weight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp))

        }

    }
}


@Composable
fun CityWeatherImageAndDegree(forecast: WeatherModel) {
    val imageUrl = "https:" + forecast.current.condition.icon
            val painter = rememberAsyncImagePainter(model = imageUrl, placeholder = painterResource(id = R.drawable.umbrella))
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                val (imageLayout, textLayout) = createRefs()
                Column(modifier = Modifier
                    .constrainAs(imageLayout) {
                        top.linkTo(textLayout.top, 10.dp)
                        bottom.linkTo(textLayout.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(textLayout.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Success -> {
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        else -> {
                            AsyncImage(model = "https:" + forecast.current.condition.icon,
                                contentDescription = null, modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                Column(modifier = Modifier
                    .padding(bottom = 10.dp)
                    .constrainAs(textLayout) {
                        top.linkTo(parent.top)
                        start.linkTo(imageLayout.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }) {

                    TextCreator(
                        text = forecast.current.tempC.toString() + "℃",
                        color = Color(0xFF303345),
                        fontSize = 50.sp,
                        weight = FontWeight.ExtraBold
                    )

                    TextCreator(
                        text = forecast.current.condition.text,
                        color = Color(0xFF303345),
                        fontSize = 20.sp,
                        weight = FontWeight.Bold,
                        Modifier.padding(start = 10.dp)
                    )
                }
            }
}


@Composable
fun SelectedCityDetails(forecast: WeatherModel) {
    CityDetailsItems(image = R.drawable.umbrella, state = "pressure",
        expect = forecast.current.pressureMb.toString())
    CityDetailsItems(image = R.drawable.wind, state = "windy",
        expect =forecast.current.windKph.toString() + " km/h")
    CityDetailsItems(image = R.drawable.humidity, state = "humidity",
        expect = forecast.current.pressureMb.toString() +"%")

}

@Composable
fun CityDetailsItems(image : Int, state : String, expect : String){
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween ,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            .clickable(enabled = true) {
                Toast
                    .makeText(context, state, Toast.LENGTH_LONG)
                    .show()
            }
            .shadow(3.dp, shape = RoundedCornerShape(12.dp))
            .background(Color(0xffffffff))
            .padding(20.dp)
    ) {
        Image(painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        TextCreator(text = state,
            color = Color(0xFF303345),
            fontSize = 14.sp ,
            weight = FontWeight.Normal,
            modifier = Modifier.padding(start = 12.dp)
        )
        TextCreator(text = expect,
            color = Color(0xFF303345),
            fontSize = 14.sp ,
            weight = FontWeight.Normal,
            modifier = Modifier.padding(start = 12.dp)
        )
    }

}

@Composable
fun HoursDetailsContent(forecast: WeatherModel) {
    val days = arrayOf(CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery(),CitySummery())
    LazyRow(modifier = Modifier
        .padding(top = 40.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        items (days.size) {index->
            HoursDetailsItem(days[index], index)
        }
    }
}

@Composable
fun HoursDetailsItem(citySummery: CitySummery, index: Int) {
    Log.d("system.out", index.toString())
    Column(modifier = Modifier
        .padding(10.dp)
        .shadow(4.dp, shape = RoundedCornerShape(12.dp))
        .background(Color.White)
        .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextCreator(text = citySummery.cityName,
            color = Color(0xFF303345),
            fontSize = 14.sp,
            weight = FontWeight.Normal,
            modifier =  Modifier.padding(start = 12.dp)
        )

        Image(painter = painterResource(id = R.drawable.cludy), contentDescription = null)

        TextCreator(text = citySummery.country,
            color = Color(0xFF303345),
            fontSize = 14.sp,
            weight = FontWeight.Normal,
            modifier =  Modifier.padding(start = 12.dp)
        )

    }
}


private fun searchWeatherStateRequest(){

}


