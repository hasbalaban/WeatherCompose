package com.weather.planetweather.view.drawers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun MainDrawer (){
    val colors = arrayOf(Pair(0.1f, Color.Black),Pair(0.6f, Color.Red),Pair(0.1f, Color.Green))
    Row(modifier = Modifier.fillMaxSize().background(brush = Brush.radialGradient(colorStops = colors))) {
        Text(text = "test", modifier = Modifier.fillMaxSize())
    }
}