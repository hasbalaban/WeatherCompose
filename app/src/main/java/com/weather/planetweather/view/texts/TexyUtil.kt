package com.weather.planetweather.view.texts

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@Composable
fun TextCreator(text :String, color: Color, fontSize : TextUnit, weight: FontWeight, modifier: Modifier = Modifier){
    Text(text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = weight,
        fontFamily = FontFamily.SansSerif,
        modifier = modifier.padding(top = 3.dp)
    )
}