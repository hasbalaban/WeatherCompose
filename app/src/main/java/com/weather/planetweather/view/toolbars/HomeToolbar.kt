package com.weather.planetweather.view.toolbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.weather.planetweather.R
import com.weather.planetweather.view.toolbars.TopAppBarType.*
import kotlinx.coroutines.launch

@Composable
fun HomeTopBar(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    topAppBarType: MutableState<TopAppBarType>,
    search: (city: String) -> Unit,
) {
    val searchedCity =  remember {
        mutableStateOf("")
    }
    TopAppBar(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth(), backgroundColor = MaterialTheme.colors.background,
    ) {
        when(topAppBarType.value){
            MainAppBar -> MainTopAppBar(navController, scaffoldState)
            SearchAppBar -> SearchTopAppBar(navController, scaffoldState, search, searchedCity )
        }
    }
}

@Composable
private fun MainTopAppBar(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
            .fillMaxSize()) {
            Image(imageVector = Icons.Rounded.Menu,
                contentDescription = "Top Menu",
                contentScale = ContentScale.Fit ,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        scope.launch {
                            if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                            else scaffoldState.drawerState.open()
                        }
                    }
            )
            Image(imageVector = Icons.Rounded.Search,
                contentDescription = "Top Search Bar",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        navController.navigate("search_screen")
                    }
            )
    }
}
@Composable
private fun SearchTopAppBar(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    search: (city: String) -> Unit,
    searchedCity: MutableState<String>
) {
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = Unit){
        focusRequester.requestFocus()
    }
    val aaa = KeyboardActions(onDone = {
        search(searchedCity.value)
        navController.navigate("main_screen"){
            popUpTo("main_screen"){
                inclusive = true
            }

        }
    })

    val trailingIconAlpha = if (searchedCity.value.isEmpty()) 0.4f else 1f
    Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = searchedCity.value ,
                trailingIcon = {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text", modifier = Modifier
                        .alpha(trailingIconAlpha)
                        .clickable {
                            searchedCity.value = ""
                        })

                },
                keyboardActions = aaa,
                onValueChange ={
                search(it)
                searchedCity.value = it
            }
                )
            
        }
}

enum class TopAppBarType(){
    MainAppBar, SearchAppBar
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(modifier = Modifier.background(Color.Red),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            painter = painterResource(id = R.drawable.header),
            contentDescription = "bottom Bar",
            contentScale = ContentScale.FillWidth )

    }
}
